package me.sadraa.detoxiom.features.get_new_quote;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.sadraa.detoxiom.data.db.Models.QuoteDbModel;
import me.sadraa.detoxiom.data.network.models.QuoteModel;

/**
 * Created by sadra on 12/1/17.
 */

public class NewQuotePresenterImpl implements NewQuoteContract.Presenter {
    private NewQuoteContract.View viewLayer;
    NewQuoteContract.Interactor interactor;

    @Inject
    public NewQuotePresenterImpl(NewQuoteContract.Interactor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void onViewAttached(NewQuoteContract.View view) {
        viewLayer=view;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public int LoadBadgeCountFromInteractor() {
        return interactor.LoadBadgeCountFromSharedPreferencesProvider();
    }

    @Override
    public void saveBadgeCounterWithInteractor(int __) {
        interactor.saveBadgeCounterWithSharedPrefrence(__);
    }

    @Override
    public boolean makeChancefromInteractor() {
        return interactor.makeChance();
    }

    @Override
    public Observable<QuoteModel> getQuoteObservableFromInteractor() {
      return interactor.getQuoteObservable();
    }

    @Override
    public void passQuotetoInteractorForSaving(QuoteDbModel quoteDbModel) {
        interactor.saveQuoteToDb(quoteDbModel);
    }
}
