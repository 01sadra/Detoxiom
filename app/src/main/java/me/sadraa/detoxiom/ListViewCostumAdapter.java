package me.sadraa.detoxiom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sadra on 10/5/17.
 */

public class ListViewCostumAdapter extends BaseAdapter {
    ArrayList<String> appNames;
    ArrayList<Drawable> appLogo;
    Context context;
    LayoutInflater inflater;

    public ListViewCostumAdapter(Context context, ArrayList<String> appNames , ArrayList<Drawable> logoName) {
        this.appNames=appNames;
        this.appLogo =logoName;
        this.context=context;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return appNames.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class Holder{
        @BindView(R.id.TitleText) TextView tv;
        @BindView(R.id.logoImage) ImageView iv;

        public Holder(View view){
            ButterKnife.bind(this,view);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = inflater.inflate(R.layout.row_item,null);
        Holder holder = new Holder(rowView);
        holder.tv.setText(appNames.get(position));
        holder.iv.setImageDrawable(appLogo.get(position));
        return rowView;
    }
}
