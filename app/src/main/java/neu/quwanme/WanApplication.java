package neu.quwanme;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;

import neu.quwanme.tools.LogUtil;

/**
 * Created by Lonie233 on 2016/3/14.
 */
public class WanApplication extends Application{

    private static Context mcontext;
    protected static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        WanApplication.mcontext = getApplicationContext();
        this.instance = this ;
    }
    public static Context getAppContext() {
        if (mcontext == null) {

            LogUtil.e("hzm","rigoiu ");
        }
            return WanApplication.mcontext ;
    }
    public static Application getInstance(){
        return instance;
    }
    public int getVersionCode() {
        try {
            return getPackageManager().getPackageInfo(getTruePackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return -1;
        }
    }
    public String getTruePackageName() {
        return super.getPackageName();
    }

}
