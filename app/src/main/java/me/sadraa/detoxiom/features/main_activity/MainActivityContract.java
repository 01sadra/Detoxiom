package me.sadraa.detoxiom.features.main_activity;

import me.sadraa.detoxiom.utils.IBasePresenter;
import me.sadraa.detoxiom.utils.IView;

/**
 * Created by sadra on 12/1/17.
 */

public interface MainActivityContract {
    interface View extends IView<MainActivityContract.Presenter>{
        void setActionBarAndLayoutDirection();
        void setupTheBottomBar();
        void showIntro();
        void showTabTargetView();
        void countingOpenedTime();
        int loadOpenedTimeFromPresenter();
        void saveOpenedTimeWithPresenter(int __);
        int loadRandomNumberFromPresenter();
        void saveBadgeCounterWithPresenter(int __);
        int loadBadgeCountFromPresenter();

    }
    interface Presenter extends IBasePresenter<MainActivityContract.View>{
        int loadOpenedTimeFromInteractor();
        int loadRandomNumberFromInteractor();
        void saveOpenedTimeWithInteractor(int __);
        void saveBadgeCounterWithInteractor(int __);
        int loadbadgeCountFromInteractor();

    }
    interface Interactor {
        int loadOpenedTimeFromProvider();
        void saveOpenedTimes(int __);
        int getMeRandomNumberBetween1and5();
        void saveBadgeCounterWithProvider(int __);
        int loadbadgeCountFromProvider();

    }
}
