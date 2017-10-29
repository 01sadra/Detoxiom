package me.sadraa.detoxiom;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArchiveFragment extends Fragment {
    ArrayList<QuoteDbModel> quoteDbModelList;
    QuoteDb quoteDb;
    RecyclerView rv;
    RVAdapter rvAdapter;


    public ArchiveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_archive, container, false);
        rv = (RecyclerView) rootView.findViewById(R.id.archive_rv);
        //Call mother method of Archive fragment (more info in comments of method)
        startQueryAndPopulate();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    //This method is mother method of this fragment. first create an object from DB and then execute query in
    //background thread and then call a method on uiThread for set adapter and populate list view
    public void startQueryAndPopulate(){
      //create new runnable
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //Create an object from QuoteDb Class
                quoteDb = QuoteDb.getQuoteDb(getContext());
                //Retrieve List of saved Quotes and authors
                final List<QuoteDbModel> mList = quoteDb.quoteDao().getAll();
                //Convert data from list to Array
                final ArrayList<QuoteDbModel> quoteDbModelListRV = convertListQuoteToArray(mList);
                //Call populateRV method on ui thread.
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        populateRV(quoteDbModelListRV);
                    }
                });
            }
        };
        new Thread(runnable).start();
    }

    //The method called in startQueryAndPopulate( onUiThead )method and populate RV with data of data base
    public void populateRV(ArrayList<QuoteDbModel> quoteDbModelListRV){
        //Create adapter object with ArrayList
        rvAdapter = new RVAdapter(quoteDbModelListRV);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(mLayoutManager);
        //DividerDecoration is a new class in support library that help to draw a line between each row of recyvleview
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(),DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(dividerItemDecoration);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(rvAdapter);
    }

    //The method get list of quotes and return an Array list to populate list view
    //This method called from startQueryAndPopulate() method not in OnCreate stage
    public ArrayList<QuoteDbModel> convertListQuoteToArray(List<QuoteDbModel> mList){
        if (mList != null) {
            quoteDbModelList = new ArrayList<>(mList.size());
            for (QuoteDbModel q:mList) {
                quoteDbModelList.add(q);
            }
            return quoteDbModelList;
        }else{
            return null;
        }

    }

}
