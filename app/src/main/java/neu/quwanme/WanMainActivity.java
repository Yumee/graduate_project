package neu.quwanme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import neu.quwanme.common.LoginActivity;

public class WanMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wan_main);
        startActivity(new Intent(this, LoginActivity.class));
    }
}
