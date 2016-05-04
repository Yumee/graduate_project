package neu.quwanme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import neu.quwanme.common.LoginActivity;
import neu.quwanme.intro.MyIntro1;
import neu.quwanme.intro.MyIntro2;
import neu.quwanme.tools.LogUtil;
import neu.quwanme.tools.PreferencesUtils;

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
       LogUtil.e("hzm", getLocalIpAddress());

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                boolean isFirstStart = true ;//PreferencesUtils.getBoolean("firstStart", true);

                if (isFirstStart) {

                    Intent i = new Intent(WanMainActivity.this, MyIntro2.class);
                    startActivity(i);

                    PreferencesUtils.putBoolean("firstStart", false);

                }
            }
        });

        t.start();

    }

    private String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("hzm", ex.toString());
        }
        return null;
    }
}
