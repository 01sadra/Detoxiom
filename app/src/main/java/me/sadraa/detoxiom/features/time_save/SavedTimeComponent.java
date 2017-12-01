package me.sadraa.detoxiom.features.time_save;

import dagger.Component;
import me.sadraa.detoxiom.di.scopes.SavedTimeFragmentComponentScope;

/**
 * Created by sadra on 11/29/17.
 */
@SavedTimeFragmentComponentScope
@Component(modules = SavedTimeModule.class)
public interface SavedTimeComponent {
    void inject(SavedTimeFragment savedTimeFragment);
}
