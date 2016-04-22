package neu.quwanme.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import neu.quwanme.R;
import neu.quwanme.bean.Activity;
import neu.quwanme.bean.Shop;

/**
 * Created by Lonie233 on 2016/4/14.
 */
public class ShopCommonListView extends RelativeLayout {

    ListView list;
    private Context mContext;
    public ClickCallBack mClickCallBack;
    public List<Shop> data;

    public ShopCommonListView(Context context) {
        super(context);
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.common_listview, this);
        list = (ListView) findViewById(R.id.list);
    }

    public void setData(final List<Shop> data) {
        if(data.isEmpty()){
            Toast.makeText(mContext, "列表无数据", Toast.LENGTH_SHORT).show();
        }
        this.data = new ArrayList<>();
        this.data = data;
        ArrayList<HashMap<String,Object>> listItem = new ArrayList<>();
        for (Shop s :data){
            HashMap<String,Object> item = new HashMap<>();
            item.put("ShopName",s.getShopName());
            item.put("ShopAddr","地址："+s.getShopCity());
            item.put("ShopImg",R.drawable.shop);
            listItem.add(item);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(mContext,listItem,R.layout.list_aty_item_layout,new String[]{
           "ShopName","ShopAddr","ShopImg"
        },new int[]{
           R.id.aty_item_title,R.id.aty_item_content,R.id.aty_item_img
        });

        list.setAdapter(simpleAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mClickCallBack.onItemClick(data.get(position));
            }
        });
    }
    public void clearData(){

    }
    public interface ClickCallBack{
        public void onItemClick(Shop aty);
    }

    public ClickCallBack getClickCallBack() {
        return mClickCallBack;
    }

    public void setClickCallBack(ClickCallBack clickCallBack) {
        this.mClickCallBack = clickCallBack;
    }
}
