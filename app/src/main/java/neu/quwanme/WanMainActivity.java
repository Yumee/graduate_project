package neu.quwanme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import neu.quwanme.common.LoginActivity;

public class WanMainActivity extends AppCompatActivity {

    private Button btn_start ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wan_main);

        btn_start = (Button) findViewById(R.id.btn_start_activity);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WanMainActivity.this, LoginActivity.class));
            }
        });
    }
}
