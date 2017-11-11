package me.sadraa.detoxiom;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.Random;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends AppCompatActivity {
    FragmentTransaction ft;
    int openedTimes;
    int badgeCount;

    //We want to use shared prefrences for counting how many time application will open by use
    //for this goal we define 2 String. 1 for prefrence name and one as a key for prefrence
    public static final String PREFRENCE_NAME = "my pref";
    private static final String PREFRENCE_KEY_OPENED_TIMES = "opened times key";
    private static final String PREFRENCE_KEY_BADGE_COUNT = "badge count key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //everyTime onCreate fire we +1 shared prefrences counter
        badgeCount=getMeRandomNumber();
        openedTimes = loadOpenedTimes(getApplicationContext());
        saveOpenedTimes(getApplicationContext(),++openedTimes);
        saveBadgeCounter(this,badgeCount);


        //Set toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Detoxiom");
        getSupportActionBar().setElevation(8);

        //Define listener for bottomBar
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        final BottomBarTab tabNew = bottomBar.getTabWithId(R.id.tab_new);


        tabNew.setBadgeCount(loadBadgeCount(this));
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
                        tabNew.removeBadge();
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
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    //We save prefrence in this method.
    public void saveOpenedTimes(Context context,int counterOpenedTimes){
        SharedPreferences.Editor mPrefrences = context.getSharedPreferences(PREFRENCE_NAME,0).edit();
        mPrefrences.putInt(PREFRENCE_KEY_OPENED_TIMES,counterOpenedTimes);
        mPrefrences.apply();
    }
    public static void saveBadgeCounter(Context context,int badgeCount){
        SharedPreferences.Editor mPrefrences = context.getSharedPreferences(PREFRENCE_NAME,0).edit();
        mPrefrences.putInt(PREFRENCE_KEY_BADGE_COUNT,badgeCount);
        mPrefrences.apply();
    }
    //we load saved prefrence by this method. It defined static because we want to call it in saved time fragment.
    static int loadOpenedTimes(Context context){
        SharedPreferences loadpreferencesOpenTime = context.getSharedPreferences(PREFRENCE_NAME,0);
        int openTimes = loadpreferencesOpenTime.getInt(PREFRENCE_KEY_OPENED_TIMES,0);
        return openTimes;
    }
    static int loadBadgeCount(Context context){
        SharedPreferences loadpreferencesOpenTime = context.getSharedPreferences(PREFRENCE_NAME,0);
        int badgeCoounter = loadpreferencesOpenTime.getInt(PREFRENCE_KEY_BADGE_COUNT,0);
        return badgeCoounter;
    }
    public int getMeRandomNumber(){
        Random r = new Random();
        return r.nextInt(5)+1;
    }
}

