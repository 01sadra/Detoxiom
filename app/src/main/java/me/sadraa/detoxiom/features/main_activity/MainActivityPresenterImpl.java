package me.sadraa.detoxiom.features.main_activity;

import javax.inject.Inject;

/**
 * Created by sadra on 12/1/17.
 */

public class MainActivityPresenterImpl implements MainActivityContract.Presenter {
    MainActivityContract.Interactor interactor;
    MainActivityContract.View layerView;
    @Inject
    public MainActivityPresenterImpl(MainActivityContract.Interactor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void onViewAttached(MainActivityContract.View view) {
        layerView = view;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public int loadOpenedTimeFromInteractor() {
        return interactor.loadOpenedTimeFromProvider();
    }

    @Override
    public int loadRandomNumberFromInteractor() {
        return interactor.getMeRandomNumberBetween1and5();
    }

    @Override
    public void saveOpenedTimeWithInteractor(int __) {
        interactor.saveOpenedTimes(__);
    }

    @Override
    public void saveBadgeCounterWithInteractor(int __) {
        interactor.saveBadgeCounterWithProvider(__);
    }

    @Override
    public int loadbadgeCountFromInteractor() {
        return interactor.loadbadgeCountFromProvider();
    }
}
