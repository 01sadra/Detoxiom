package me.sadraa.detoxiom.features.main_activity;

import dagger.Module;
import dagger.Provides;
import me.sadraa.detoxiom.di.scopes.MainActivityComponentScope;

/**
 * Created by sadra on 12/1/17.
 */
@Module
public class MainActivityModule {
    @MainActivityComponentScope
    @Provides
    MainActivityContract.Presenter presenter(MainActivityPresenterImpl presenter){
        return presenter;
    }
    @MainActivityComponentScope
    @Provides
    MainActivityContract.Interactor presnter(MainActivityInteractorImpl interactor){
        return interactor;
    }
}
