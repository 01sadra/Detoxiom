package me.sadraa.detoxiom.features.main_activity;

import dagger.Component;

/**
 * Created by sadra on 12/1/17.
 */
@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject (MainActivity mainActivity);
}
