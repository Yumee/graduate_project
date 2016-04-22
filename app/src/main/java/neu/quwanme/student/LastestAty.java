package neu.quwanme.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import neu.quwanme.CONFIG.OfficalUrl;
import neu.quwanme.CONFIG.Symbols;
import neu.quwanme.R;
import neu.quwanme.bean.Activity;
import neu.quwanme.framwork.net.NetWorker;
import neu.quwanme.shop.OneAtyDetail;
import neu.quwanme.tools.GSONTOOLS;
import neu.quwanme.tools.LogUtil;
import neu.quwanme.tools.PreferencesUtils;
import neu.quwanme.tools.UrlParseTool;
import neu.quwanme.view.CommonListView;

/**
 * Created by Lonie233 on 2016/4/7.
 * 查看最新活动
 * 操作：点击查看详情，报名参与等
 */
public class LastestAty extends AppCompatActivity {


    @Bind(R.id.tv_latest_aty)
    TextView tvLatestAty;
    @Bind(R.id.tv_my_aty)
    TextView tvMyAty;
    @Bind(R.id.tv_my_deal)
    TextView tvMyDeal;
    @Bind(R.id.tv_history)
    TextView tvHistory;
    @Bind(R.id.aty_list)
    LinearLayout atyList;
    private ArrayList<HashMap<String, Object>> itemList = new ArrayList<>();
    public CommonListView commonListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lastestaty);
        ButterKnife.bind(this);
        commonListView = new CommonListView(this);
        initView();
        initData(OfficalUrl.GetUserLaestAty);
    }

    public void initView(){
        commonListView.setClickCallBack(new CommonListView.ClickCallBack() {
            @Override
            public void onItemClick(Activity aty) {
                // TODO: 2016/4/19 点击列表项启动详情页
                Intent i = new Intent(LastestAty.this,StuOneAtyDetail.class);
                Bundle b = new Bundle();
                LogUtil.d("hzm","tart aty "+aty.toString());
                b.putSerializable("aty",aty);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }
    /**
     * 加载活动数据，设置进itemList
     */
    public void initData(String urlStr) {
        String url = OfficalUrl.baseUrl + OfficalUrl.AtyBaseUrl + urlStr;
        Map<String, String> params = new HashMap<>();
        params.put(Symbols.userId, PreferencesUtils.getString(Symbols.userId));
        params.put(Symbols.startPos,0+"");
        params.put(Symbols.endPos,"8");
        NetWorker.getInstance().get(UrlParseTool.parseParam(url, params), new NetWorker.ICallback() {
            @Override
            public void onResponse(int status, String result) {
                if (status == NetWorker.HTTP_OK) {
                    Type type = new TypeToken<ArrayList<Activity>>() {
                    }.getType();
                    List<Activity> list = GSONTOOLS.getList(result, type);
                    if(list.size()>0){
                        commonListView.setData(list);
                        atyList.removeAllViews();
                        atyList.addView(commonListView);
                    }
                }
            }
        });
    }


    @OnClick({R.id.tv_latest_aty, R.id.tv_my_aty, R.id.tv_my_deal, R.id.tv_history})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_latest_aty:
                initData(OfficalUrl.GetUserLaestAty);
                break;
            case R.id.tv_my_aty:
                break;
            case R.id.tv_my_deal:
                break;
            case R.id.tv_history:
                break;
        }
    }

}
