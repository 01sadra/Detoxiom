package me.sadraa.detoxiom;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArchiveFragment extends Fragment {
    TextView authorArchive;
    MyApplication myApplication;
    List<QuoteDbModel> quoteDbModelList;
    List<QuoteDbModel> quoteDbModelList2;
    public ArchiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_archive, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        authorArchive = (TextView) getView().findViewById(R.id.author_archive);
        quoteDbModelList2 = getAllQuotes();
        if(quoteDbModelList2.isEmpty()){

        }else {
            authorArchive.setText(quoteDbModelList2.get(0).getAuthor());

        }


    }
    public List<QuoteDbModel> getAllQuotes(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                quoteDbModelList = myApplication.getQuoteDb().quoteDao().getAll();
            }
        };
        new Thread(runnable).start();
        return quoteDbModelList;
    }
}
