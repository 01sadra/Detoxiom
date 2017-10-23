package me.sadraa.detoxiom;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;


public class MainActivity extends AppCompatActivity {
    FragmentTransaction ft;

    int openedTimes;
    public static final String PREFRENCE_NAME_OPENED_TIMES = "opened times";
    private static final String PREFRENCE_KEY_OPENED_TIMES = "opened times key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openedTimes = loadOpenedTimes(getApplicationContext());
        saveOpenedTimes(getApplicationContext(),openedTimes);

        //Set toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Detoxiom");


        //Define listener for bottomBar
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        //Set click listener for bottombar tabs
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {

            @Override
            public void onTabSelected(@IdRes int tabId) {
              //using switch to woke fragments up
                switch (tabId){
                    case R.id.tab_archive :
                        ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.contentContainer,new ArchiveFragment());
                        break;
                    case R.id.tab_time:
                        ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.contentContainer,new SavedTimeFragment());
                        break;
                    case R.id.tab_new :
                        ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.contentContainer,new NewQuoteFragment());
                        break;
                    case R.id.tab_setting :
                        ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.contentContainer,new SettingFragment());
                        break;
                }
                ft.commit();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        openedTimes = loadOpenedTimes(this);
        saveOpenedTimes(getApplicationContext(),++openedTimes);
    }

    public void saveOpenedTimes(Context context,int counter){
        SharedPreferences.Editor countOpenTimes = context.getSharedPreferences(PREFRENCE_NAME_OPENED_TIMES,0).edit();
        countOpenTimes.putInt(PREFRENCE_KEY_OPENED_TIMES,counter);
        countOpenTimes.apply();
    }
    static int loadOpenedTimes(Context context){
        SharedPreferences loadpreferencesOpenTime = context.getSharedPreferences(PREFRENCE_NAME_OPENED_TIMES,0);
        int openTimes = loadpreferencesOpenTime.getInt(PREFRENCE_KEY_OPENED_TIMES,0);
        return openTimes;
    }
}

