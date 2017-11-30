package me.sadraa.detoxiom;

import android.content.SharedPreferences;

import com.google.android.gms.analytics.Tracker;

import java.util.Random;

import dagger.Component;
import me.sadraa.detoxiom.data.SharedprefrenceProvider;
import me.sadraa.detoxiom.data.network.QuoteClient;
import me.sadraa.detoxiom.di.CalligraphyModule;
import me.sadraa.detoxiom.di.MyAppComponentScope;
import me.sadraa.detoxiom.di.NetworkModule;
import me.sadraa.detoxiom.di.RandomModule;
import me.sadraa.detoxiom.di.SharedPrefrencesModule;
import me.sadraa.detoxiom.di.SharedPrefrencesProviderModule;
import me.sadraa.detoxiom.di.TrackerModule;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by sadra on 11/22/17.
 */
@MyAppComponentScope
@Component(modules= {
        NetworkModule.class,
        CalligraphyModule.class,
        SharedPrefrencesModule.class,
        RandomModule.class,
        SharedPrefrencesProviderModule.class,
        TrackerModule.class
})
public interface MyAppComponent {
    QuoteClient getQCService();
    CalligraphyConfig getCalligraphyConfig();
    SharedPreferences getSharedPrefrence();
    Random getRandom();
    SharedprefrenceProvider getSharedPrefrenceProvider();
    Tracker getDefaultTracker();
}
