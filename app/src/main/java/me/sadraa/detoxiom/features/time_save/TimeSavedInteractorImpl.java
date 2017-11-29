package me.sadraa.detoxiom.features.time_save;

import javax.inject.Inject;

import me.sadraa.detoxiom.data.SharedprefrenceProvider;

/**
 * Created by sadra on 11/29/17.
 */

public class TimeSavedInteractorImpl implements TimeSavedInteractor {
    SharedprefrenceProvider sharedprefrenceProvider;

    @Inject
    public TimeSavedInteractorImpl(SharedprefrenceProvider sharedprefrenceProvider) {
        this.sharedprefrenceProvider =sharedprefrenceProvider;
    }

    @Override
    public int LoadOpenedTimeFromProvider() {
        return sharedprefrenceProvider.loadOpenedTimes();
    }

    @Override
    public int RealTimeInSocialMedia(int openedTime, String socialMediaName) {
        switch (socialMediaName){
            case "twitter":
                return openedTime*5;
            case "instagram":
                return openedTime*6;
            case "telegram":
                return openedTime*4;
        }
        return openedTime;
    }

}
