package me.sadraa.detoxiom.features.time_save;

import dagger.Component;

/**
 * Created by sadra on 11/29/17.
 */
@Component(modules = SavedTimeModule.class)
public interface SavedTimeComponent {
    void inject(SavedTimeFragment savedTimeFragment);
}
