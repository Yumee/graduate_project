package neu.quwanme.common;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

import neu.quwanme.CONFIG.OfficalUrl;
import neu.quwanme.R;
import neu.quwanme.bean.User;
import neu.quwanme.framwork.net.NetWorker;
import neu.quwanme.tools.GSONTOOLS;
import neu.quwanme.tools.LogUtil;
import neu.quwanme.tools.TOAST;
import neu.quwanme.tools.UrlParseTool;

/**
 * Lonie233 2016年2月19日19:50:31
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_username, et_pwd ;
    TextView tv_forgetPwd;
    Button btn_login,btn_regi,btnShopRegi;
    RadioButton chooseUser,chooseShop;
    private Context mContxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContxt = this;
        initView();
    }

    public void initView() {
        setContentView(R.layout.activity_login);

        et_username = (EditText) findViewById(R.id.et_username);
        et_pwd = (EditText) findViewById(R.id.et_password);

        chooseUser = (RadioButton) findViewById(R.id.user_type_normal);
        chooseShop = (RadioButton) findViewById(R.id.user_type_shop);

        tv_forgetPwd = (TextView) findViewById(R.id.tv_forgetPwd);
//        设置下划线
        tv_forgetPwd.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_regi = (Button) findViewById(R.id.btn_regi);
        btnShopRegi = (Button) findViewById(R.id.btn_shop_regi);

//        设置监听器
        tv_forgetPwd.setOnClickListener(this);
        btn_regi.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btnShopRegi.setOnClickListener(this);

//        initData();

    }
    public void initData() {
        final String url = OfficalUrl.testUrl;

        NetWorker.getInstance().get(url, new NetWorker.ICallback() {
            @Override
            public void onResponse(int status, String result) {
                if (status == NetWorker.HTTP_OK){
                    Log.d("hzm","result "+result);
                    Map<String,User> user = GSONTOOLS.getMap(result,new TypeToken<Map<String,User>>(){}.getType());
                    Log.d("hzm", "map "+user.get("user").getClass());
                    btn_login.setText(user.get("user").getUserRealName());

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (checked()) {
                    loginProcess();
                } else {
                    et_pwd.setText("请重新输入密码");
                }
                break;
            case R.id.btn_regi:
                Toast.makeText(this,"点击了学生注册按钮",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,RegisterActivity.class).putExtra("regi_usertype","student"));
                break;
            case R.id.btn_shop_regi:
                Toast.makeText(this,"点击了学生注册按钮",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,RegisterActivity.class).putExtra("regi_usertype","shop"));
                break;
            case R.id.tv_forgetPwd:
                Toast.makeText(this,"忘记密码",Toast.LENGTH_SHORT).show();
                break;
        }
    }
    public boolean loginProcess(){
        String username = et_username.getText().toString();
        String password = et_pwd.getText().toString();

        Map<String,String > params = new HashMap<String, String>() ;

        params.put("userId",username);
        params.put("userPassword",password);

        String url = UrlParseTool.parseUrl(OfficalUrl.baseUrl,OfficalUrl.UserLoginUrl);
        NetWorker.getInstance().get(UrlParseTool.parseParam(url, params), new NetWorker.ICallback() {
            @Override
            public void onResponse(int status, String result) {
               Map<String,Integer> res = GSONTOOLS.getBean(result,Map.class);
                LogUtil.d("hzm", result+" "+res.toString());
            }
        });
        return true ;
    }

    /**
     * 检测是否输入应有的登陆信息
     * @return
     */
    public boolean checked() {

        if (et_username.getText().equals(null)){
            TOAST.ToastShort(mContxt,"请输入用户名");
        } else if (et_pwd.getText().equals(null)){
            TOAST.ToastShort(mContxt,"请输入密码");
        } else if (!chooseShop.isChecked()&&!chooseUser.isChecked()){
            TOAST.ToastShort(mContxt,"请选择身份");
        } else {
            return true;
        }
        return false;
    }
}
