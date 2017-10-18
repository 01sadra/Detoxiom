package me.sadraa.detoxiom;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.res.Configuration;

/**
 * Created by sadra on 10/15/17.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
