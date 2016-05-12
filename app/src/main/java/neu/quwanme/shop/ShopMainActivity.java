package neu.quwanme.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import neu.quwanme.CONFIG.Symbols;
import neu.quwanme.R;
import neu.quwanme.tools.PreferencesUtils;

/**
 * Created by Lonie233 on 2016/3/21.
 */
public class ShopMainActivity extends AppCompatActivity {

    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.tv_shop_name)
    TextView tvShopName;
    @Bind(R.id.tv_shop_owner)
    TextView tvShopOwner;
    @Bind(R.id.tv_shop_favor)
    TextView tvShopFavor;
    @Bind(R.id.tv_shop_rank)
    TextView tvShopRank;
    @Bind(R.id.tv_scorll_text)
    TextView tvScorllText;

    private long exitTime = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_main);
        ButterKnife.bind(this);
//        setTvScorllText();
        initView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    @OnClick({R.id.imageView, R.id.rl_manage_aty, R.id.rl_my_aty, R.id.rl_person_info, R.id.rl_shop_rank_list, R.id.tv_scorll_text})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView:
                startActivity(new Intent(this, ShopDetail.class).putExtra(Symbols.from, Symbols.fromShopMain));
                break;
            case R.id.rl_manage_aty:
                Intent i = new Intent(this, ActivityMain.class);
                i.putExtra(Symbols.IsFromOtherAty, true);
                startActivity(i);
                break;
            case R.id.rl_my_aty:
                startActivity(new Intent(this, ActivityMain.class));
                break;
            case R.id.rl_person_info:
                startActivity(new Intent(this, ShopDetail.class).putExtra(Symbols.from, Symbols.fromShopMain));
                break;
            case R.id.rl_shop_rank_list:
                startActivity(new Intent(this, ShopRank.class));
                break;
            case R.id.tv_scorll_text:
                break;
        }
    }

    /**
     * 根据sp设置view显示的shop相关信息
     */
    public void initView() {

        try {
            tvShopName.setText(PreferencesUtils.getString(Symbols.shopName));
            tvShopOwner.setText("黄教主");
            tvShopFavor.setText(PreferencesUtils.getString(Symbols.shopCity));
            tvShopRank.setText(PreferencesUtils.getString(Symbols.shopType));
            imageView.setImageResource(R.drawable.shop);
        } catch (Exception e) {

        }

    }

    public void setTvScorllText(){
        tvScorllText.setFocusable(true);
    }
}
