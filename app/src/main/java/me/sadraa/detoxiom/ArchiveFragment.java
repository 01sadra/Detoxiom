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
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArchiveFragment extends Fragment {
    ArrayList<QuoteDbModel> quoteDbModelList;
    QuoteDb quoteDb;

    @BindView(R.id.archive_rv) RecyclerView rv;
    RVAdapter rvAdapter;
    @BindView(R.id.empty_view) TextView tv;
    @BindView(R.id.empty_status) LottieAnimationView emptyAnimation;
    private Unbinder unbinder;
    public ArchiveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_archive, container, false);
        //binding the Butterknife
        unbinder = ButterKnife.bind(this,rootView);
        //Call mother method of Archive fragment (more info in comments of method)
        startQueryAndPopulate();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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

    //The method called in startQueryAndPopulate( onUiThread )method and populate RV with data of data base
    public void populateRV(ArrayList<QuoteDbModel> quoteDbModelListRV){
        //Create adapter object with ArrayList
        rvAdapter = new RVAdapter(quoteDbModelListRV);
        //Check if recycleView have data or not. if not then show a message.
        if(rvAdapter.getItemCount()==0){
            rv.setVisibility(View.GONE);
            tv.setVisibility(View.VISIBLE);
            emptyAnimation.setVisibility(View.VISIBLE);
        } else {
            rv.setVisibility(View.VISIBLE);
            tv.setVisibility(View.GONE);
            emptyAnimation.setVisibility(View.GONE);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            rv.setLayoutManager(mLayoutManager);
            //DividerDecoration is a new class in support library that help to draw a line between each row of recyvleview
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(),DividerItemDecoration.VERTICAL);
            rv.addItemDecoration(dividerItemDecoration);
            //It makes scrolling smooth
            rv.setNestedScrollingEnabled(false);
            rv.setItemAnimator(new DefaultItemAnimator());
            rv.setAdapter(rvAdapter);
        }
    }

    //The method get list of quotes and return an Array list to populate list view
    //This method called from startQueryAndPopulate() method not in OnCreate stage
    public ArrayList<QuoteDbModel> convertListQuoteToArray(List<QuoteDbModel> mList){
        if (mList != null) {
            quoteDbModelList = new ArrayList<>(mList.size());
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
