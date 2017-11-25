package me.sadraa.detoxiom.di;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import dagger.Module;
import dagger.Provides;
import me.sadraa.detoxiom.features.archive_quotes.RVAdapter;

/**
 * Created by sadra on 11/25/17.
 */
@Module
public class RVAdapterModule {
    Context context;

    public RVAdapterModule(Context context){
        this.context=context;
    }
    @ArchiveFragmentComponentScope
    @Provides
    RVAdapter rvAdapter(){
        return new RVAdapter();
    }
    @ArchiveFragmentComponentScope
    @Provides
    RecyclerView.LayoutManager layoutManager(){
        return new LinearLayoutManager(context);
    }
    @ArchiveFragmentComponentScope
    @Provides
    DividerItemDecoration dividerItemDecoration() {
       return new DividerItemDecoration(context,DividerItemDecoration.VERTICAL);
    }


}
