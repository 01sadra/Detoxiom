package me.sadraa.detoxiom.features.widget.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import me.sadraa.detoxiom.R;
import me.sadraa.detoxiom.features.widget.App;

/**
 * Created by sadra on 10/5/17.
 */
/*

public class ListViewCostumAdapter extends BaseAdapter {
    List<App> apps;
    LayoutInflater inflater;

    public ListViewCostumAdapter(Context context, List<App> apps) {
        this.apps = apps;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return apps.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class Holder {
        @BindView(R.id.TitleText)
        TextView tv;
        @BindView(R.id.logoImage)
        ImageView iv;

        public Holder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = inflater.inflate(R.layout.row_item, null);
        Holder holder = new Holder(rowView);
        holder.tv.setText(appNames.get(position));
        holder.iv.setImageDrawable(appLogo.get(position));
        return rowView;
    }
}

class AdapterMain extends RecyclerView.Adapter<AdapterMain.MyViewHolder> {

    private List<App> mainList;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        ImageView img;
        TextView txtSize;

        MyViewHolder(View view) {
            super(view);
            txtSize = view.findViewById(R.id.TitleText);
            img = view.findViewById(R.id.logoImage);

        }
    }


    AdapterMain(List<App> mainList) {
        this.mainList = mainList;
    }

    void updateList(List<App> array) {
        mainList = array;
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        App main = mainList.get(position);

        holder.txtTitle.setText(main.getLabel());
        holder.img.setImageDrawable(main.getIcon());
    }

    @Override
    public int getItemCount() {
        return mainList.size();
    }

}
*/

/*
public class ListViewCostumAdapter extends ArrayAdapter<App> {
    private View.OnClickListener parentClickListener;
    private Context context;
    private List<App> data;

    ListViewCostumAdapter(Context context, ArrayList<App> data) {
        super(context, R.layout.row_item, data);
        this.context = context;
        this.data = data;
    }

    class ViewHolder {

        TextView titleTV;
        ImageView imageView;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        final ListViewCostumAdapter.ViewHolder holder;

        final App app = data.get(position);

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_item, parent, false);
            holder = new ListViewCostumAdapter.ViewHolder();

            holder.titleTV = row.findViewById(R.id.TitleText);

            holder.imageView = row.findViewById(R.id.logoImage);

            row.setTag(holder);
        } else {
            holder = (ListViewCostumAdapter.ViewHolder) row.getTag();
        }

        holder.titleTV.setText(app.getLabel());


        holder.imageView.setImageURI(app.getIconURI());

        row.setOnClickListener(v -> parentClickListener.onClick(v));

        return row;
    }

    public void setOnItemClickListener(View.OnClickListener clickListener) {
        this.parentClickListener = clickListener;
    }


}
*/


public class ListViewCostumAdapter
        extends RecyclerView.Adapter<ListViewCostumAdapter.ViewHolder> {

    private List<App> mData = Collections.emptyList();
    private LayoutInflater mInflater;
    private OnClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    public ListViewCostumAdapter(Context context, List<App> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ListViewCostumAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        View view = mInflater.inflate(R.layout.row_item,
                parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the textview in each row
    @Override
    public void onBindViewHolder(ListViewCostumAdapter.ViewHolder holder, int position) {
        final App app = mData.get(position);

        holder.txtAppTitle.setText(app.getLabel());

        holder.imgAppIcon.setImageURI(app.getIconURI());

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView txtAppTitle;
        ImageView imgAppIcon;


        public ViewHolder(View itemView) {
            super(itemView);
            txtAppTitle = itemView.findViewById(R.id.TitleText);
            imgAppIcon = itemView.findViewById(R.id.logoImage);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onClick(
                        mData.get(getAdapterPosition()),
                        getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public App getItem(int id) {
        return mData.get(id);
    }


    public void setData(List<App> data) {
        mData = data;
    }

    // allows clicks events to be caught
    public void setClickListener(OnClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface OnClickListener {
        void onClick(App appModel, int position);
    }

}

