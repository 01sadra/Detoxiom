package me.sadraa.detoxiom.di;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by sadra on 11/25/17.
 */
@Module
public class CompositeDisposableModule {
    @Provides
    CompositeDisposable compositeDisposable(){
        return new CompositeDisposable();
    }
}
