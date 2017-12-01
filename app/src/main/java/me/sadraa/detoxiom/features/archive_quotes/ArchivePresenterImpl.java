package me.sadraa.detoxiom.features.archive_quotes;

import java.util.ArrayList;

import javax.inject.Inject;

import me.sadraa.detoxiom.data.db.Models.QuoteDbModel;

/**
 * Created by sadra on 12/1/17.
 */

public class ArchivePresenterImpl implements ArchiveContract.Presenter {
    ArchiveContract.View layerView;
    ArchiveContract.Interactor interactor;

    @Inject
    public ArchivePresenterImpl(ArchiveContract.Interactor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void onViewAttached(ArchiveContract.View view) {
        layerView=view;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public ArrayList<QuoteDbModel> returnAllQuotesFromIntractorInArrayList() {
        return interactor.convertAllQuoteListToArrayListAndReturn(interactor.getAllQuotesFromDataBase());
    }
}
