package br.com.dribble.wisley.mydribble.presenter;

import java.util.List;

import br.com.dribble.wisley.mydribble.model.ResultBO;
import br.com.dribble.wisley.mydribble.model.ShotBO;
import br.com.dribble.wisley.mydribble.model.response.ErrorResponse;
import br.com.dribble.wisley.mydribble.network.requesthelper.ShotRequestHelper;
import br.com.dribble.wisley.mydribble.network.requesthelper.contracts.IShotRequest;
import br.com.dribble.wisley.mydribble.presenter.contracts.IPresenterShot;

/**
 * Created by Wisley on 24/09/17.
 */

public class PresenterShot implements IShotRequest{

    final IPresenterShot mCallBack;

    public PresenterShot(final IPresenterShot mCallBack) {
        this.mCallBack = mCallBack;
    }

    @Override
    public void notifyStart() {
        mCallBack.notifyStart();
        final ShotRequestHelper shotRequestHelper = new ShotRequestHelper(ResultBO.class,this);
        shotRequestHelper.getShots();
    }

    @Override
    public void notifySuccess(List<ShotBO> response) {
        this.mCallBack.notifySucces(response);
    }

    @Override
    public void notifyFailure(ErrorResponse errorResponse) {
        this.mCallBack.notifyError(errorResponse);
    }
}
