package me.sadraa.detoxiom.features.get_new_quote;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.sadraa.detoxiom.MyApplication;
import me.sadraa.detoxiom.data.SharedPreferencesProvider;
import me.sadraa.detoxiom.data.db.Models.QuoteDbModel;
import me.sadraa.detoxiom.data.db.QuoteDb;
import me.sadraa.detoxiom.data.network.models.QuoteModel;
import me.sadraa.detoxiom.utils.ClientConfig;
import retrofit2.Call;

/**
 * Created by sadra on 12/1/17.
 */

public class NewQuoteInterctorImpl implements NewQuoteContract.Interactor {
    @Inject
    public NewQuoteInterctorImpl() {
    }

    SharedPreferencesProvider sharedPreferences= MyApplication.getAppComponent()
                                                        .getSharedPrefrenceProvider();
    @Override
    public int LoadBadgeCountFromSharedPreferencesProvider() {
        return sharedPreferences.loadBadgeCount();
    }

    @Override
    public void saveBadgeCounterWithSharedPrefrence(int __) {
        sharedPreferences.saveBadgeCounter(__);
    }

    @Override
    public boolean makeChance() {
        int mRandomNumber = MyApplication.getAppComponent().getRandom().nextInt(100) + 1;
        //Just make sure the first attempt is successful. it is necessary for gamifation
        if(sharedPreferences.loadOpenedTimes()<3){
            return true;
        }
        return mRandomNumber%3==0;
    }

    @Override
    public Observable<QuoteModel> getQuoteObservable() {
        /* Call method and run it asynchronously :) */
        Call<QuoteModel> call = MyApplication.getAppComponent().getQCService().getQuote(ClientConfig.api_token);
        return Observable.fromCallable(() -> call.execute().body());
    }

    @Override
    public void saveQuoteToDb(QuoteDbModel __) {
        //Creating a new thread for Runnig Room Query.
        Runnable runnable = () -> {
            QuoteDb quoteDb = QuoteDb.getQuoteDb(MyApplication.getAppComponent().getContext());
            quoteDb.quoteDao().insertOne(__);
        };
        new Thread(runnable).start();
    }
}
