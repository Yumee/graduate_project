package neu.quwanme.framwork.net;

import android.os.Process;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import neu.quwanme.tools.LogUtil;

/**
 * Created by Lonie233 on 2016/3/11.
 */
public class NetWorker {
    public static final int HTTP_OK = 200 ;
    public static final int HTTP_404 = 404 ;
    public static final int HTTP_500 = 500 ;
    public static final int NATIVE_ERROR = 600;
    public static final int UNKNOWN_HOST = 601;
    public static final int SOCKET_TIMEOUT = 602;

    public static  NetWorker instance = new NetWorker();
    public static NetWorkerVolleyImpl netWorkerVolleyImpl = new NetWorkerVolleyImpl();
    private ExecutorService threadPool;

    private NetWorker() {
        LogUtil.d("hzm", ">>>>>>>>>>>> init networker");
        // fixed thread pool
        threadPool = Executors.newFixedThreadPool(5, new ThreadFactory() {
            int i = 0;

            @Override
            public Thread newThread(Runnable r) {
                Thread mThread = new Thread(r, "NetworkWorker " + i++);
                mThread.setPriority(Thread.MIN_PRIORITY);
//                mThread.setPriority(5);
                Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
                return mThread;
            }
        });
    }


    public static NetWorker getInstance() {
        return instance;
    }

    /**
     * get请求
     * @param url 访问地址
     * @param callback 回调接口
     * @param params 附加参数
     */
    public void get(final String url,final ICallback callback,final  Object... params){
        netWorkerVolleyImpl.get(url,callback,params);
    }

    /**
     * post请求
     * @param url
     * @param callback
     * @param params
     */
    public void post(final String url,final ICallback callback,final  Object... params){
        netWorkerVolleyImpl.post(url,callback,params);
    }

//  网络访问后回调的接口，处理访问结果
    public interface ICallback{
        public void onResponse(int status, String result);
    }
}
