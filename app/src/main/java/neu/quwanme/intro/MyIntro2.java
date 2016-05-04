package neu.quwanme.intro;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

import neu.quwanme.R;
import neu.quwanme.WanMainActivity;
import neu.quwanme.common.LoginActivity;

/**
 * Created by Lonie233 on 2016/4/27.
 */
public class MyIntro2 extends AppIntro2 {
    @Override
    public void init(@Nullable Bundle savedInstanceState) {

        addSlide(AppIntroFragment.newInstance("引导页1","第一个引导页", R.drawable.ic_slide1,R.color.red));
        addSlide(AppIntroFragment.newInstance("引导页2","第2个引导页", R.drawable.ic_slide2,R.color.yellow));
        addSlide(AppIntroFragment.newInstance("引导页3","第3个引导页", R.drawable.ic_slide3,R.color.white));
        addSlide(AppIntroFragment.newInstance("引导页4","第4个引导页", R.drawable.ic_slide4,R.color.white));

        showStatusBar(true);

        setNavBarColor("#e30c26");

        setVibrate(true);
        setVibrateIntensity(30);

        setFadeAnimation();

    }

    @Override
    public void onDonePressed() {
        startActivity(new Intent(MyIntro2.this, LoginActivity.class));
    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onSlideChanged() {

    }
}
