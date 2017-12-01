package me.sadraa.detoxiom.features.teaching;


import javax.inject.Inject;

/**
 * Created by sadra on 11/29/17.
 */

public class TeachingPresenterImpl implements TeachingContract.Presenter {
    private TeachingContract.View viewLayer;
    @Inject
    public TeachingPresenterImpl() {
    }

    @Override
    public void onViewAttached(TeachingContract.View view) {
        viewLayer=view;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void aboutAcitivtyButtonClicked() {
        viewLayer.callAboutActivity();
    }

    @Override
    public void IntroActivityButtonClicked() {
        viewLayer.callIntroActivity();
    }
}
