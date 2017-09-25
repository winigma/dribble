package br.com.dribble.wisley.mydribble.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import br.com.dribble.wisley.mydribble.R;
import br.com.dribble.wisley.mydribble.model.ShotBO;
import br.com.dribble.wisley.mydribble.view.activity.MainActivity;
import br.com.dribble.wisley.mydribble.view.holder.HolderShotDetailFragment;

/**
 * Created by wisle on 25/09/2017.
 */

public class ShotDetailFragmet extends Fragment {
    private HolderShotDetailFragment mHolder;
    public static final String KEY_SHOT = "key_shot";
    private ShotBO mShot;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            mShot = this.getArguments().getParcelable(KEY_SHOT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shot_detail, container, false);
        mHolder = new HolderShotDetailFragment(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeView();
    }

    private void initializeView() {

        /**
         * Toolbar
         */
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);


        /**
         * Views
         */
        mHolder.getTvTittle().setText(mShot.getTitle());
        mHolder.getTvCommentCount().setText(String.valueOf(mShot.getLikes_count()));
        mHolder.getTvViewsCount().setText(String.valueOf(mShot.getViews_count()));
        mHolder.getTvDescription().setText(mShot.getDescription() != null?Html.fromHtml(mShot.getDescription().toString()): "");

        Picasso.with(getActivity())
                .load(this.mShot.getImages().getNormal())
                .error(R.drawable.sasquatch)
                .placeholder(R.drawable.sasquatch)
                .fit()
                .into(mHolder.getIvThumbnail());



    }
}
