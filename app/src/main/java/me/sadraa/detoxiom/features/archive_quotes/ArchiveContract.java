package me.sadraa.detoxiom.features.archive_quotes;

import java.util.ArrayList;
import java.util.List;

import me.sadraa.detoxiom.data.db.Models.QuoteDbModel;
import me.sadraa.detoxiom.utils.IBasePresenter;
import me.sadraa.detoxiom.utils.IView;

/**
 * Created by sadra on 12/1/17.
 */

public interface ArchiveContract {
    interface View extends IView<ArchiveContract.Presenter>{
        void populateListView(ArrayList<QuoteDbModel> q);
        void showWhaleInsteadOfRView();
        void setAdapterAndShowRView();
        void deleteQuote(int postion);
    }
    interface Presenter extends IBasePresenter<ArchiveContract.View>{
        ArrayList<QuoteDbModel> returnAllQuotesFromIntractorInArrayList();
    }
    interface Interactor{
        List<QuoteDbModel> getAllQuotesFromDataBase();
        ArrayList<QuoteDbModel> convertAllQuoteListToArrayListAndReturn(List<QuoteDbModel> Q);

    }
}
