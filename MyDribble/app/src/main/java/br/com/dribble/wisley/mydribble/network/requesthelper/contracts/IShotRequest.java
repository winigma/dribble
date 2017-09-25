package br.com.dribble.wisley.mydribble.network.requesthelper.contracts;

import java.util.List;

import br.com.dribble.wisley.mydribble.model.ShotBO;
import br.com.dribble.wisley.mydribble.model.response.ErrorResponse;

/**
 * Created by Wisley on 12/09/17.
 */

public interface IShotRequest {

    /**
     * start request
     */
    void notifyStart();

    /**
     * notify on finish success
     *
     * @param response
     */
    void notifySuccess(final List<ShotBO> response);

    /**
     * notify error
     *
     * @param errorResponse
     */
    void notifyFailure(final ErrorResponse errorResponse);
}
