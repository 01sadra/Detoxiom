package me.sadraa.detoxiom;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sadra on 10/18/17.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder> {
    private ArrayList<QuoteDbModel> quoteList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView authorRV, quoteRV;
        public MyViewHolder(View itemView) {
            super(itemView);
            authorRV = (TextView) itemView.findViewById(R.id.author_text_rv);
            quoteRV = (TextView) itemView.findViewById(R.id.quote_text_rv);
        }
    }
    public RVAdapter(ArrayList<QuoteDbModel> quoteList){
        this.quoteList = quoteList;
    }

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
