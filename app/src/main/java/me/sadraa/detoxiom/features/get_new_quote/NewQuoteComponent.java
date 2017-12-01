package me.sadraa.detoxiom.features.get_new_quote;

import dagger.Component;
import me.sadraa.detoxiom.di.CompositeDisposableModule;

/**
 * Created by sadra on 12/1/17.
 */
@Component(modules ={NewQuoteModule.class,
        CompositeDisposableModule.class} )
public interface NewQuoteComponent {

    void inject(NewQuoteFragment fragment);

}
