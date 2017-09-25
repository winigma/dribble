package br.com.dribble.wisley.mydribble.presenter.contracts;

import java.util.List;

import br.com.dribble.wisley.mydribble.model.ResultBO;
import br.com.dribble.wisley.mydribble.model.ShotBO;
import br.com.dribble.wisley.mydribble.model.response.ErrorResponse;

/**
 * Created by Wisley on 24/09/17.
 */

public interface IPresenterShot {

    /* send notification on connection failure and others erros    */
    void notifyError(final ErrorResponse errorResponse);

    /**
     * send notification has start
     */
    void notifyStart();

    /**
     * send notification has finish
     */
    void notifySucces(final List<ShotBO> reponse);
}
