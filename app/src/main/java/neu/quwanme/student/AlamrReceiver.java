package neu.quwanme.student;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by 胡泽明 on 2016/5/12.
 */
public class AlamrReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "闹钟时间到", Toast.LENGTH_LONG).show();
    }
}
