package me.sadraa.detoxiom.features.main_activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sadra on 12/1/17.
 */
@Module
public class MainActivityModule {
    @Provides
    MainActivityContract.Presenter presenter(MainActivityPresenterImpl presenter){
        return presenter;
    }
    @Provides
    MainActivityContract.Interactor presnter(MainActivityInteractorImpl interactor){
        return interactor;
    }
}
