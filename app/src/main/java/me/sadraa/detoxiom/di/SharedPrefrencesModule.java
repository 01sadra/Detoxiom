package me.sadraa.detoxiom.di;

import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sadra on 11/24/17.
 */
@Module
public class SharedPrefrencesModule {
    private Context context;

    public SharedPrefrencesModule(Context context){
        this.context=context;
    }

    @Provides
    public SharedPreferences sharedPreferences(){
      return context.getSharedPreferences("prefName",0);
    }

    @Provides
    public Context mContext(){
        return context;
    }


}
