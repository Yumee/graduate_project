package neu.quwanme.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import neu.quwanme.bean.Shop;
import neu.quwanme.framwork.net.NetWorker;
import neu.quwanme.tools.GSONTOOLS;
import neu.quwanme.tools.LogUtil;
import neu.quwanme.tools.PreferencesUtils;
import neu.quwanme.tools.UrlParseTool;

/**
 * Created by Lonie233 on 2016/4/15.
 */
public class ShopDetail extends AppCompatActivity {

    @Bind(R.id.et_shopname)
    EditText etShopname;
    @Bind(R.id.et_shopAddr)
    EditText etShopAddr;
    @Bind(R.id.et_shoppwd)
    EditText etShopPwd;
    @Bind(R.id.tv_shop_rank)
    TextView tvShopRank;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopdetail);
        ButterKnife.bind(this);
        initView();
    }

    public void updateInfo() {
        String url = UrlParseTool.parseUrl(OfficalUrl.baseUrl, OfficalUrl.ShopUpdatetUrl);
        Map<String, String> params = new HashMap();

        params.put(Symbols.shopId, PreferencesUtils.getString(Symbols.shopId));
        params.put(Symbols.shopCity,etShopAddr.getText().toString());
        params.put(Symbols.shopName,etShopname.getText().toString());
        params.put(Symbols.shopPassword,etShopPwd.getText().toString());

        NetWorker.getInstance().get(UrlParseTool.parseParam(url, params), new NetWorker.ICallback() {
            @Override
            public void onResponse(int status, String result) {
                if (status == NetWorker.HTTP_OK){
                    Type type = new TypeToken<Map<String,Integer>>(){}.getType();
                    Map<String,Integer> res = GSONTOOLS.getMap(result,type);
                    if (res.get(Status_Code.Status_Code) == Status_Code.SUCCESS_STATUS){
                        Toast.makeText(ShopDetail.this, "信息更新成功", Toast.LENGTH_SHORT).show();
                        PreferencesUtils.putString(Symbols.shopName,etShopname.getText().toString());
                        PreferencesUtils.putString(Symbols.shopPassword,etShopPwd.getText().toString());
                        PreferencesUtils.putString(Symbols.shopCity,etShopAddr.getText().toString());
                    }
                }
            }
        });
    }

    public void initView() {
        if (getIntent()!=null) {
            if (getIntent().getStringExtra(Symbols.from).equals(Symbols.fromShopMain)) {
                etShopname.setText(PreferencesUtils.getString(Symbols.shopName));
                etShopAddr.setText(PreferencesUtils.getString(Symbols.shopCity));
                etShopPwd.setText(PreferencesUtils.getString(Symbols.shopPassword));
                tvShopRank.setText(PreferencesUtils.getString(Symbols.shopType));
            }
            if (getIntent().getStringExtra(Symbols.from).equals(Symbols.fromShopRank)) {
                Shop s = (Shop) getIntent().getExtras().get(Symbols.shop);
                etShopname.setText(s.getShopName());
                etShopAddr.setText(s.getShopCity());
                if (PreferencesUtils.getString(Symbols.shopId).equals(s.getShopId() + "")) {
                    etShopPwd.setText(s.getShopPassword());
                } else {
                    findViewById(R.id.ly_shoppwd).setVisibility(View.GONE);
                    findViewById(R.id.ly_shop_loginbtn).setVisibility(View.GONE);
                    etShopname.setEnabled(false);
                    etShopAddr.setEnabled(false);
                }
                tvShopRank.setText(s.getShopType());
            }
        }
    }

    @OnClick({R.id.btn_update_shop, R.id.btn_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_update_shop:
                updateInfo();
                break;
            case R.id.btn_cancel:
                initView();
                break;
        }
    }
}
