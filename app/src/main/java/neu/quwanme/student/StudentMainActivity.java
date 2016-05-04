package neu.quwanme.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import neu.quwanme.R;

/**
 * Created by Lonie233 on 2016/3/21.
 * 学生的主页面
 */
public class StudentMainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_wan_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void initView() {


    }

    @OnClick({R.id.imageView, R.id.rl_last_aty, R.id.rl_my_aty, R.id.rl_person_info, R.id.rl_shop_rank_list})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView:
                // TODO: 2016/4/7 更换头像 
                break;
            case R.id.rl_last_aty:
                // TODO: 2016/4/7 查看最新活动
                startActivity(new Intent(this,LastestAty.class));
                break;
            case R.id.rl_my_aty:
                // TODO: 2016/4/7 查看我参与的（参与过的，报名的） 
                break;
            case R.id.rl_person_info:
                // TODO: 2016/4/7 查看，修改个人信息 
                break;
            case R.id.rl_shop_rank_list:
                // TODO: 2016/4/7 查看商家排名 
                break;
        }
    }

    /**
     *  设置滚动条内容及链接
     */
    public void setScorllText(){

    }
}
