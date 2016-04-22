package neu.quwanme.student;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
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
import neu.quwanme.shop.ActivityMain;
import neu.quwanme.tools.DateTimePickDialogUtil;
import neu.quwanme.tools.DateTools;
import neu.quwanme.tools.GSONTOOLS;
import neu.quwanme.tools.UrlParseTool;

/**
 * Created by Lonie233 on 2016/4/14.
 * aty详情页，进行改，删操作
 */

public class StuOneAtyDetail extends AppCompatActivity {


    @Bind(R.id.et_atyname)
    TextView etAtyname;
    @Bind(R.id.et_atyAddr)
    TextView etAtyAddr;
    @Bind(R.id.et_atyMaxPopu)
    TextView etAtyMaxPopu;
    @Bind(R.id.et_atyCurPopu)
    TextView etAtyCurPopu;
    @Bind(R.id.et_atyMinPopu)
    TextView etAtyMinPopu;
    @Bind(R.id.et_starttime)
    TextView etStarttime;
    @Bind(R.id.et_endtime)
    TextView etEndtime;
    @Bind(R.id.status_list)
    TextView statusList;

    private Activity mActivity;

    private List<String> statusname = new ArrayList<>();
    private ArrayAdapter<String> arr_adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stuoneatydetail);
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
        if (mActivity == null) {
            Toast.makeText(this, "aty参数传递错误", Toast.LENGTH_SHORT).show();
        }
        initView();
    }

    public void initView() {
        etAtyname.setText(mActivity.getActivityName());
        etAtyAddr.setText(mActivity.getActivityAddr() == null ? "" : mActivity.getActivityAddr());
        etAtyMaxPopu.setText(mActivity.getActivityMaxPopu() == null ? "" : mActivity.getActivityMaxPopu() + "");
        etAtyCurPopu.setText(mActivity.getActivityCurPeople()==0?"你是第一个呦":mActivity.getActivityCurPeople()+" 人已参与");
        etAtyMinPopu.setText(mActivity.getActiviityMinPopu() == null ? "" : mActivity.getActivityMaxPopu() + "");
        etStarttime.setText(DateTools.parseDate(mActivity.getActivityStartTime()));
        etEndtime.setText(DateTools.parseDate(mActivity.getActivityEndTime()));

        String status = "未开始";
        if (mActivity.getActivityStatus() != null) {
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
        }
        statusList.setText(status);
    }

    @OnClick({R.id.btn_join, R.id.btn_delete_aty})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_join:
                doNetWork(OfficalUrl.UserJoinAtyUrl, true);
                break;
            case R.id.btn_delete_aty:
                doNetWork(OfficalUrl.DeleteAtyUrl, false);
                break;
        }
    }

    public void doNetWork(String url, boolean IsUpdate) {
        url = OfficalUrl.baseUrl + OfficalUrl.AtyBaseUrl + url;

    }
}
