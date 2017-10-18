package me.sadraa.detoxiom;


import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArchiveFragment extends Fragment {
    TextView authorArchive;
    List<QuoteDbModel> quoteDbModelList;
    QuoteDb quoteDb;
    RecyclerView rv;
    RVAdapter rvAdapter;

    public ArchiveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        List<QuoteDbModel> quoteDbModelListRV;

        QuoteDbModel quoteDbModel = new QuoteDbModel();
        QuoteDbModel quoteDbModel1 = new QuoteDbModel();
        quoteDbModel.setAuthor("سلام");
        quoteDbModel.setQuote("بیسشتنمبتیسمنکشبسیتمکنیتسشبشیکنمتبنش");
        quoteDbModel1.setAuthor("اودافظ");
        quoteDbModel1.setQuote("fjdsak;ljfdaskljdsalkjsakl;fdjsajfdska");

        quoteDbModelListRV.add(0,quoteDbModel);
        quoteDbModelListRV.add(1,quoteDbModel1);
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_archive, container, false);
        rv = (RecyclerView) rootView.findViewById(R.id.archive_rv);
       // Toast.makeText(getContext(),quoteDbModelListRV.get(0).getAuthor(),Toast.LENGTH_LONG).show();
        Log.d("myTag",quoteDbModelListRV.get(0).getAuthor());
        rvAdapter = new RVAdapter(quoteDbModelListRV);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(mLayoutManager);
        // rv.setItemAnimator(new DefaultItemAnimator());

        rv.setAdapter(rvAdapter);
        rvAdapter.notifyDataSetChanged();
        return rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
     //   quoteDbModelListRV = getAllQuotes();


    }
    public List<QuoteDbModel> getAllQuotes(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                quoteDb = QuoteDb.getQuoteDb(getContext());
                quoteDbModelList = quoteDb.quoteDao().getAll();

            }
        };
        new Thread(runnable).start();
        if(quoteDbModelList!=null){
            Toast.makeText(getContext(),quoteDbModelList.get(0).getAuthor(),Toast.LENGTH_LONG).show();
        }
        return quoteDbModelList;
    }
}
