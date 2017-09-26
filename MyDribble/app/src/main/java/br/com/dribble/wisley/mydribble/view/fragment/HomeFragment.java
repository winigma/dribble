package br.com.dribble.wisley.mydribble.view.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import br.com.dribble.wisley.mydribble.R;
import br.com.dribble.wisley.mydribble.model.ShotBO;
import br.com.dribble.wisley.mydribble.model.response.ErrorResponse;
import br.com.dribble.wisley.mydribble.presenter.PresenterShot;
import br.com.dribble.wisley.mydribble.presenter.contracts.IPresenterShot;
import br.com.dribble.wisley.mydribble.util.ActivityUtils;
import br.com.dribble.wisley.mydribble.view.activity.MainActivity;
import br.com.dribble.wisley.mydribble.view.adapter.ShotAdapter;
import br.com.dribble.wisley.mydribble.view.contracts.OnClickShotDetail;
import br.com.dribble.wisley.mydribble.view.holder.HolderHomeFragment;

/**
 * Created by Wisley on 24/09/17.
 */

public class HomeFragment extends Fragment implements IPresenterShot, OnClickShotDetail {


    private PresenterShot mPresenter;
    HolderHomeFragment mHolder;
    private ShotAdapter mAdapter;
    ProgressDialog dialog;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mHolder = new HolderHomeFragment(view);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new PresenterShot(this);
        mPresenter.notifyStart();
        initializeView();
    }

    private void initializeView() {
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
    }


    @Override
    public void notifyError(ErrorResponse errorResponse) {
        Toast.makeText(getActivity(), "Error!!!", Toast.LENGTH_LONG).show();
        dialog.hide();
    }

    @Override
    public void notifyStart() {
        dialog=new ProgressDialog(getActivity());
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();
    }

    @Override
    public void notifySucces(List<ShotBO> reponse) {

        //Toast.makeText(getActivity(),"Sucesso!!!",Toast.LENGTH_LONG).show();
        this.mAdapter = new ShotAdapter(reponse, this);
        this.mHolder.getRvBody().setAdapter(this.mAdapter);
        dialog.hide();
    }

    @Override
    public void onClickOpenDetail(int position) {

        ShotBO bo = mAdapter.getItem(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable(ShotDetailFragmet.KEY_SHOT, bo);
        ShotDetailFragmet frag = new ShotDetailFragmet();
        frag.setArguments(bundle);
        ActivityUtils.replaceFragmentToActivityWithBackStack(this,getActivity().
                getSupportFragmentManager(), frag, R.id.home_container);

    }
}
