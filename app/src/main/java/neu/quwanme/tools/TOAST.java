package neu.quwanme.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Lonie233 on 2016/3/18.
 */
public class TOAST {


    public static void ToastShort(Context context,String msg){
        Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();
    }
}
