package me.sadraa.detoxiom.features.get_new_quote;

import io.reactivex.Observable;
import me.sadraa.detoxiom.data.db.Models.QuoteDbModel;
import me.sadraa.detoxiom.data.network.models.QuoteModel;
import me.sadraa.detoxiom.utils.IBasePresenter;
import me.sadraa.detoxiom.utils.IView;

/**
 * Created by sadra on 12/1/17.
 */

public interface NewQuoteContract {
    interface View extends IView<NewQuoteContract.Presenter>{
        boolean makeChanceFromPresenter();
        void passQuoteToPresenter(QuoteDbModel __);
        Observable<QuoteModel> getQuoteObservableFromPresenter();
        void setQuoteToBottomSheet(Observable<QuoteModel> __);
        void setChanceCounterText(int __);
        void initiateTheScreenTextAndAnimation();
    }

    interface Presenter extends IBasePresenter<NewQuoteContract.View>{
        int LoadBadgeCountFromInteractor();
        void saveBadgeCounterWithInteractor(int __);
        boolean makeChancefromInteractor();
        Observable<QuoteModel> getQuoteObservableFromInteractor();
        void passQuotetoInteractorForSaving(QuoteDbModel quoteDbModel);

    }

    interface Interactor{
        int LoadBadgeCountFromSharedPreferencesProvider();
        void saveBadgeCounterWithSharedPrefrence(int __);
        boolean makeChance();
        Observable<QuoteModel> getQuoteObservable();
        void saveQuoteToDb(QuoteDbModel __);

    }
}
