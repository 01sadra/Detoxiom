package me.sadraa.detoxiom.features.archive_quotes;

import java.util.ArrayList;
import java.util.List;

import me.sadraa.detoxiom.data.db.Models.QuoteDbModel;
import me.sadraa.detoxiom.data.network.models.QuoteModel;
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
    }
    interface Presenter extends IBasePresenter<ArchiveContract.View>{
        ArrayList<QuoteModel> returnAllQuotesFromIntractorInArrayList();
        void deleteQuoteWithIntractor(int position);
    }
    interface Interactor{
        List<QuoteModel> getAllQuotesFromDataBase();
        ArrayList<QuoteModel> convertAllQuoteListToArrayListAndReturn();
        void deleteQuoteFromDataBase(int position);

    }
}
