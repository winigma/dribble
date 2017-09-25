package br.com.dribble.wisley.mydribble.view.adapter.viewholder;


import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.dribble.wisley.mydribble.R;

/**
 * Created by wisle on 25/09/2017.
 */

public class ViewHolderShot extends ViewHolder {

    private ImageView ivThumbnail;
    private TextView tvTittle;
    private TextView tvViewCount;
    private TextView tvViewCreated;
    private View viewItem;

    public ViewHolderShot(View view) {
        super(view);
        this.ivThumbnail = (ImageView) view.findViewById(R.id.ivThumbnail);
        this.tvViewCount = (TextView) view.findViewById(R.id.tvViewsCount);
        this.tvTittle = (TextView) view.findViewById(R.id.tvTitle);
        this.tvViewCreated = (TextView) view.findViewById(R.id.tvCreate);

        viewItem = view;
    }


    public ImageView getIvThumbnail() {
        return ivThumbnail;
    }

    public void setIvThumbnail(ImageView ivThumbnail) {
        this.ivThumbnail = ivThumbnail;
    }

    public TextView getTvTittle() {
        return tvTittle;
    }

    public void setTvTittle(TextView tvTittle) {
        this.tvTittle = tvTittle;
    }

    public TextView getTvViewCount() {
        return tvViewCount;
    }

    public void setTvViewCount(TextView tvViewCount) {
        this.tvViewCount = tvViewCount;
    }

    public TextView getTvViewCreated() {
        return tvViewCreated;
    }

    public void setTvViewCreated(TextView tvViewCreated) {
        this.tvViewCreated = tvViewCreated;
    }

    public View getViewItem() {
        return viewItem;
    }

    public void setViewItem(View viewItem) {
        this.viewItem = viewItem;
    }
}
