package neu.quwanme.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import neu.quwanme.CONFIG.OfficalUrl;
import neu.quwanme.CONFIG.Symbols;
import neu.quwanme.R;
import neu.quwanme.bean.Shop;
import neu.quwanme.framwork.net.NetWorker;
import neu.quwanme.tools.GSONTOOLS;
import neu.quwanme.tools.LogUtil;
import neu.quwanme.tools.UrlParseTool;
import neu.quwanme.view.ShopCommonListView;

/**
 * Created by Lonie233 on 2016/4/15.
 */
public class ShopRank extends AppCompatActivity {

    public ShopCommonListView shopCommonListView;
    @Bind(R.id.ll_shopranklist)
    LinearLayout llShopranklist;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoprank);
        ButterKnife.bind(this);
        shopCommonListView = new ShopCommonListView(this);
        shopCommonListView.setClickCallBack(new ShopCommonListView.ClickCallBack() {
            @Override
            public void onItemClick(Shop aty) {
                Intent i = new Intent(ShopRank.this, ShopDetail.class);
                Bundle b = new Bundle();
                b.putSerializable(Symbols.shop, aty);
                i.putExtras(b);
                i.putExtra(Symbols.from,Symbols.fromShopRank);
                startActivity(i);
            }
        });
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    public void initData() {
        NetWorker.getInstance().get(UrlParseTool.parseUrl(OfficalUrl.baseUrl,OfficalUrl.getShopByRank), new NetWorker.ICallback() {
            @Override
            public void onResponse(int status, String result) {
                if (status == NetWorker.HTTP_OK) {
                    LogUtil.d("hzm", result.toString());
                    Type type = new TypeToken<List<Shop>>() {
                    }.getType();

                    List<Shop> list = GSONTOOLS.getList(result, type);

                    shopCommonListView.setData(list);
                    llShopranklist.removeAllViews();
                    llShopranklist.addView(shopCommonListView);
                }
            }
        });
    }

}
