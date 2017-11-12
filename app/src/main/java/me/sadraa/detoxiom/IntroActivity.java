package me.sadraa.detoxiom;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance("salam","khodafez",R.drawable.about_icon_facebook, Color.parseColor("#F23FFF")));
        addSlide(AppIntroFragment.newInstance("salam","khodafez",R.drawable.about_icon_facebook, Color.parseColor("#F56FFF")));
        addSlide(AppIntroFragment.newInstance("salam","khodafez",R.drawable.about_icon_facebook, Color.parseColor("#FFF44F")));
        setFadeAnimation();
        showSkipButton(false);
        setProgressButtonEnabled(true);
        setVibrate(true);
    }

    @Override
    public void onBackPressed() {
     //   super.onBackPressed();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent mIntent = new Intent(IntroActivity.this,MainActivity.class);
        startActivity(mIntent);
    }
}