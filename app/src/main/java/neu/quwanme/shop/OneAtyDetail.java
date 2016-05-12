package neu.quwanme.shop;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import neu.quwanme.CONFIG.OfficalUrl;
import neu.quwanme.CONFIG.Status_Code;
import neu.quwanme.CONFIG.Symbols;
import neu.quwanme.R;
import neu.quwanme.bean.Activity;
import neu.quwanme.framwork.net.NetWorker;
import neu.quwanme.tools.DateTimePickDialogUtil;
import neu.quwanme.tools.DateTools;
import neu.quwanme.tools.GSONTOOLS;
import neu.quwanme.tools.UrlParseTool;

/**
 * Created by Lonie233 on 2016/4/14.
 * aty详情页，进行改，删操作
 */

public class OneAtyDetail extends AppCompatActivity {

    @Bind(R.id.et_atyname)
    EditText etAtyname;
    @Bind(R.id.et_atyAddr)
    EditText etAtyAddr;
    @Bind(R.id.et_atyMaxPopu)
    EditText etAtyMaxPopu;
    @Bind(R.id.et_atyMinPopu)
    EditText etAtyMinPopu;
    @Bind(R.id.et_starttime)
    EditText etStarttime;
    @Bind(R.id.et_endtime)
    EditText etEndtime;
    @Bind(R.id.status_list)
    Spinner statusList;
    @Bind(R.id.ly_shop_op_btn)
    LinearLayout lyShopOpBtn;
    private Activity mActivity;

    private List<String> statusname = new ArrayList<>();
    private ArrayAdapter<String> arr_adapter;
    private boolean IsFromOtherAty = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oneatydetail);
        ButterKnife.bind(this);
        initData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void initData() {
        Intent i = this.getIntent();
        mActivity = (Activity) i.getSerializableExtra("aty");
        IsFromOtherAty = i.getBooleanExtra(Symbols.IsFromOtherAty, false);
        if (mActivity == null) {
            Toast.makeText(this, "aty参数传递错误", Toast.LENGTH_SHORT).show();
        }
        initView();
    }

    public void initView() {
        etAtyname.setText(mActivity.getActivityName());
        etAtyAddr.setText(mActivity.getActivityAddr());
        etAtyMaxPopu.setText(mActivity.getActivityMaxPopu() + "");
        etAtyMinPopu.setText(mActivity.getActiviityMinPopu() + "");
        etStarttime.setText(DateTools.parseDate(mActivity.getActivityStartTime()));
        etEndtime.setText(DateTools.parseDate(mActivity.getActivityEndTime()));
        final Date d = new Date();
        etStarttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePickDialogUtil dateTimePickDialog = new DateTimePickDialogUtil(OneAtyDetail.this, d.getYear() + "年" + d.getMonth() + "月" + d.getDay() + "日 " + d.getHours() + ":" + d.getMinutes());
                dateTimePickDialog.dateTimePicKDialog(etStarttime);
            }
        });
        etEndtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePickDialogUtil dateTimePickDialog = new DateTimePickDialogUtil(OneAtyDetail.this, d.getYear() + "年" + d.getMonth() + "月" + d.getDay() + "日 " + d.getHours() + ":" + d.getMinutes());
                dateTimePickDialog.dateTimePicKDialog(etEndtime);
            }
        });
        String status = "未开始";
        switch (mActivity.getActivityStatus()) {
            case Symbols.Not_Start:
                status = "未开始";
                break;
            case Symbols.Strated:
                status = "已开始";
                break;
            case Symbols.Finished:
                status = "已结束";
                break;
        }
        statusname.add(status + "（当前）");
        statusname.add("未开始");
        statusname.add("已开始");
        statusname.add("已结束");
        arr_adapter = new ArrayAdapter<>(this, R.layout.spinner_item, statusname);
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusList.setAdapter(arr_adapter);

        if (IsFromOtherAty){//来自他的活动，则不能进行操作，所有信息无法修改，不显示操作按钮
            etAtyname.setEnabled(false);
            etAtyAddr.setEnabled(false);
            etAtyMaxPopu.setEnabled(false);
            etAtyMinPopu.setEnabled(false);
            etEndtime.setEnabled(false);
            etStarttime.setEnabled(false);
            statusList.setEnabled(false);
            lyShopOpBtn.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.btn_update_aty, R.id.btn_delete_aty})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_update_aty:
                doNetWork(OfficalUrl.UpdateAtyUrl, true);
                break;
            case R.id.btn_delete_aty:
                doNetWork(OfficalUrl.DeleteAtyUrl, false);
                break;
        }
    }

    public void doNetWork(String url, boolean IsUpdate) {
        url = OfficalUrl.baseUrl + OfficalUrl.AtyBaseUrl + url;
        Map<String, String> params = new HashMap<>();
        params.put("activityId", mActivity.getActivityId() + "");
        if (IsUpdate) {
            params.put("activityName", URLEncoder.encode(etAtyname.getText().toString()));
            params.put("activityAddr", URLEncoder.encode(etAtyAddr.getText().toString()));
            params.put("startTime", URLEncoder.encode(etStarttime.getText().toString().replace("年", "-").replace("月", "-").replace("日", "")));
            params.put("endTime", URLEncoder.encode(etEndtime.getText().toString().replace("年", "-").replace("月", "-").replace("日", "")));
            params.put("activityCurPeople", "0");
            params.put("activityMaxPopu", etAtyMaxPopu.getText().toString());
            params.put("activiityMinPopu", etAtyMinPopu.getText().toString());
            String status;
            if (statusList.getSelectedItem().toString().equals("未开始")) {
                status = "0";
            } else if (statusList.getSelectedItem().toString().equals("已开始")) {
                status = "1";
            } else {
                status = "2";
            }
            params.put("activityStatus", status);
        }

        NetWorker.getInstance().get(UrlParseTool.parseParam(url, params), new NetWorker.ICallback() {
            @Override
            public void onResponse(int status, String result) {
                if (status == NetWorker.HTTP_OK) {
                    Type t = new TypeToken<Map<String, Integer>>() {
                    }.getType();
                    Map<String, Integer> map = GSONTOOLS.getMap(result, t);
                    int status_code = map.get(Status_Code.Status_Code);
                    switch (status_code) {
                        case Status_Code.SUCCESS_STATUS:
                            //跳转默认活动列表
                            Toast.makeText(OneAtyDetail.this, "操作成功", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(OneAtyDetail.this, ActivityMain.class));
                            break;
                        case Status_Code.EXCEPTION_STATUS:
                            Toast.makeText(OneAtyDetail.this, "数据操作异常,请联系数据库", Toast.LENGTH_SHORT).show();
                            break;
                        case Status_Code.FAILED:
                            Toast.makeText(OneAtyDetail.this, "操作失败", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }
        });

    }
}
