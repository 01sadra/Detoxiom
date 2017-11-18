package me.sadraa.detoxiom.features.archive_quotes;

import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.sadraa.detoxiom.R;
import me.sadraa.detoxiom.db.Models.QuoteDbModel;

/**
 * Created by sadra on 10/18/17.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder> {
    ArrayList<QuoteDbModel> quoteList;
    private OnItemClickListener mItemClickListener;
    public RVAdapter(ArrayList<QuoteDbModel> quoteList){
        this.quoteList = quoteList;
    }

    public void setOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.author_text_rv) TextView authorRV;
        @BindView(R.id.quote_text_rv) TextView quoteRV;
        PopupMenu popupMenu;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //binding the butterknife
            ButterKnife.bind(this,itemView);
        }
     //defining an interface that pass the posiotion of item get clicked to the fragment.
        @Override
        public void onClick(final View v) {
            if (mItemClickListener != null) {
                int adapterPosition = getAdapterPosition();
                mItemClickListener.onItemClick(v, adapterPosition, quoteList.get(adapterPosition));
            }

    }}


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row_rv,parent,false);
        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        QuoteDbModel quoteDbModel = quoteList.get(position);
        holder.authorRV.setText(quoteDbModel.getAuthor());
        holder.quoteRV.setText(quoteDbModel.getQuote());
    }

    @Override
    public int getItemCount() {
        if(quoteList!=null){
            return quoteList.size();
        }
        return 0;
    }


}
