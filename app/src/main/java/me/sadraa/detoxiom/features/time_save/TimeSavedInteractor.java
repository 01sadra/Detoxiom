package me.sadraa.detoxiom.features.time_save;

/**
 * Created by sadra on 11/29/17.
 */

public interface TimeSavedInteractor {
    int LoadOpenedTimeFromProvider();
    int RealTimeInSocialMedia(int time, String socialMediaName);
}
