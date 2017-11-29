package me.sadraa.detoxiom.features.teaching;

import dagger.Component;

/**
 * Created by sadra on 11/29/17.
 */
@Component(modules = TeachingPresenterModule.class)
public interface TeachingPresenterComponent {

    void inject(TeachingFragment teachingFragment);
}
