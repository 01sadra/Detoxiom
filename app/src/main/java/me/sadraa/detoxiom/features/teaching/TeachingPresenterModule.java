package me.sadraa.detoxiom.features.teaching;

import dagger.Module;
import dagger.Provides;
import me.sadraa.detoxiom.di.scopes.TeachingFragmentComponentScope;

/**
 * Created by sadra on 11/29/17.
 */
@Module
class TeachingPresenterModule {
    @TeachingFragmentComponentScope
    @Provides
    TeachingContract.Presenter teachingPresenter(TeachingPresenterImpl presenter){
        return presenter;
    }
}
