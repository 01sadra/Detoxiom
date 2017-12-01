package me.sadraa.detoxiom.features.archive_quotes;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.sadraa.detoxiom.MyApplication;
import me.sadraa.detoxiom.data.db.Models.QuoteDbModel;
import me.sadraa.detoxiom.data.db.QuoteDb;

/**
 * Created by sadra on 12/1/17.
 */

public class ArchiveIntractorImpl implements ArchiveContract.Interactor {

    @Inject
    public ArchiveIntractorImpl() {
    }

    @Override
    public List<QuoteDbModel> getAllQuotesFromDataBase() {
        QuoteDb quoteDb = QuoteDb.getQuoteDb(MyApplication.getAppComponent().getContext());
        return quoteDb.quoteDao().getAll();
    }

    @Override
    public ArrayList<QuoteDbModel> convertAllQuoteListToArrayListAndReturn(List<QuoteDbModel> mList) {
        if (mList != null) {
            ArrayList<QuoteDbModel> quoteDbModelList = new ArrayList<>(mList.size());
            //We add object from list to arraylist, from the end to the start to put last quote in the top
            for (int i=mList.size(); i>0 ;i--){
                quoteDbModelList.add(mList.get(i-1));
            }
            return quoteDbModelList;
        }else{
            return null;
        }
    }
}


