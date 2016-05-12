package neu.quwanme.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import neu.quwanme.CONFIG.OfficalUrl;
import neu.quwanme.CONFIG.Symbols;
import neu.quwanme.R;
import neu.quwanme.bean.Activity;
import neu.quwanme.framwork.net.NetWorker;
import neu.quwanme.tools.GSONTOOLS;
import neu.quwanme.tools.LogUtil;
import neu.quwanme.tools.PreferencesUtils;
import neu.quwanme.tools.UrlParseTool;
import neu.quwanme.view.CommonListView;

/**
 * Created by Lonie233 on 2016/4/11.
 * 我的活动列表
 */
public class ActivityMain extends AppCompatActivity {


    @Bind(R.id.aty_list)
    LinearLayout atyList;
    boolean IsFromOtherAty  = false ;

    public CommonListView commonListView;
    int fromWhere =  0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty_main);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    public void initView() {

        commonListView = new CommonListView(this);
        commonListView.setClickCallBack(new CommonListView.ClickCallBack() {

            @Override
            public void onItemClick(Activity aty) {
                // TODO: 2016/4/14 跳转商品详情页,直接将aty传过去，进行删，改操作
                Intent i = new Intent(ActivityMain.this,OneAtyDetail.class);
                Bundle b = new Bundle();
                LogUtil.d("hzm","tart aty "+aty.toString());
                b.putSerializable("aty",aty);
                i.putExtras(b);
                i.putExtra(Symbols.IsFromOtherAty,IsFromOtherAty);
//                startActivity(i);
                startActivityForResult(i,fromWhere);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (0 == fromWhere) {
            getAtyList(OfficalUrl.GetAllActivity,1);
        } else if (1 == fromWhere) {
            getAtyList(OfficalUrl.GetLastestActivity, 1);
        } else if (2 == fromWhere) {
            getAtyList(OfficalUrl.GetOrganizingActivity, 1);
        } else if (3 == fromWhere){
            getAtyList(OfficalUrl.GetgetOrganizedOverActivity, 1);
        }else  if (4 == fromWhere){
            getAtyList(OfficalUrl.GetFinishActivity, 1);
        }else {

        }
    }

    public void initData() {

        Intent i = this.getIntent();
        int type = i.getIntExtra(Symbols.ListType, Symbols.AllAty);
        IsFromOtherAty = i.getBooleanExtra(Symbols.IsFromOtherAty,false);
        if (!IsFromOtherAty) {//来自我的活动
            switch (type) {
                case Symbols.AllAty:
                    getAtyList(OfficalUrl.GetAllActivity, 1);
                    break;
                case Symbols.Ing:
                    getAtyList(OfficalUrl.GetLastestActivity, 1);
                    break;
                case Symbols.OrgIng:
                    getAtyList(OfficalUrl.GetOrganizingActivity, 1);
                    break;
                case Symbols.Orged:
                    getAtyList(OfficalUrl.GetgetOrganizedOverActivity, 1);
                    break;
                case Symbols.Finish:
                    getAtyList(OfficalUrl.GetFinishActivity, 1);
                    break;
            }
        }else {//来自其他活动，获取其他shop发起的活动
            switch (type) {
                case Symbols.AllAty:
                    getAtyList(OfficalUrl.GetAllActivityNotMe, 1);
                    break;
                case Symbols.Ing:
                    getAtyList(OfficalUrl.GetLastestActivityNotMe, 1);
                    break;
                case Symbols.OrgIng:
                    getAtyList(OfficalUrl.GetOrganizingActivityNotMe, 1);
                    break;
                case Symbols.Orged:
                    getAtyList(OfficalUrl.GetgetOrganizedOverActivityNotMe, 1);
                    break;
                case Symbols.Finish:
                    getAtyList(OfficalUrl.GetFinishActivityNotMe, 1);
                    break;
            }
        }

    }

    @OnClick({R.id.tv_aty_all, R.id.tv_aty_organize_ing,R.id.tv_aty_ing, R.id.tv_aty_organize_over, R.id.tv_aty_finish, R.id.ll_create_new_aty})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_aty_all:
                fromWhere =  0;
                getAtyList(OfficalUrl.GetAllActivity,1);
                break;
            case R.id.tv_aty_ing:
                fromWhere =  1;
                getAtyList(OfficalUrl.GetLastestActivity, 1);
                break;
            case R.id.tv_aty_organize_ing:
                fromWhere =  2;
                getAtyList(OfficalUrl.GetOrganizingActivity, 1);
                break;
            case R.id.tv_aty_organize_over:
                fromWhere =  3;
                getAtyList(OfficalUrl.GetgetOrganizedOverActivity, 1);
                break;
            case R.id.tv_aty_finish:
                fromWhere =  4;
                getAtyList(OfficalUrl.GetFinishActivity, 1);
                break;
            case R.id.ll_create_new_aty:
                startActivity(new Intent(this, CreateAty.class));
                break;
        }
    }

    /**
     * 根据点击按钮筛选获取不同的atylist
     *
     * @param url     要获取的list类型
     * @param pageNum 翻页参数
     */
    public void getAtyList(String url, int pageNum) {

        url = UrlParseTool.parseUrl(OfficalUrl.baseUrl, UrlParseTool.parseUrl(OfficalUrl.AtyBaseUrl, url));

        HashMap<String, String> params = new HashMap<>();

        params.put(Symbols.shopId, PreferencesUtils.getString(Symbols.shopId));
        params.put(Symbols.startPos, 8 * (pageNum - 1) + "");
        params.put(Symbols.endPos, 8 * pageNum + "");

        NetWorker.getInstance().get(UrlParseTool.parseParam(url, params), new NetWorker.ICallback() {
            @Override
            public void onResponse(int status, String result) {
                // TODO: 2016/4/14 设置adaptor，显示列表
                if (status == NetWorker.HTTP_OK) {
                    LogUtil.d("hzm", result.toString());
                    Type type = new TypeToken<List<Activity>>() {
                    }.getType();

                    List<Activity> list = GSONTOOLS.getList(result, type);

                    commonListView.setData(list);
                    atyList.removeAllViews();
                    atyList.addView(commonListView);
                }
            }
        });

    }

}
