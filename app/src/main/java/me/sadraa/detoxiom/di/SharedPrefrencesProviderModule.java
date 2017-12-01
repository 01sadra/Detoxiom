package me.sadraa.detoxiom.di;

import dagger.Module;
import dagger.Provides;
import me.sadraa.detoxiom.data.SharedPreferencesProvider;

/**
 * Created by sadra on 11/25/17.
 */
@Module
public class SharedPrefrencesProviderModule {

    @Provides
    public SharedPreferencesProvider sharedprefrenceProvider(){
        return new SharedPreferencesProvider();
    }
}
