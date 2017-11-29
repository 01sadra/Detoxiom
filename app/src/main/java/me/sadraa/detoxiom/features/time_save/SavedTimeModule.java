package me.sadraa.detoxiom.features.time_save;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sadra on 11/29/17.
 */
@Module
public class SavedTimeModule {
    @Provides
    SavedTimeContract.presenter presenter(SavedTimePresenter presenter){
        return presenter;
    }
    @Provides
    TimeSavedInteractor timeSavedInteractor(TimeSavedInteractorImpl timeSavedInteractor){
        return timeSavedInteractor;
    }
}
