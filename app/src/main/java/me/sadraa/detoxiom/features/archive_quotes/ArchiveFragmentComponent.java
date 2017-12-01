package me.sadraa.detoxiom.features.archive_quotes;

import dagger.Component;
import me.sadraa.detoxiom.di.ArchiveFragmentComponentScope;
import me.sadraa.detoxiom.di.ArchivedependencyModule;
import me.sadraa.detoxiom.di.CompositeDisposableModule;

/**
 * Created by sadra on 11/25/17.
 */
@ArchiveFragmentComponentScope
@Component(modules = {ArchivedependencyModule.class,
        CompositeDisposableModule.class,
})
public interface ArchiveFragmentComponent {

    void injectFragment(ArchiveFragment archiveFragment);

}
