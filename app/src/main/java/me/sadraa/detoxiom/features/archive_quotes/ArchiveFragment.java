package me.sadraa.detoxiom.features.archive_quotes;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.sadraa.detoxiom.R;
import me.sadraa.detoxiom.db.Models.QuoteDbModel;
import me.sadraa.detoxiom.db.QuoteDb;
import me.sadraa.detoxiom.di.RVAdapterModule;

public class ArchiveFragment extends Fragment {
    ArrayList<QuoteDbModel> quoteDbModelList;
    QuoteDb quoteDb;

    @BindView(R.id.archive_rv) RecyclerView rv;
    @BindView(R.id.empty_view) TextView tv;
    @BindView(R.id.empty_status) LottieAnimationView emptyAnimation;
    private Unbinder unbinder;
    PopupMenu popupMenu;
    @Inject
    CompositeDisposable compositeDisposable;
    @Inject
    RVAdapter rvAdapter;
    @Inject
    DividerItemDecoration dividerItemDecoration;
    @Inject
    RecyclerView.LayoutManager mLayoutManager;

    public ArchiveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
         DaggerArchiveFragmentComponent.builder()
                .rVAdapterModule(new RVAdapterModule(getContext()))
                .build().injectFragment(this);;

        super.onCreate(savedInstanceState);
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
        compositeDisposable.dispose();
    }
    //This method is mother method of this fragment. first create an object from DB and then execute query in
    //rxJava and then populate RV
    public void startQueryAndPopulate(){
        //Create an object from QuoteDb Class
        quoteDb = QuoteDb.getQuoteDb(getContext());
        //make an Observer from arrays of QuoteDbModel Objects async
        Observable.fromArray(convertListQuoteToArray(quoteDb.quoteDao().getAll()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<QuoteDbModel>>() {
                               @Override
                               public void onSubscribe(Disposable d) {
                                   compositeDisposable.add(d);
                               }

                               @Override
                               public void onNext(ArrayList<QuoteDbModel> quoteDbModels) {
                                   populateRV(quoteDbModels);
                               }

                               @Override
                               public void onError(Throwable e) {

                               }

                               @Override
                               public void onComplete() {

                               }
                           });

    }
    //The method called in startQueryAndPopulate( onUiThread )method and populate RV with data of data base
    public void populateRV(ArrayList<QuoteDbModel> quoteDbModelListRV){
        //Create adapter object with ArrayList
        rvAdapter.setQuoteList(quoteDbModelListRV);
        //Check if recycleView have data or not. if not then show a message.
        if(rvAdapter.getItemCount()==0){
            rv.setVisibility(View.GONE);
            tv.setVisibility(View.VISIBLE);
            emptyAnimation.setVisibility(View.VISIBLE);
        }

        if(rvAdapter.getItemCount()!=0){
            rv.setVisibility(View.VISIBLE);
            tv.setVisibility(View.GONE);
            emptyAnimation.setVisibility(View.GONE);
           // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            rv.setLayoutManager(mLayoutManager);
            //DividerDecoration is a new class in support library that help to draw a line between each row of recyvleview
            rv.addItemDecoration(dividerItemDecoration);
            //It makes scrolling smooth
            rv.setNestedScrollingEnabled(false);
            rv.setItemAnimator(new DefaultItemAnimator());
            //using a seprate method for set On Item click listener
            setOnItemClickListenerToAdapter();
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
   //we Just set On item click listener for adapter with RxJava/
    public void setOnItemClickListenerToAdapter(){
        rvAdapter.setOnItemClickListener((view, position, data) -> {
            popupMenu= new PopupMenu(view.getContext(),view);
            popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.delete) {
                    final QuoteDb quoteDb = QuoteDb.getQuoteDb(getContext());
                    rvAdapter.quoteList.remove(position);
                    quoteDb.quoteDao().deleteOne(rvAdapter.quoteList.get(position));
                    rvAdapter.notifyItemRemoved(position);
                    return true;
                }
                if (item.getItemId() == R.id.share) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, rvAdapter.quoteList.get(position).getQuote());
                    view.getContext().startActivity(Intent.createChooser(intent, " به اشتراک بگذارید "));
                    return true;
                }
                return true;
            });
            popupMenu.show();
        });
    }
}