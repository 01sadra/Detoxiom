package me.sadraa.detoxiom.features.teaching;

import dagger.Component;
import me.sadraa.detoxiom.di.scopes.TeachingFragmentComponentScope;

/**
 * Created by sadra on 11/29/17.
 */
@TeachingFragmentComponentScope
@Component(modules = TeachingPresenterModule.class)
public interface TeachingPresenterComponent {

    void inject(TeachingFragment teachingFragment);
}
