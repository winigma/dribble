package br.com.dribble.wisley.mydribble.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.dribble.wisley.mydribble.R;
import br.com.dribble.wisley.mydribble.model.ShotBO;
import br.com.dribble.wisley.mydribble.view.adapter.viewholder.ViewHolderShot;
import br.com.dribble.wisley.mydribble.view.contracts.OnClickShotDetail;

/**
 * Created by wisle on 25/09/2017.
 */

public class ShotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnClickShotDetail onClickDetail;
    private List<ShotBO> shots;
    private Context mContext;

    public ShotAdapter(final List<ShotBO> shots, OnClickShotDetail listener) {
        this.shots = shots;
        this.onClickDetail = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shot2, parent, false);
        return new ViewHolderShot(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final ViewHolderShot viewHolderShot = (ViewHolderShot) holder;
        viewHolderShot.getTvTittle().setText(getItem(position).getTitle());
        viewHolderShot.getTvViewCount().setText(String.valueOf(getItem(position).getViews_count()));
        viewHolderShot.getTvViewCreated().setText(parseDate(getItem(position).getCreated_at()));
        Picasso.with(mContext)
                .load(getItem(position).getImages().getTeaser())
                .error(R.drawable.img_default)
                .placeholder(R.drawable.img_default)
                .fit()
                .centerInside()
                .into(viewHolderShot.getIvThumbnail());
        viewHolderShot.getViewItem().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDetail.onClickOpenDetail(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return shots.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    /**
     * Return item to position
     *
     * @param position
     * @return
     */
    public ShotBO getItem(final int position) {
        if (position < 0 || position > shots.size()) {
            return null;
        }
        return shots.get(position);
    }

    /**
     * formmater date
     * @param strDate
     * @return
     */
    private String parseDate(final String strDate) {
        try {
            DateFormat original = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            Date result1 = original.parse(strDate);

            SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
            String myNewDate = formatter.format(result1);
            return myNewDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }

    }
}
