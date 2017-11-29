package me.sadraa.detoxiom.utils;

/**
 * Created by sadra on 11/29/17.
 */

public interface IBasePresenter<T> {

    void onViewAttached(T view);

    void subscribe();

    void unsubscribe();


}
