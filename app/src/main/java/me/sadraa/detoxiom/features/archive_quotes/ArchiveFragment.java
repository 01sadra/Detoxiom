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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import me.sadraa.detoxiom.R;
import me.sadraa.detoxiom.data.db.Models.QuoteDbModel;
import me.sadraa.detoxiom.data.db.QuoteDb;
import me.sadraa.detoxiom.di.ArchivedependencyModule;

public class ArchiveFragment extends Fragment implements ArchiveContract.View{
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
    @Inject
    ArchiveContract.Presenter presenter;
    public ArchiveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
         DaggerArchiveFragmentComponent.builder()
                .archivedependencyModule(new ArchivedependencyModule(getContext()))
                .build().injectFragment(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_archive, container, false);
        //binding the Butterknife
        unbinder = ButterKnife.bind(this,rootView);

        populateListView(presenter.returnAllQuotesFromIntractorInArrayList());

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


   //we Just set On item click listener for adapter with RxJava/
    public void setOnItemClickListenerToAdapter(){
        rvAdapter.setOnItemClickListener((view, position, data) -> {
            popupMenu= new PopupMenu(view.getContext(),view);
            popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.delete) {
                    deleteQuote(position);
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

    @Override
    public void populateListView(ArrayList<QuoteDbModel> q) {
        //Create adapter object with ArrayList
        rvAdapter.setQuoteList(q);
        //Check if recycleView have data or not. if not then show a message.
        if(rvAdapter.getItemCount()==0){
            showWhaleInsteadOfRView();
        }
        if(rvAdapter.getItemCount()!=0){
            setAdapterAndShowRView();
        }
    }

    @Override
    public void showWhaleInsteadOfRView() {
        rv.setVisibility(View.GONE);
        tv.setVisibility(View.VISIBLE);
        emptyAnimation.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAdapterAndShowRView() {
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

    @Override
    public void deleteQuote(int position) {
        final QuoteDb quoteDb = QuoteDb.getQuoteDb(getContext());
        quoteDb.quoteDao().deleteOne(rvAdapter.quoteList.get(position));
        rvAdapter.quoteList.remove(position);
        rvAdapter.notifyItemRemoved(position);
    }
}
