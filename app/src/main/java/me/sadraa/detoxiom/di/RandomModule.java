package me.sadraa.detoxiom.di;

import java.util.Random;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sadra on 11/24/17.
 */
@Module
public class RandomModule {
    @Provides
    public Random gerRandon(){
        return new Random();
    }
}
