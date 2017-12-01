package me.sadraa.detoxiom.features.main_activity;

import javax.inject.Inject;

import me.sadraa.detoxiom.MyApplication;
import me.sadraa.detoxiom.data.SharedPreferencesProvider;

/**
 * Created by sadra on 12/1/17.
 */

public class MainActivityInteractorImpl implements MainActivityContract.Interactor {
    SharedPreferencesProvider sharedPreferencesProvider =
            MyApplication.getAppComponent().getSharedPrefrenceProvider();
    @Inject
    public MainActivityInteractorImpl() {
    }

    @Override
    public int loadOpenedTimeFromProvider() {
        return sharedPreferencesProvider.loadOpenedTimes();
    }

    @Override
    public void saveOpenedTimes(int __) {
        sharedPreferencesProvider.saveOpenedTimes(__);
    }

    @Override
    public int getMeRandomNumberBetween1and5() {
        return MyApplication.getAppComponent().getRandom().nextInt(5)+1;
    }

    @Override
    public void saveBadgeCounterWithProvider(int __) {
        sharedPreferencesProvider.saveBadgeCounter(__);
    }

    @Override
    public int loadbadgeCountFromProvider() {
        return sharedPreferencesProvider.loadBadgeCount();
    }
}
