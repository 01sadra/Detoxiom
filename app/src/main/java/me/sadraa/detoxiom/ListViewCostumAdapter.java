package me.sadraa.detoxiom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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
        TextView tv;
        ImageView iv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView = inflater.inflate(R.layout.row_item,null);
        holder.tv=(TextView) rowView.findViewById(R.id.TitleText);
        holder.iv=(ImageView) rowView.findViewById(R.id.logoImage);
        holder.tv.setText(appNames.get(position));
        holder.iv.setImageDrawable(appLogo.get(position));
        return rowView;
    }
}
