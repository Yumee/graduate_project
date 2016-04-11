package neu.quwanme.framwork.net.image;


import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Lonie233 on 2016/4/5.
 */
public class MyImageLoader {

    ImageLoaderConfiguration mImageLoaderConfiguration ;
    ImageLoader mImageLoader;
//    ImageLoader


    public MyImageLoader(Context context) {
        this.mImageLoaderConfiguration = ImageLoaderConfiguration.createDefault(context);
        mImageLoader.getInstance().init(mImageLoaderConfiguration);
    }
}