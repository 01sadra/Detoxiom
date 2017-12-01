package me.sadraa.detoxiom.features.get_new_quote;

import dagger.Component;
import me.sadraa.detoxiom.di.CompositeDisposableModule;
import me.sadraa.detoxiom.di.scopes.NewQuoteFragmentComponentScope;

/**
 * Created by sadra on 12/1/17.
 */
@NewQuoteFragmentComponentScope
@Component(modules ={NewQuoteModule.class,
        CompositeDisposableModule.class} )
public interface NewQuoteComponent {

    void inject(NewQuoteFragment fragment);

}
