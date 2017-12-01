package me.sadraa.detoxiom.features.time_save;

import javax.inject.Inject;

/**
 * Created by sadra on 11/29/17.
 */

public class SavedTimePresenterImpl implements SavedTimeContract.Presenter {
    private SavedTimeContract.View viewLayer;
    SavedTimeContract.Interactor timeSavedInteractor;

    @Inject
    public SavedTimePresenterImpl(SavedTimeContract.Interactor timeSavedInteractor) {
        this.timeSavedInteractor=timeSavedInteractor;
    }

    @Override
    public void onViewAttached(SavedTimeContract.View view) {
        viewLayer=view;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public int LoadOpenedTimeFromInteractor() {
        return timeSavedInteractor.LoadOpenedTimeFromProvider();
    }

    @Override
    public int LoadFromInteractorRealTimeInSocialMedia(int openedTime, String socialMedia) {
        return timeSavedInteractor.RealTimeInSocialMedia(openedTime,socialMedia);
    }
}
