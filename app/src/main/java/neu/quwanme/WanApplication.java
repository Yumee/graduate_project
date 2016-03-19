package neu.quwanme;

import android.app.Application;
import android.content.Context;

import neu.quwanme.tools.LogUtil;

/**
 * Created by Lonie233 on 2016/3/14.
 */
public class WanApplication extends Application{

    private static Context mcontext;

    @Override
    public void onCreate() {
        super.onCreate();
        WanApplication.mcontext = getApplicationContext();
    }
    public static Context getAppContext() {
        if (mcontext == null) {

            LogUtil.e("hzm","rigoiu ");
        }
            return WanApplication.mcontext ;
    }
}
