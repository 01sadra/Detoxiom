package me.sadraa.detoxiom.features.get_new_quote;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sadra on 12/1/17.
 */
@Module
public class NewQuoteModule {
    @Provides
    NewQuoteContract.Presenter presenter(NewQuotePresenterImpl presenter){
        return presenter;
    }
    @Provides
    NewQuoteContract.Interactor interactor(NewQuoteInterctorImpl interactor){
        return interactor;
    }
}
