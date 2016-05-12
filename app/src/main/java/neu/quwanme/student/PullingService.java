package neu.quwanme.student;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import neu.quwanme.R;
import neu.quwanme.WanApplication;
import neu.quwanme.bean.Activity;
import neu.quwanme.tools.CONFIG;
import neu.quwanme.tools.LogUtil;

public class PullingService extends Service {

    public static final String ACTION = "com.neu.edu.service.PullingService";

    public static final String BROADCASTACTION = "'com.neu.edu.cn.UPDATENEWATY";//新活动推送action

    private Notification mNotification;
    private NotificationManager mManager;

    private Activity pulledAty;

    public PullingService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        initNotifiManager();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new PollingThread().start();
        return super.onStartCommand(intent, flags, startId);
    }

    //初始化通知栏配置
    private void initNotifiManager() {
        mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        int icon = R.drawable.icon;
//        mNotification = new Notification();
//        mNotification.icon = icon;
//        mNotification.tickerText = "New Message";
//        mNotification.defaults |= Notification.DEFAULT_SOUND;
//        mNotification.flags = Notification.FLAG_AUTO_CANCEL;
    }
    //弹出Notification
    private void showNotification() {
//        mNotification.when = ;
        Intent i = new Intent(this, StuOneAtyDetail.class);
        Bundle b = new Bundle();
        b.putSerializable("aty",pulledAty);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i,
                0);
        Notification.Builder builder= new Notification.Builder(this)
                .setSmallIcon(R.drawable.icon)
                .setContentTitle("去玩么")
                .setContentText("新活动推送")
                .setContentIntent(pendingIntent);

        builder.setTicker("new aty");
//        builder.setNumber(12);
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);
        builder.setWhen(System.currentTimeMillis());

        mManager.notify(0, builder.build());
    }
    /**
     * Polling thread
     * 模拟向Server轮询的异步线程
     * @Author Ryan
     * @Create 2013-7-13 上午10:18:34
     */
    int count = 0;
    class PollingThread extends Thread {
        @Override
        public void run() {
            System.out.println("开始轮询活动推送...");
            count ++;
//            if (count % 5 == 0) {
                showNotification();
                pulledAty = new Activity();
                LogUtil.d(CONFIG.MYTAG,"new push");
                Intent i = new Intent(BROADCASTACTION);
                Bundle b = new Bundle();
                b.putSerializable("aty",pulledAty);
                i.putExtras(b);
                sendBroadcast(i);
//            }
        }
    }
}
