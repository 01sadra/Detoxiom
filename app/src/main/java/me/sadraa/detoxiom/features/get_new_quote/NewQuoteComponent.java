package me.sadraa.detoxiom.features.get_new_quote;

import dagger.Component;

/**
 * Created by sadra on 12/1/17.
 */
@Component(modules = NewQuoteModule.class)
public interface NewQuoteComponent {

    void inject(NewQuoteFragment fragment);

}
