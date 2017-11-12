package me.sadraa.detoxiom;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class IntroActivity extends AppIntro {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(new IntroFragment(getString(R.string.Intero1), getResources().getDrawable(R.drawable.one_intro)));
        addSlide(new IntroFragment(getString(R.string.Intero2), getResources().getDrawable(R.drawable.two_intro)));
        addSlide(new IntroFragment(getString(R.string.Intero3), getResources().getDrawable(R.drawable.three_intro)));
        addSlide(new IntroFragment(getString(R.string.Intero4), getResources().getDrawable(R.drawable.four_intro)));

        // setFadeAnimation();
        setBarColor(Color.parseColor("#00796B"));
        setProgressButtonEnabled(true);
        setVibrate(true);
        setDoneText("برو بریم");
        setSkipText("بلدم اینارو!");
    }

    @Override
    public void onBackPressed() {
     //   super.onBackPressed();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent mIntent = new Intent(IntroActivity.this,MainActivity.class);
        startActivity(mIntent);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent mIntent = new Intent(IntroActivity.this,MainActivity.class);
        startActivity(mIntent);
    }
}