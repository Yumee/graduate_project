package neu.quwanme.student;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import neu.quwanme.CONFIG.Symbols;
import neu.quwanme.R;
import neu.quwanme.bean.Activity;
import neu.quwanme.shop.ShopRank;
import neu.quwanme.tools.LogUtil;
import neu.quwanme.tools.PreferencesUtils;
import neu.quwanme.tools.PullingUtils;

/**
 * Created by Lonie233 on 2016/3/21.
 * 学生的主页面
 */
public class StudentMainActivity extends AppCompatActivity {


    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Bind(R.id.tv_user_age)
    TextView tvUserAge;
    @Bind(R.id.tv_scorll_text)
    TextView tvScorllText;

    private UpdateEsllipReceiver updateEsllipReceiever ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);
        ButterKnife.bind(this);
        initView();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PullingService.BROADCASTACTION);
        updateEsllipReceiever = new UpdateEsllipReceiver();
        registerReceiver(updateEsllipReceiever, intentFilter);
        PullingUtils.startPullingService(this, 1, PullingService.class, PullingService.ACTION);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(updateEsllipReceiever);
        PullingUtils.stopPullingService(this, PullingService.class, PullingService.ACTION);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_wan_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void initView() {

        tvUserAge.setText(PreferencesUtils.getString(Symbols.userAge));
        tvUserName.setText(PreferencesUtils.getString(Symbols.userRealName).substring(0, PreferencesUtils.getString(Symbols.userAge).length() - 2));

    }

    @OnClick({R.id.imageView, R.id.rl_last_aty, R.id.rl_my_aty, R.id.rl_person_info, R.id.rl_shop_rank_list})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView:
                // : 2016/4/7 更换头像
                startActivity(new Intent(this, StudentDetail.class));
                break;
            case R.id.rl_last_aty:
                // : 2016/4/7 查看最新活动
                startActivity(new Intent(this, LastestAty.class));
                break;
            case R.id.rl_my_aty:
                startActivity(new Intent(this, MytAty.class));
                // : 2016/4/7 查看我参与的（参与过的，报名的）
                break;
            case R.id.rl_person_info:
                // : 2016/4/7 查看，修改个人信息
                startActivity(new Intent(this, StudentDetail.class));
                break;
            case R.id.rl_shop_rank_list:
                // : 2016/4/7 查看商家排名
                startActivity(new Intent(this, ShopRank.class));
                break;
        }
    }


    /**
     * 设置滚动条内容及链接
     */
    public void setScorllText(Activity aty) {
        Toast.makeText(this, "有新活动推送了哦", Toast.LENGTH_SHORT).show();
        if (aty != null) {
            tvScorllText.setText(aty.getActivityName() +" "+aty.getActivityComment());
        }
    }
    class UpdateEsllipReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtil.d("hzm","获得轮询的广播 "+action);
            if (PullingService.BROADCASTACTION.equals(action)) {
                Bundle b = intent.getExtras();
                Activity aty = (Activity) b.get("aty");
                setScorllText(aty);
            }
        }
    }
}
