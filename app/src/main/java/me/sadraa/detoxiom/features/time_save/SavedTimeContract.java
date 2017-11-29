package me.sadraa.detoxiom.features.time_save;

import me.sadraa.detoxiom.utils.IBasePresenter;
import me.sadraa.detoxiom.utils.IView;

/**
 * Created by sadra on 11/29/17.
 */

public interface SavedTimeContract {
    interface View extends IView<SavedTimeContract.presenter>{
        void setTextForViews();
    }
    interface presenter extends IBasePresenter<SavedTimeContract.View>{
        int LoadOpenedTimeFromInteractor();
        int LoadFromInteractorRealTimeInSocialMedia(int openedTime, String socialMedia);

    }
}
