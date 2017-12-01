package me.sadraa.detoxiom.features.main_activity;

import dagger.Component;
import me.sadraa.detoxiom.di.scopes.MainActivityComponentScope;

/**
 * Created by sadra on 12/1/17.
 */
@MainActivityComponentScope
@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject (MainActivity mainActivity);
}
