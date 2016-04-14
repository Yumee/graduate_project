package neu.quwanme.shop;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import neu.quwanme.CONFIG.Symbols;
import neu.quwanme.R;
import neu.quwanme.bean.Activity;
import neu.quwanme.tools.DateTools;

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
    @Bind(R.id.btn_create_aty)
    Button btnCreateAty;
    @Bind(R.id.btn_shop_login)
    Button btnShopLogin;
    @Bind(R.id.status_list)
    Spinner statusList;
    private Activity mActivity;

    private List<String> statusname = new ArrayList<>();
    private ArrayAdapter<String> arr_adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oneatydetail);
        ButterKnife.bind(this);
        initData();
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
        etAtyAddr.setText(mActivity.getActivityAddr());
        etAtyMaxPopu.setText(mActivity.getActivityMaxPopu()+"");
        etAtyMinPopu.setText(mActivity.getActiviityMinPopu()+"");
        etStarttime.setText(DateTools.parseDate(mActivity.getActivityStartTime()));
        etEndtime.setText(DateTools.parseDate(mActivity.getActivityEndTime()));
        String status="未开始";
        switch (mActivity.getActivityStatus()){
            case Symbols.Not_Start:
                status="未开始";
                break;
            case Symbols.Strated:
                status="已开始";
                break;
            case Symbols.Finished:
                status="已结束";
                break;
        }
        statusname.add(status+"（当前）");
        statusname.add("未开始");
        statusname.add("已开始");
        statusname.add("已结束");
        arr_adapter = new ArrayAdapter<>(this, R.layout.spinner_item, statusname);
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusList.setAdapter(arr_adapter);
    }

    @OnClick({R.id.btn_create_aty, R.id.btn_shop_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_create_aty:
                break;
            case R.id.btn_shop_login:
                break;
        }
    }
}
