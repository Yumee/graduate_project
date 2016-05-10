package neu.quwanme.student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.internal.ObjectConstructor;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import neu.quwanme.CONFIG.OfficalUrl;
import neu.quwanme.CONFIG.Status_Code;
import neu.quwanme.CONFIG.Symbols;
import neu.quwanme.R;
import neu.quwanme.framwork.net.NetWorker;
import neu.quwanme.tools.GSONTOOLS;
import neu.quwanme.tools.PreferencesUtils;
import neu.quwanme.tools.StringUtils;
import neu.quwanme.tools.UrlParseTool;

/**
 * Created by 胡泽明 on 2016/5/10.
 */
public class StudentDetail extends AppCompatActivity {

    @Bind(R.id.et_user_name)
    EditText etUserName;
    @Bind(R.id.et_user_pwd)
    EditText etUserPwd;
    @Bind(R.id.et_user_Addr)
    EditText etUserAddr;
    @Bind(R.id.et_user_nickname)
    EditText etUserNickname;
    @Bind(R.id.et_user_number)
    EditText etUserNumber;
    @Bind(R.id.btn_update_user)
    Button btnUpdateUser;
    @Bind(R.id.btn_cancel)
    Button btnCancel;
    @Bind(R.id.et_user_age)
    EditText etUserAge;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studetail);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        etUserName.setText(PreferencesUtils.getString(Symbols.userRealName));
        etUserNickname.setText(PreferencesUtils.getString(Symbols.userNickName));
//        etUserAddr.setText(PreferencesUtils.getString(Symbols.));
        etUserPwd.setText(PreferencesUtils.getString(Symbols.userPassword));
        etUserNumber.setText(PreferencesUtils.getString(Symbols.userNumber).substring(0,PreferencesUtils.getString(Symbols.userNumber).length()-2));
        etUserAge.setText(PreferencesUtils.getString(Symbols.userAge).substring(0,PreferencesUtils.getString(Symbols.userAge).length()-2));
    }

    public void updateInfo() {
        String url = OfficalUrl.baseUrl + OfficalUrl.UserBaseUrl + OfficalUrl.UserUpdataInfo ;
        Map<String,String> params = new HashMap<>();
        params.put(Symbols.userId,PreferencesUtils.getString(Symbols.userId));
        params.put(Symbols.userRealName,etUserName.getText().toString());
        params.put(Symbols.userNickName,etUserNickname.getText().toString());
        params.put(Symbols.userAge,etUserAge.getText().toString().substring(0,etUserAge.getText().toString().length()-2));
        params.put(Symbols.userNumber,etUserNumber.getText().toString().substring(0,etUserAge.getText().toString().length()-2));
//        params.put(Symbols.cityName,etUserAddr.getText().toString());

        NetWorker.getInstance().get(UrlParseTool.parseParam(url, params), new NetWorker.ICallback() {
            @Override
            public void onResponse(int status, String result) {
                if (status == NetWorker.HTTP_OK){
                    Type type = new TypeToken<Map<String,Integer>>(){}.getType();
                    Map<String,Integer> res = GSONTOOLS.getMap(result,type);
                    if (res.get(Status_Code.Status_Code) == Status_Code.SUCCESS_STATUS){
                        Toast.makeText(StudentDetail.this, "信息更新成功", Toast.LENGTH_SHORT).show();
                        PreferencesUtils.putString(Symbols.userRealName,etUserName.getText().toString());
                        PreferencesUtils.putString(Symbols.userNickName,etUserNickname.getText().toString());
                        PreferencesUtils.putString(Symbols.userAge,etUserAge.getText().toString());
                        PreferencesUtils.putString(Symbols.userNumber,etUserNumber.getText().toString());
                        PreferencesUtils.putString(Symbols.userPassword,etUserPwd.getText().toString());
                    }
                }
            }
        });


    }

    @OnClick({R.id.btn_update_user, R.id.btn_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_update_user:
                updateInfo();
                break;
            case R.id.btn_cancel:
                //取消修改返回上级界面
                onBackPressed();
                break;
        }
    }
}
