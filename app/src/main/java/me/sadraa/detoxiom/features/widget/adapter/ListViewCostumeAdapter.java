package me.sadraa.detoxiom.features.widget.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.Collections;
import java.util.List;

import me.sadraa.detoxiom.R;
import me.sadraa.detoxiom.features.widget.App;

/**
 * Created by sadra on 10/5/17.
 */

public class ListViewCostumeAdapter
        extends RecyclerView.Adapter<ListViewCostumeAdapter.ViewHolder> {

    private List<App> mData = Collections.emptyList();
    private LayoutInflater mInflater;
    private OnClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    public ListViewCostumeAdapter(Context context, List<App> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ListViewCostumeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        View view = mInflater.inflate(R.layout.row_item,
                parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ListViewCostumeAdapter.ViewHolder holder, int position) {
        final App app = mData.get(position);

        holder.txtAppTitle.setText(app.getLabel());

        Glide.with(context)
                .load(app.getIconURI())
                .into(new GlideDrawableImageViewTarget(holder.imgAppIcon) {
                    @Override
                    public void onResourceReady(GlideDrawable resource
                            , GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        //if any app doesn't load resource icon get it's drawable
                        Drawable icon = app.getAppInfo().loadIcon(context.getPackageManager());
                        Glide.with(context)
                                .load("")
                                .placeholder(icon)
                                .into(holder.imgAppIcon);
                    }
                });

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

