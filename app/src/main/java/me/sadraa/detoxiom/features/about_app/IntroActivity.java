package me.sadraa.detoxiom.features.about_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.github.paolorotolo.appintro.AppIntro;

import me.sadraa.detoxiom.R;
import me.sadraa.detoxiom.features.MainActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class IntroActivity extends AppIntro {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }

        //Adding custom fragments to activity with custom atrributes.
        addSlide(new IntroFragment(getString(R.string.Intero1), getResources().getDrawable(R.drawable.one_intro)));
        addSlide(new IntroFragment(getString(R.string.Intero2), getResources().getDrawable(R.drawable.two_intro)));
        addSlide(new IntroFragment(getString(R.string.Intero3), getResources().getDrawable(R.drawable.three_intro)));
        addSlide(new IntroFragment(getString(R.string.Intero4), getResources().getDrawable(R.drawable.four_intro)));
        addSlide(new IntroFragment(getString(R.string.Intero5), getResources().getDrawable(R.drawable.five_intro)));

        setFadeAnimation();
        //set bottom bar color
        setBarColor(Color.parseColor("#00796B"));
        setProgressButtonEnabled(true);
        setVibrate(true);
        setDoneText("برو بریم");
        setSkipText("بلدم اینارو!");
    }

    @Override
    public void onBackPressed() {
       super.onBackPressed();
    }

    //open the main activity if skip pressed
    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent mIntent = new Intent(IntroActivity.this,MainActivity.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(mIntent);
    }
    //open the main activity if done pressed
    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent mIntent = new Intent(IntroActivity.this,MainActivity.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(mIntent);
    }
}