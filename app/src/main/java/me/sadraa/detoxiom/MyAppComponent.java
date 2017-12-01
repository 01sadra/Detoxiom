package me.sadraa.detoxiom;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Random;

import dagger.Component;
import me.sadraa.detoxiom.data.SharedPreferencesProvider;
import me.sadraa.detoxiom.data.network.QuoteClient;
import me.sadraa.detoxiom.di.CalligraphyModule;
import me.sadraa.detoxiom.di.scopes.MyAppComponentScope;
import me.sadraa.detoxiom.di.NetworkModule;
import me.sadraa.detoxiom.di.RandomModule;
import me.sadraa.detoxiom.di.SharedPrefrencesModule;
import me.sadraa.detoxiom.di.SharedPrefrencesProviderModule;
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
        SharedPrefrencesProviderModule.class
})
public interface MyAppComponent {
    QuoteClient getQCService();
    CalligraphyConfig getCalligraphyConfig();
    SharedPreferences getSharedPrefrence();
    Random getRandom();
    SharedPreferencesProvider getSharedPrefrenceProvider();
    Context getContext();
}
