package neu.quwanme.intro;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import neu.quwanme.R;

/**
 * Created by Lonie233 on 2016/4/27.
 */
public class MyIntro1 extends AppIntro {
    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        addSlide(AppIntroFragment.newInstance("引导页1","第一个引导页", R.drawable.ic_slide1,R.color.new_home_head_line_color));
        addSlide(AppIntroFragment.newInstance("引导页2","第2个引导页", R.drawable.ic_slide2,R.color.yellow));
        addSlide(AppIntroFragment.newInstance("引导页3","第3个引导页", R.drawable.ic_slide3,R.color.red));
        addSlide(AppIntroFragment.newInstance("引导页4","第4个引导页", R.drawable.ic_slide4,R.color.white));

        setBarColor(getResources().getColor(R.color.red));
        setSeparatorColor(getResources().getColor(R.color.yellow));

        showStatusBar(true);

//        setNavBarColor(Color.parseColor("#3F51B5"));

        showSkipButton(false);
        setProgressButtonEnabled(false);


        setVibrate(true);
        setVibrateIntensity(30);

        askForPermissions(new String[]{Manifest.permission.CAMERA}, 4);

    }

    @Override
    public void onSkipPressed() {

    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onDonePressed() {

    }

    @Override
    public void onSlideChanged() {

    }
}
