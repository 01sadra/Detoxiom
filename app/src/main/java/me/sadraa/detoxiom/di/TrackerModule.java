package me.sadraa.detoxiom.di;

import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import dagger.Module;
import dagger.Provides;
import me.sadraa.detoxiom.R;

/**
 * Created by sadra on 11/30/17.
 */
@Module
public class TrackerModule {
    Context context;

    public TrackerModule(Context context) {
        this.context = context;
    }
    @MyAppComponentScope
    @Provides
    public GoogleAnalytics analytics(){
        return GoogleAnalytics.getInstance(context);
   }
   @MyAppComponentScope
   @Provides
    synchronized public Tracker getDefaultTracker(GoogleAnalytics analytics) {

       return analytics.newTracker(R.xml.global_tracker);

    }
}
