package neu.quwanme.student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import neu.quwanme.R;
import neu.quwanme.bean.Activity;
import neu.quwanme.framwork.net.NetWorker;
import neu.quwanme.tools.GSONTOOLS;

/**
 * Created by Lonie233 on 2016/4/7.
 * 查看最新活动
 * 操作：点击查看详情，报名参与等
 */
public class LastestAty extends AppCompatActivity {

    @Bind(R.id.list_aty)
    ListView listAty;

    private ArrayList<HashMap<String,Object>> itemList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lastestaty);
        ButterKnife.bind(this);
        initData();
        initListView();
    }

    @OnClick(R.id.list_aty)
    public void onClick() {
    }

    /**
     * 加载活动数据，设置进itemList
     */
    public void initData(){
        String url ="";
        NetWorker.getInstance().get("", new NetWorker.ICallback() {
            @Override
            public void onResponse(int status, String result) {
                if (status == NetWorker.HTTP_OK){
                    Type type = new TypeToken<ArrayList<Activity>>(){}.getType();
                    List<Activity> atyList = GSONTOOLS.getList(result,type);

                    for (int i=0;i<atyList.size();i++){
                        HashMap<String,Object> item = new HashMap<>();
                        item.put("ItemTitle",atyList.get(i).getActivityName());
                        String itemContent = "开始时间："+atyList.get(i).getActivityStartTime()
                                            +" 结束时间："+atyList.get(i).getActivityEndTime()
                                            +" 已报名人数："+atyList.get(i).getActivityCurPeople()
                                            +" 活动地址："+atyList.get(i).getActivityAddr();
                        item.put("ItemContent",atyList.get(i).getActivityStartTime()+itemContent);
                        item.put("ItemImg",R.drawable.aty_icon);
                        itemList.add(item);
                    }
                }
            }
        });
    }
    public void initListView(){

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                                        itemList,
                                        R.layout.list_aty_item_layout,
                                        new String[]{"ItemTitle","ItemContent","ItemImg"},
                                        new int[]{R.id.aty_item_title,R.id.aty_item_content,R.id.aty_item_img});
        listAty.setAdapter(simpleAdapter);
        listAty.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: 2016/4/7 获取点击item的信息，跳转活动详情页
            }
        });
    }

    public class Item{

    }
}
