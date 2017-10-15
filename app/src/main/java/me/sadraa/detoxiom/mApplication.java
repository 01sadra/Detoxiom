package me.sadraa.detoxiom;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.res.Configuration;

/**
 * Created by sadra on 10/15/17.
 */

public class mApplication extends Application {
 QuoteDb quoteDb = null;
    @Override
    public void onCreate() {
        super.onCreate();

    }
    public QuoteDb getQuoteDb(){
        quoteDb = Room.databaseBuilder(getApplicationContext(),
                QuoteDb.class, "QuoteDbModel").build();
        return quoteDb;
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
