package me.sadraa.detoxiom.features.teaching;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sadra on 11/29/17.
 */
@Module
class TeachingPresenterModule {

    @Provides
    TeachingContract.Presenter teachingPresenter(TeachingPresenterImpl presenter){
        return presenter;
    }
}
