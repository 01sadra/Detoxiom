package me.sadraa.detoxiom.features.teaching;

import me.sadraa.detoxiom.utils.IBasePresenter;
import me.sadraa.detoxiom.utils.IView;

/**
 * Created by sadra on 11/29/17.
 */

public interface TeachingContract {

    interface View extends IView<TeachingContract.Presenter>{
        void callIntroActivity();
        void callAboutActivity();
    }
    interface Presenter extends IBasePresenter<TeachingContract.View>{
        void aboutAcitivtyButtonClicked();
        void IntroActivityButtonClicked();
    }
}
