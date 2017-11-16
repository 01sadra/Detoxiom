package me.sadraa.detoxiom;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by sadra on 10/28/17.
 */

public class mApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Vazir-Medium.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}
