package me.sadraa.detoxiom.features.archive_quotes;

import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.sadraa.detoxiom.R;
import me.sadraa.detoxiom.db.Models.QuoteDbModel;
import me.sadraa.detoxiom.db.QuoteDb;

/**
 * Created by sadra on 10/18/17.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder> {
    private ArrayList<QuoteDbModel> quoteList;

    public RVAdapter(ArrayList<QuoteDbModel> quoteList){
        this.quoteList = quoteList;
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
        //If you are reading my code and see this i'm really sorry. It's the most shitty code i've ever written.
        //I will fix it ASAP but keep in mind you should NEVER handle clicks in viewHolder as I do it here.
        @Override
        public void onClick(final View view) {

            popupMenu= new PopupMenu(view.getContext(),view);
            popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    if(item.getItemId()== R.id.delete){
                        QuoteDb quoteDb = QuoteDb.getQuoteDb(view.getContext());
                        try {
                            quoteDb.quoteDao().deleteOne(quoteList.get(getAdapterPosition()));
                            quoteList.remove(getAdapterPosition());
                            RVAdapter.this.notifyItemRemoved(getAdapterPosition());
                        }catch (Exception e){

                        }

                        return true;
                    }else {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(android.content.Intent.EXTRA_TEXT, quoteList.get(getAdapterPosition()).getQuote());
                        view.getContext().startActivity(Intent.createChooser(intent," به اشتراک بگذارید "));

                        return true;
                    }
                }
            });
            popupMenu.show();
        }
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
