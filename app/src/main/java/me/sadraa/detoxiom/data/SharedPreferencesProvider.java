package me.sadraa.detoxiom.data;

import android.content.SharedPreferences;

import me.sadraa.detoxiom.MyApplication;

public class SharedPreferencesProvider {
    //We want to use shared prefrences for counting how many time application will open by use
    //for this goal we define 2 String. 1 for prefrence name and one as a key for prefrence
    private static final String PREFRENCE_KEY_OPENED_TIMES = "opened times key";
    private static final String PREFRENCE_KEY_BADGE_COUNT = "badge count key";

    //We save prefrence in this method.
    public void saveOpenedTimes(int counterOpenedTimes){
        SharedPreferences.Editor mPrefrences = MyApplication.getAppComponent().getSharedPrefrence().edit();
        mPrefrences.putInt(PREFRENCE_KEY_OPENED_TIMES,counterOpenedTimes);
        mPrefrences.apply();
    }

    public void saveBadgeCounter(int badgeCount){
        SharedPreferences.Editor mPrefrences = MyApplication.getAppComponent().getSharedPrefrence().edit();
        mPrefrences.putInt(PREFRENCE_KEY_BADGE_COUNT,badgeCount);
        mPrefrences.apply();
    }

    //we load saved prefrence by this method. It defined static because we want to call it in saved time fragment.
    public  int loadOpenedTimes(){
        SharedPreferences loadpreferencesOpenTime = MyApplication.getAppComponent().getSharedPrefrence();
        int openTimes = loadpreferencesOpenTime.getInt(PREFRENCE_KEY_OPENED_TIMES,0);
        return openTimes;
    }

    public int loadBadgeCount(){
        SharedPreferences loadpreferencesOpenTime = MyApplication.getAppComponent().getSharedPrefrence();
        int badgeCoounter = loadpreferencesOpenTime.getInt(PREFRENCE_KEY_BADGE_COUNT,0);
        return badgeCoounter;
    }

}