package br.com.dribble.wisley.mydribble.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.dribble.wisley.mydribble.R;

/**
 * Created by wisle on 25/09/2017.
 * Holder of Shot Fragmnet detail
 */

public class HolderShotDetailFragment extends AbstractHolderFragment {

    private ImageView ivThumbnail;
    private TextView tvTittle;
    private TextView tvViewsCount;
    private TextView tvCommentCount;
    private TextView tvDescription;


    public HolderShotDetailFragment(View siteSelectionView) {
        super(siteSelectionView);
    }

    @Override
    protected void initializeFields() {
        this.ivThumbnail = (ImageView) getField(R.id.ivThumbnail);
        this.tvTittle = (TextView) getField(R.id.tvTittle);
        this.tvViewsCount = (TextView) getField(R.id.tvViewsCount);
        this.tvCommentCount = (TextView) getField(R.id.tvCommentCount);
        this.tvDescription = (TextView) getField(R.id.tvDescription);
    }

    @Override
    protected void initializeActions() {

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

    public TextView getTvViewsCount() {
        return tvViewsCount;
    }

    public void setTvViewsCount(TextView tvViewsCount) {
        this.tvViewsCount = tvViewsCount;
    }

    public TextView getTvCommentCount() {
        return tvCommentCount;
    }

    public void setTvCommentCount(TextView tvCommentCount) {
        this.tvCommentCount = tvCommentCount;
    }

    public TextView getTvDescription() {
        return tvDescription;
    }

    public void setTvDescription(TextView tvDescription) {
        this.tvDescription = tvDescription;
    }
}
