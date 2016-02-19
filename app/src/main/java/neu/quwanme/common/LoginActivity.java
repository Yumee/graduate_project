package neu.quwanme.common;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import neu.quwanme.R;

/**
 * Lonie233 2016年2月19日19:50:31
 */

public class LoginActivity extends Activity implements View.OnClickListener {

    EditText et_username, et_pwd ;
    TextView tv_forgetPwd;
    Button btn_login,btn_regi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = (EditText) findViewById(R.id.et_username);
        et_pwd = (EditText) findViewById(R.id.et_password);

        tv_forgetPwd = (TextView) findViewById(R.id.tv_forgetPwd);
//        设置下划线
        tv_forgetPwd.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_regi = (Button) findViewById(R.id.btn_regi);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                Toast.makeText(this,"点击了登录按钮",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_regi:
                Toast.makeText(this,"点击了注册按钮",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_forgetPwd:
                Toast.makeText(this,"忘记密码",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
