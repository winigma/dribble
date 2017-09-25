package br.com.dribble.wisley.mydribble.view.fragment;

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
import br.com.dribble.wisley.mydribble.view.holder.HolderHomeFragment;

/**
 * Created by Wisley on 24/09/17.
 */

public class HomeFragment extends Fragment implements IPresenterShot {


    private PresenterShot mPresenter;
    HolderHomeFragment mHolder;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //mHolder = new HolderHomeFragment(view);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new PresenterShot(this);
        mPresenter.notifyStart();
        //initializeView();
    }


    @Override
    public void notifyError(ErrorResponse errorResponse) {
        Toast.makeText(getActivity(),"Error!!!",Toast.LENGTH_LONG).show();
    }

    @Override
    public void notifyStart() {

    }

    @Override
    public void notifySucces(List<ShotBO> reponse) {

        Toast.makeText(getActivity(),"Sucesso!!!",Toast.LENGTH_LONG).show();
    }
}
