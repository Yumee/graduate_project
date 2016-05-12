package neu.quwanme.tools;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/**
 * Created by 胡泽明 on 2016/5/12.
 * 轮询工具类，负责定时发送消息
 */
public class PullingUtils {
    public static void startPullingService(Context context, int seconds, Class<?> cls, String action){
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context,cls);
        i.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(context,0,i,PendingIntent.FLAG_UPDATE_CURRENT);

        long tiggerAtTime = SystemClock.elapsedRealtime();

        manager.setRepeating(AlarmManager.ELAPSED_REALTIME,tiggerAtTime,seconds*1000,pendingIntent);
    }
    public static void stopPullingService(Context context,Class<?> cls, String action){
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context,cls);
        i.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(context,0,i,PendingIntent.FLAG_UPDATE_CURRENT);

        manager.cancel(pendingIntent);
    }

}
