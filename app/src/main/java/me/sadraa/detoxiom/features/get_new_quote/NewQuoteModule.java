package me.sadraa.detoxiom.features.get_new_quote;

import dagger.Module;
import dagger.Provides;
import me.sadraa.detoxiom.di.scopes.NewQuoteFragmentComponentScope;

/**
 * Created by sadra on 12/1/17.
 */
@Module
public class NewQuoteModule {
    @NewQuoteFragmentComponentScope
    @Provides
    NewQuoteContract.Presenter presenter(NewQuotePresenterImpl presenter){
        return presenter;
    }
    @NewQuoteFragmentComponentScope
    @Provides
    NewQuoteContract.Interactor interactor(NewQuoteInterctorImpl interactor){
        return interactor;
    }
}
