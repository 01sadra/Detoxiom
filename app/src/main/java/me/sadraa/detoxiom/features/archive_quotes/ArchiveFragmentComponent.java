package me.sadraa.detoxiom.features.archive_quotes;

import dagger.Component;
import me.sadraa.detoxiom.di.ArchiveFragmentComponentScope;
import me.sadraa.detoxiom.di.CompositeDisposableModule;
import me.sadraa.detoxiom.di.RVAdapterModule;

/**
 * Created by sadra on 11/25/17.
 */
@ArchiveFragmentComponentScope
@Component(modules = {RVAdapterModule.class,
        CompositeDisposableModule.class})
public interface ArchiveFragmentComponent {

    void injectFragment(ArchiveFragment archiveFragment);

}
