package neu.quwanme.student;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
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
import neu.quwanme.tools.DateTools;
import neu.quwanme.tools.GSONTOOLS;
import neu.quwanme.tools.PreferencesUtils;
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
    @Bind(R.id.ly_user_join_del_btn)
    LinearLayout btn1;
    @Bind(R.id.ly_user_cancel_join_btn)
    LinearLayout btnCancelJoin;
    @Bind(R.id.btn_alarm)
    Button btnAlarm;

    private Activity mActivity;
    private RefreshCallBack mRefreshCallBack;
    boolean IsAlarm = false ;
    public int fromWhere = 0;//0 最新活动  1活动推荐  2我的参与   3  历史活动


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

    /**
     * 参加或者退出活动之后，返回到列表页
     */
    public void finshOP() {
        onBackPressed();
    }

    public void initData() {
        Intent i = this.getIntent();
        mActivity = (Activity) i.getSerializableExtra("aty");
        if (i.getStringExtra(Symbols.from) == null) {
            Toast.makeText(this, "来源错误", Toast.LENGTH_SHORT).show();
        } else {
            String from = i.getStringExtra(Symbols.from);
            if (Symbols.fromLastestAty.equals(from)) {
                fromWhere = 0;
            } else if (Symbols.fromRecomAty.equals(from)) {
                fromWhere = 1;
            } else if (Symbols.fromMyAty.equals(from)) {
                fromWhere = 2;
            } else if (Symbols.fromMyAtyHis.equals(from)) {
                fromWhere = 3;
            } else {
                Toast.makeText(this, "未知来源", Toast.LENGTH_SHORT).show();
            }
        }
        if (mActivity == null) {
            Toast.makeText(this, "aty参数传递错误", Toast.LENGTH_SHORT).show();
        }
        initView();
    }

    public void initView() {
        etAtyname.setText(mActivity.getActivityName());
        etAtyAddr.setText(mActivity.getActivityAddr() == null ? "" : mActivity.getActivityAddr());
        etAtyMaxPopu.setText(mActivity.getActivityMaxPopu() == null ? "" : mActivity.getActivityMaxPopu() + "");
        etAtyCurPopu.setText(mActivity.getActivityCurPeople() == 0 ? "你是第一个呦" : mActivity.getActivityCurPeople() + " 人已参与");
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
        //根据来源控制显示的按钮
        if (fromWhere == 0 || fromWhere == 1) {//最新活动
            btn1.setVisibility(View.VISIBLE);
            btnCancelJoin.setVisibility(View.GONE);
        } else if (fromWhere == 2) {//我的活动
            btn1.setVisibility(View.GONE);
            btnCancelJoin.setVisibility(View.VISIBLE);
            IsAlarm = PreferencesUtils.getBoolean("IsHasAlarm", false);

            if (IsAlarm) {
                btnAlarm.setText("取消闹钟");
            }else {
                btnAlarm.setText("设置闹钟");
            }
        } else if (fromWhere == 3) {
            btn1.setVisibility(View.GONE);
            btnCancelJoin.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.btn_join, R.id.btn_cancel_join, R.id.btn_alarm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_join:
                doNetWork(OfficalUrl.UserJoinAtyUrl, 1);
                break;
            case R.id.btn_cancel_join:
                doNetWork(OfficalUrl.UserQuitAtyUrl, 2);
                break;
            case R.id.btn_alarm:
                setAlarm();
                break;
        }
    }

    /**
     * 参加活动还是退出活动
     *
     * @param url
     * @param op
     */
    public void doNetWork(String url, final int op) {

        Map<String, String> params = new HashMap<>();

        switch (op) {
//            case 0://删除
//            {
//                url = OfficalUrl.baseUrl + OfficalUrl.AtyBaseUrl +url;
//                params.put(Symbols.activityId,mActivity.getActivityId()+"");
//            }
//            break;
            case 1://加入
            {
                url = OfficalUrl.baseUrl + OfficalUrl.UserBaseUrl + url;
                params.put(Symbols.userId, PreferencesUtils.getString(Symbols.userId));
                params.put(Symbols.activityId, mActivity.getActivityId() + "");
            }
            break;
            case 2://退出
            {
                url = OfficalUrl.baseUrl + OfficalUrl.UserBaseUrl + url;
                params.put(Symbols.userId, PreferencesUtils.getString(Symbols.userId));
                params.put(Symbols.activityId, mActivity.getActivityId() + "");
            }
            break;

        }

        NetWorker.getInstance().get(UrlParseTool.parseParam(url, params), new NetWorker.ICallback() {
                    @Override
                    public void onResponse(int status, String result) {
                        if (NetWorker.HTTP_OK == status) {
                            // TODO: 2016/4/22 处理返回结果
                            Type type = new TypeToken<Map<String, Integer>>() {
                            }.getType();
                            Map<String, Integer> map = GSONTOOLS.getMap(result, type);
                            if (map.get(Status_Code.Status_Code) == Status_Code.SUCCESS_STATUS) {
                                switch (op) {
                                    case 0:
                                        Toast.makeText(StuOneAtyDetail.this, "删除活动成功", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 1:
                                        Toast.makeText(StuOneAtyDetail.this, "报名成功", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 2:
                                        Toast.makeText(StuOneAtyDetail.this, "退出成功", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        }
                    }
                }
        );

        finshOP();

    }

    interface RefreshCallBack {
        void opCallBack(String url);
    }

    /**
     * 设置或者取消闹钟
     */
    public void setAlarm() {

//        if(IsAlarm){


//        }else {
            Intent alarmas = new Intent(AlarmClock.ACTION_SET_ALARM);
            alarmas.putExtra(AlarmClock.EXTRA_HOUR,18);
            alarmas.putExtra(AlarmClock.EXTRA_MINUTES,0);
//            alarmas.putExtra(AlarmClock.EXTRA_DAYS,)
            startActivity(alarmas);
//        }

    }

}
