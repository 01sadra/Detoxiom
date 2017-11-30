package me.sadraa.detoxiom;

import android.app.Application;

import com.google.android.gms.analytics.Tracker;

import me.sadraa.detoxiom.di.SharedPrefrencesModule;
import me.sadraa.detoxiom.di.TrackerModule;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by sadra on 10/28/17.
 */

public class MyApplication extends Application {
    private static MyAppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerMyAppComponent.builder()
                        .sharedPrefrencesModule(new SharedPrefrencesModule(getApplicationContext()))
                        .trackerModule(new TrackerModule(getApplicationContext()))
                        .build();
        //Send Data to google analytics
        Tracker tracker = appComponent.getDefaultTracker();
        tracker.enableAutoActivityTracking(true);
        tracker.enableExceptionReporting(true);

        CalligraphyConfig.initDefault(appComponent.getCalligraphyConfig());

    }


    public static MyAppComponent getAppComponent(){
        return appComponent;
    }

}
