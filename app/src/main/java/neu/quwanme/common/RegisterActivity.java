package neu.quwanme.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import neu.quwanme.CONFIG.OfficalUrl;
import neu.quwanme.CONFIG.Status_Code;
import neu.quwanme.R;
import neu.quwanme.bean.User;
import neu.quwanme.framwork.net.NetWorker;
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
    @Bind(R.id.user_name)
    TextView userName;
    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.password)
    TextView password;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.et_nickname)
    EditText etNickname;
    @Bind(R.id.et_number)
    EditText etNumber;
    @Bind(R.id.age)
    TextView age;
    @Bind(R.id.et_age)
    EditText etAge;
    @Bind(R.id.user_sex_girl)
    RadioButton userSexGirl;
    @Bind(R.id.user_sex_boy)
    RadioButton userSexBoy;
    @Bind(R.id.ly_number)
    LinearLayout lyNumber;
    @Bind(R.id.ly_age)
    LinearLayout lyAge;
    @Bind(R.id.shop_name)
    TextView shopName;
    @Bind(R.id.et_shopname)
    EditText etShopname;
    @Bind(R.id.shop_city)
    TextView shopCity;
    @Bind(R.id.et_shopcity)
    EditText etShopcity;
    @Bind(R.id.btn_student_regi)
    Button btnStudentRegi;
    @Bind(R.id.btn_student_login)
    Button btnStudentLogin;
    @Bind(R.id.btn_shop_regi)
    Button btnShopRegi;
    @Bind(R.id.btn_shop_login)
    Button btnShopLogin;



    public boolean IsStudent = false;//true means user/ false means shop
    private String userSex = "";

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
        } else {
            rlStudent.setVisibility(View.GONE);
            rlStudentBtn.setVisibility(View.GONE);
            rlShop.setVisibility(View.VISIBLE);
            rlShopBtn.setVisibility(View.VISIBLE);
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
                    LogUtil.d("hzm","用户信息不完整");
                    TOAST.ToastShort(this, "用户信息不完整!");
                }
                break;
            case R.id.btn_student_login:
                break;
            case R.id.btn_shop_regi:
                if (checked()) {
                    registeUser();
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
            if (etUsername.getText() == null || etPassword == null || etNickname == null || etNumber == null || etAge == null) {
                return false;
            }
            //检测选择项
            if ((!userSexBoy.isChecked() && !userSexGirl.isChecked())) {
                return false;
            }
        } else {
            if (etShopcity == null || etShopname == null){
                return false;
            }
        }
            return true;
    }

    public void registeUser() {
        String url = "";

        Map<String, String> params = new HashMap<>();
        params.clear();
        if (IsStudent) {
            url = OfficalUrl.StudentResgistUrl;
            params.put("userNumber", etNumber.getText().toString());
            params.put("userRealName", etNumber.getText().toString());
            params.put("userNickName", etNumber.getText().toString());
            params.put("userPassword", etNumber.getText().toString());
            params.put("userAge", etNumber.getText().toString());
            params.put("userSex", userSex);

        } else {
            url = OfficalUrl.ShopResgistUrl;

            params.put("shopName", etShopname.getText().toString());
            params.put("shopCity", etShopcity.getText().toString());
//            params.put("shopName", "");

        }
        url = UrlParseTool.parseUrl(OfficalUrl.baseUrl, url);

        NetWorker.getInstance().get(UrlParseTool.parseParam(url, params), new NetWorker.ICallback() {
            @Override
            public void onResponse(int status, String result) {
                if (status == NetWorker.HTTP_OK){
                    Map<String,Integer> map = GSONTOOLS.getMap(result,Map.class);
                    // TODO: 2016/3/21 再重新请求用户信息放入本地，然后跳转到首页
                    LogUtil.d("hzm"," 创建 用户返回值 "+map.get(Status_Code.Status_COde));
                }
            }
        });

    }


}