package neu.quwanme.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import neu.quwanme.CONFIG.OfficalUrl;
import neu.quwanme.CONFIG.Status_Code;
import neu.quwanme.R;
import neu.quwanme.bean.City;
import neu.quwanme.bean.School;
import neu.quwanme.bean.ShopActivity;
import neu.quwanme.framwork.net.NetWorker;
import neu.quwanme.shop.ShopMainActivity;
import neu.quwanme.student.StudentMainActivity;
import neu.quwanme.tools.CONFIG;
import neu.quwanme.tools.GSONTOOLS;
import neu.quwanme.tools.LogUtil;
import neu.quwanme.tools.TOAST;
import neu.quwanme.tools.UrlParseTool;

/**
 * Created by Lonie233 on 2016/3/21.
 */
public class RegisterActivity extends AppCompatActivity {

    @Bind(R.id.rl_student)
    RelativeLayout rlStudent;
    @Bind(R.id.rl_shop)
    RelativeLayout rlShop;
    @Bind(R.id.rl_studentbtn)
    RelativeLayout rlStudentBtn;
    @Bind(R.id.rl_shop_btn)
    RelativeLayout rlShopBtn;

    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.et_nickname)
    EditText etNickname;
    @Bind(R.id.et_number)
    EditText etNumber;
    @Bind(R.id.et_age)
    EditText etAge;
    @Bind(R.id.user_sex_girl)
    RadioButton userSexGirl;
    @Bind(R.id.user_sex_boy)
    RadioButton userSexBoy;


    @Bind(R.id.et_shopname)
    EditText etShopname;


    @Bind(R.id.school_list)
    Spinner schoolList;
    @Bind(R.id.city_list)
    Spinner cityList;


    public boolean IsStudent = false;//true means user/ false means shop
    private String userSex = "";
    private List<String> schoolName;
    private List<String> cityname;
    private ArrayAdapter<String> arr_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {

        //设置年龄，学号默认吊起数字键盘

        etAge.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        etNumber.setInputType(EditorInfo.TYPE_CLASS_PHONE);

        IsStudent = getIntent().getStringExtra("regi_usertype").equals("student");
        if (IsStudent) {
            rlShop.setVisibility(View.GONE);
            rlShopBtn.setVisibility(View.GONE);
            rlStudent.setVisibility(View.VISIBLE);
            rlStudentBtn.setVisibility(View.VISIBLE);
            getSchool();
        } else {
            rlStudent.setVisibility(View.GONE);
            rlStudentBtn.setVisibility(View.GONE);
            rlShop.setVisibility(View.VISIBLE);
            rlShopBtn.setVisibility(View.VISIBLE);
            getAllCity();
        }

    }

    @OnClick({R.id.user_sex_girl, R.id.user_sex_boy, R.id.btn_student_regi,
            R.id.btn_student_login, R.id.btn_shop_regi, R.id.btn_shop_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_sex_girl:
                userSex = "f";
                break;
            case R.id.user_sex_boy:
                userSex = "m";
                break;
            case R.id.user_type_normal:
                IsStudent = true;
                break;
            case R.id.user_type_shop:
                IsStudent = false;
                break;
            case R.id.btn_regi:

                break;
            case R.id.btn_login:
                break;
            case R.id.btn_student_regi:
                if (checked()) {
                    registeUser();
                } else {
                    LogUtil.d("hzm", "用户信息不完整");
                    TOAST.ToastShort(this, "用户信息不完整!");
                }
                break;
            case R.id.btn_student_login:
                break;
            case R.id.btn_shop_regi:
                if (checked()) {
                    registeShop();
                } else {
                    TOAST.ToastShort(this, "商家信息不完整!");
                }
                break;
            case R.id.btn_shop_login:
                break;
        }
    }

    /**
     * 检测输入是否合法
     *
     * @return
     */
    public boolean checked() {
        if (IsStudent) {
            //检测输入
            if (schoolList.getSelectedItem().toString().equals("")||etUsername.getText() == null || etPassword == null || etNickname == null || etNumber == null || etAge == null) {
                return false;
            }
            //检测选择项
            if ((!userSexBoy.isChecked() && !userSexGirl.isChecked())) {
                return false;
            }
        } else {
            if (cityList.getSelectedItem().toString().equals("") ||etShopname == null) {
                return false;
            }
        }
        return true;
    }

    public void registeUser() {
        String url = "";

        Map<String, String> params = new HashMap<>();
        params.put("userNumber", etNumber.getText().toString());
        params.put("userPassword", etPassword.getText().toString());
        params.put("userAge", etAge.getText().toString());
        try {
            params.put("userRealName", URLEncoder.encode(etUsername.getText().toString()));
            params.put("userNickName", URLEncoder.encode(etNickname.getText().toString()));
            params.put("schoolName", URLEncoder.encode(schoolList.getSelectedItem().toString(), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        params.put("userSex", userSex);

        url = UrlParseTool.parseUrl(OfficalUrl.baseUrl,OfficalUrl.StudentResgistUrl);

        try {
            NetWorker.getInstance().get(UrlParseTool.parseParam(url, params), new NetWorker.ICallback() {
                @Override
                public void onResponse(int status, String result) {
                    if (status == NetWorker.HTTP_OK){
                        Type type = new TypeToken<Map<String,Object>>(){}.getType();
                        Map<String,Object> map = GSONTOOLS.getMap(result,type);
                        LogUtil.d("hzm",map.get(Status_Code.Status_Code).toString());
                        startActivity(new Intent(RegisterActivity.this, StudentMainActivity.class));
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 展示可选择的学校列表
     */
    public void getSchool() {
        // TODO: 2016/3/29 获取全部的学校，以列表的形式展现出来
        schoolName = new ArrayList<>();
        schoolName.add("请选择学校");
        try {
            NetWorker.getInstance().get(UrlParseTool.parseUrl(OfficalUrl.baseUrl, OfficalUrl.SchoolListUrl), new NetWorker.ICallback() {
                @Override
                public void onResponse(int status, String result) {
                    if (status == NetWorker.HTTP_OK) {
                        Type type = new TypeToken<ArrayList<School>>() {
                        }.getType();
                        List<School> schools = GSONTOOLS.getList(result, type);
                        LogUtil.d("hzm", "schools " + schools.toString());
                        if (!schools.isEmpty()) {
                            Iterator it = schools.iterator();
                            schoolName.clear();
                            while (it.hasNext()) {
                                schoolName.add(((School) it.next()).getSchoolName());
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "城市列表为空，请检查服务器数据库", Toast.LENGTH_SHORT).show();
                        }
                    }else if (status == NetWorker.HTTP_500){
                        Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception e) {
            Toast.makeText(RegisterActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
        }

        arr_adapter = new ArrayAdapter<>(this, R.layout.spinner_item, schoolName);
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        schoolList.setAdapter(arr_adapter);
        schoolList.setPrompt("请选择您的学校");

    }

    public void registeShop() {
        Map<String, String> params = new HashMap<>();

        try{
            params.put("shopName",URLEncoder.encode(etShopname.getText().toString()));
            params.put("cityName",URLEncoder.encode(cityList.getSelectedItem().toString()));
            String url = UrlParseTool.parseUrl(OfficalUrl.baseUrl, OfficalUrl.ShopResgistUrl) ;
            NetWorker.getInstance().get(UrlParseTool.parseParam(url,params), new NetWorker.ICallback() {
                @Override
                public void onResponse(int status, String result) {
                    if (status == NetWorker.HTTP_OK){
                        Type type = new TypeToken<Map<String,Object>>(){}.getType();
                        
                        Map<String,Object> res = GSONTOOLS.getMap(result,type);

                        LogUtil.d("hzm",res.get(Status_Code.Status_Code)+" "+Status_Code.SUCCESS_STATUS);
                        if (((double)res.get(Status_Code.Status_Code)) == Status_Code.SUCCESS_STATUS){
                            // TODO: 2016/4/8 注册成功，返回shopId，shop实例
                            // TODO: 2016/4/8 跳转商家首页
                            Toast.makeText(RegisterActivity.this, "商家注册成功", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, ShopMainActivity.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, "商家注册失败", Toast.LENGTH_SHORT).show();
                        }
                        
                        
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void getAllCity() {
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
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        arr_adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, cityname);
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityList.setAdapter(arr_adapter);
        cityList.setPrompt("请选择城市");

    }

}
