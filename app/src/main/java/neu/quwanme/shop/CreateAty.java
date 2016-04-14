package neu.quwanme.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import neu.quwanme.CONFIG.OfficalUrl;
import neu.quwanme.CONFIG.Symbols;
import neu.quwanme.R;
import neu.quwanme.bean.City;
import neu.quwanme.framwork.net.NetWorker;
import neu.quwanme.tools.DateTimePickDialogUtil;
import neu.quwanme.tools.GSONTOOLS;
import neu.quwanme.tools.LogUtil;
import neu.quwanme.tools.PreferencesUtils;
import neu.quwanme.tools.UrlParseTool;

/**
 * Created by Lonie233 on 2016/4/12.
 */
public class CreateAty extends AppCompatActivity {


    @Bind(R.id.et_atyname)
    EditText etAtyname;
    @Bind(R.id.et_atyAddr)
    EditText etAtyAddr;
    @Bind(R.id.et_atyMaxPopu)
    EditText etAtyMaxPopu;
    @Bind(R.id.et_atyMinPopu)
    EditText etAtyMinPopu;
    @Bind(R.id.et_starttime)
    EditText etStarttime;
    @Bind(R.id.et_endtime)
    EditText etEndtime;
    @Bind(R.id.city_list)
    Spinner cityList;
    @Bind(R.id.type_list)
    Spinner typeList;
    @Bind(R.id.ly_atyMinPopu)
    LinearLayout lyAtyMinPopu;
    private List<String> typeName;
    private List<String> cityname;
    private ArrayAdapter<String> arr_adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_aty);
        ButterKnife.bind(this);
        initView();
    }

    public void initData() {

    }

    public void initView() {
        getCityList();
        getTypeList();
        final Date d = new Date();
        etStarttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePickDialogUtil dateTimePickDialog = new DateTimePickDialogUtil(CreateAty.this, d.getYear()+"年"+d.getMonth()+"月"+d.getDay()+"日 "+d.getHours()+":"+d.getMinutes());
                dateTimePickDialog.dateTimePicKDialog(etStarttime);
            }
        });
        etEndtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePickDialogUtil dateTimePickDialog = new DateTimePickDialogUtil(CreateAty.this, d.getYear()+"年"+d.getMonth()+"月"+d.getDay()+"日 "+d.getHours()+":"+d.getMinutes());
                dateTimePickDialog.dateTimePicKDialog(etEndtime);
            }
        });
    }

    public void getCityList() {
        cityname = new ArrayList<>();
        cityname.add("请选择城市");
        try {

            NetWorker.getInstance().get(UrlParseTool.parseUrl(OfficalUrl.baseUrl, OfficalUrl.CityListUrl), new NetWorker.ICallback() {
                @Override
                public void onResponse(int status, String result) {
                    if (status == NetWorker.HTTP_OK) {
                        Type type = new TypeToken<ArrayList<City>>() {
                        }.getType();

                        List<City> cityList = GSONTOOLS.getList(result, type);

                        for (City c : cityList) {
                            cityname.add(c.getCityName());
                        }
                        PreferencesUtils.putString(Symbols.citylist,result);
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        arr_adapter = new ArrayAdapter<>(this, R.layout.spinner_item, cityname);
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityList.setAdapter(arr_adapter);
        cityList.setPrompt("请选择城市");

    }

    public void getTypeList() {
        typeName = new ArrayList<>();
        typeName.add("请选择活动类型");
        try {
            NetWorker.getInstance().get(UrlParseTool.parseUrl(OfficalUrl.baseUrl, OfficalUrl.AtyBaseUrl + OfficalUrl.TypeListUrl), new NetWorker.ICallback() {
                @Override
                public void onResponse(int status, String result) {
                    if (status == NetWorker.HTTP_OK) {
                        Type type = new TypeToken<ArrayList<neu.quwanme.bean.Type>>() {
                        }.getType();

                        List<neu.quwanme.bean.Type> typeList = GSONTOOLS.getList(result, type);

                        for (neu.quwanme.bean.Type c : typeList) {
                            typeName.add(c.getTypeName());
                        }
                        PreferencesUtils.putString(Symbols.typelist,result);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        arr_adapter = new ArrayAdapter<>(this, R.layout.spinner_item, typeName);
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeList.setAdapter(arr_adapter);
    }

    public boolean checkInput() {
//        if (etAtyname.getText().equals("")
//                || etAtyAddr.getText().equals("")
//                || etAtyMaxPopu.getText().toString().equals("")
//                || etAtyMinPopu.getText().toString().equals("")
//                || etStarttime.getText().toString().equals("")
//                || etEndtime.getText().toString().equals("")
//                || cityList.getSelectedItem().toString().equals("请选择城市")
//                || typeList.getSelectedItem() .equals("请选择活动类型")) {
//            return false;
//        }
//        if (Integer.parseInt(etAtyMaxPopu.getText().toString())<Integer.parseInt(etAtyMinPopu.getText().toString())) {
//            return false;
//        }
        return true;
    }

    @OnClick(R.id.btn_create_aty)
    public void onClick() {
        if (checkInput()) {
            String cityName = cityList.getSelectedItem().toString();
            String typeName = typeList.getSelectedItem().toString();
            Map<String, String> params = new HashMap<>();
            params.put("activityName", URLEncoder.encode(etAtyname.getText().toString()));
            params.put("activityAddr", URLEncoder.encode(etAtyAddr.getText().toString()));
            params.put("startTime", URLEncoder.encode(etStarttime.getText().toString().replace("年","-").replace("月","-").replace("日","")));
            params.put("endTime", URLEncoder.encode(etEndtime.getText().toString().replace("年","-").replace("月","-").replace("日","")));
            params.put("activityCurPeople", "0");
            params.put("activityMaxPopu", etAtyMaxPopu.getText().toString());
            params.put("activiityMinPopu", etAtyMinPopu.getText().toString());
            params.put("cityname", URLEncoder.encode(cityName));
            params.put("typename", URLEncoder.encode(typeName));
            params.put("shopId", PreferencesUtils.getString(Symbols.shopId));

            String url = UrlParseTool.parseUrl(OfficalUrl.baseUrl, OfficalUrl.AtyBaseUrl + OfficalUrl.CreateAtyUrl);

            NetWorker.getInstance().get(UrlParseTool.parseParam(url,params), new NetWorker.ICallback() {
                @Override
                public void onResponse(int status, String result) {
                    if (status == NetWorker.HTTP_OK){
                        // TODO: 2016/4/14 跳转全部活动列表
                        Intent i = new Intent(CreateAty.this,ActivityMain.class);
                        i.putExtra(Symbols.ListType,Symbols.AllAty);
                        startActivity(i);
                    }
                }
            });

        } else {
            Toast.makeText(CreateAty.this, "活动信息不完整！", Toast.LENGTH_SHORT).show();
        }
    }


}
