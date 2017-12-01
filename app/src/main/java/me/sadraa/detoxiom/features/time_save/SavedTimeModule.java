package me.sadraa.detoxiom.features.time_save;

import dagger.Module;
import dagger.Provides;
import me.sadraa.detoxiom.di.scopes.SavedTimeFragmentComponentScope;

/**
 * Created by sadra on 11/29/17.
 */
@Module
public class SavedTimeModule {
    @SavedTimeFragmentComponentScope
    @Provides
    SavedTimeContract.Presenter presenter(SavedTimePresenterImpl presenter){
        return presenter;
    }
    @SavedTimeFragmentComponentScope
    @Provides
    SavedTimeContract.Interactor timeSavedInteractor(SavedTimeInteractorImpl timeSavedInteractor){
        return timeSavedInteractor;
    }
}
