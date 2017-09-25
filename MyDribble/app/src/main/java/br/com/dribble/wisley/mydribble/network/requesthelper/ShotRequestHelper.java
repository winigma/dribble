package br.com.dribble.wisley.mydribble.network.requesthelper;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import br.com.dribble.wisley.mydribble.model.ResultBO;
import br.com.dribble.wisley.mydribble.model.ShotBO;
import br.com.dribble.wisley.mydribble.network.APIs;
import br.com.dribble.wisley.mydribble.network.GsonSimpleRequest;
import br.com.dribble.wisley.mydribble.network.requesthelper.contracts.IShotRequest;

/**
 * Created by Wisley on 24/09/17.
 */

public class ShotRequestHelper<T> extends BaseRequestHelper<T> {

    private IShotRequest mPresenter;

    public ShotRequestHelper(Class<T> clazz,final IShotRequest mPresenter) {
        super(clazz);
        this.mPresenter = mPresenter;

    }

    /**
     * this method call shot's
     */
    public void getShots() {

        final GsonSimpleRequest<ResultBO> request = new GsonSimpleRequest<>(
                Request.Method.GET,
                APIs.URL_CONTENT,
                ResultBO.class,
                null,
                new Response.Listener<ResultBO>() {
                    @Override
                    public void onResponse(ResultBO response) {
                        mPresenter.notifySuccess(response.getShots());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mPresenter.notifyFailure(getErrorResponse(error));
                    }
                });

        startRequest(request);


    }

}
