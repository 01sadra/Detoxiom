package me.sadraa.detoxiom.features.time_save;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sadra on 11/29/17.
 */
@Module
public class SavedTimeModule {
    @Provides
    SavedTimeContract.Presenter presenter(SavedTimePresenterImpl presenter){
        return presenter;
    }
    @Provides
    SavedTimeContract.Interactor timeSavedInteractor(SavedTimeInteractorImpl timeSavedInteractor){
        return timeSavedInteractor;
    }
}
