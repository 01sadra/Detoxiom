package me.sadraa.detoxiom;

import android.app.Application;

import me.sadraa.detoxiom.di.SharedPrefrencesModule;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class MyApplication extends Application {
    private static MyAppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerMyAppComponent.builder()
                        .sharedPrefrencesModule(new SharedPrefrencesModule(getApplicationContext()))
                        .build();


        CalligraphyConfig.initDefault(appComponent.getCalligraphyConfig());

    }


    public static MyAppComponent getAppComponent(){
        return appComponent;
    }

}
