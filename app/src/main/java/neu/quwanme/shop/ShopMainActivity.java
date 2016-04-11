package neu.quwanme.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
 */
public class ShopMainActivity extends AppCompatActivity {

    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.rl_last_aty)
    RelativeLayout rlLastAty;
    @Bind(R.id.rl_my_aty)
    RelativeLayout rlMyAty;
    @Bind(R.id.rl_person_info)
    RelativeLayout rlShopInfo;
    @Bind(R.id.rl_shop_rank_list)
    RelativeLayout rlShopRankList;
    @Bind(R.id.tv_scorll_text)
    TextView tvScorllText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView, R.id.rl_last_aty, R.id.rl_my_aty, R.id.rl_person_info, R.id.rl_shop_rank_list, R.id.tv_scorll_text})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView:
                break;
            case R.id.rl_last_aty:
                break;
            case R.id.rl_my_aty:
                break;
            case R.id.rl_person_info:
                break;
            case R.id.rl_shop_rank_list:
                break;
            case R.id.tv_scorll_text:
                break;
        }
    }
}
