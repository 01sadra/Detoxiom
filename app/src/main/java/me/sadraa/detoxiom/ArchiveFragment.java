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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArchiveFragment extends Fragment {
    TextView authorArchive;
    ArrayList<QuoteDbModel> quoteDbModelList;
    ArrayList<QuoteDbModel> quoteDbModelListRV;
    QuoteDb quoteDb;
    RecyclerView rv;
    RVAdapter rvAdapter;
    List<QuoteDbModel> mList;

    public ArchiveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // quoteDbModelListRV = getAllQuotes();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        quoteDb = QuoteDb.getQuoteDb(getContext());
        mList = quoteDb.quoteDao().getAll();
        quoteDbModelListRV = new ArrayList<>(mList.size());
        for (QuoteDbModel q:mList) {
            quoteDbModelListRV.add(q);
        }
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_archive, container, false);
        rv = (RecyclerView) rootView.findViewById(R.id.archive_rv);
       // Toast.makeText(getContext(),quoteDbModelListRV.get(0).getAuthor(),Toast.LENGTH_LONG).show();
        rvAdapter = new RVAdapter(quoteDbModelListRV);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(mLayoutManager);
         rv.setItemAnimator(new DefaultItemAnimator());

        rv.setAdapter(rvAdapter);
        rvAdapter.notifyDataSetChanged();
        return rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
    public ArrayList<QuoteDbModel> getAllQuotes(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                quoteDb = QuoteDb.getQuoteDb(getContext());
                mList = quoteDb.quoteDao().getAll();
                quoteDbModelList = new ArrayList<>();
            }
        };
        new Thread(runnable).start();

        return quoteDbModelList;
    }
}
