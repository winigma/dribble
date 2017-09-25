package br.com.dribble.wisley.mydribble.network.requesthelper;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.dribble.wisley.mydribble.DesafioApplication;
import br.com.dribble.wisley.mydribble.model.response.ErrorResponse;
import br.com.dribble.wisley.mydribble.network.enums.HttpStatusCode;
import br.com.dribble.wisley.mydribble.network.enums.ServiceMessage;

/**
 *
 * @param <T>
 */
public abstract class BaseRequestHelper<T> {

    protected Class<T> clazz;
    private ErrorResponse errorResponse;

    public BaseRequestHelper(Class<T> clazz) {
        this.errorResponse = null;
        this.clazz = clazz;
    }



    protected ErrorResponse getErrorResponse(VolleyError volleyError) {
        if (volleyError != null) {
            errorResponse = new ErrorResponse();

            if(checkedAuthFailure(volleyError)){
                errorResponse.setHttpStatusCode(401);
                errorResponse.setMessage("UNAUTHORIZED");
                return errorResponse;

            }


            if (volleyError.networkResponse != null)
                errorResponse.setHttpStatusCode(volleyError.networkResponse.statusCode);

            errorResponse.setMessage(volleyError.getMessage());
        }

        return errorResponse;
    }


    private boolean checkedAuthFailure(final VolleyError volleyError) {
        if (volleyError instanceof AuthFailureError) {
            return true;
        } else {
            return false;
        }

    }

    public String trimMessage(String json, String key) {
        String trimmedString = null;

        try {
            JSONObject obj = new JSONObject(json);
            trimmedString = obj.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return trimmedString;
    }

    //Somewhere that has access to a context
    public void displayMessage(String toastString) {
        //Toast.makeText(context, toastString, Toast.LENGTH_LONG).show();
    }


    private boolean isLoseSession(ErrorResponse errorResponse) {
        if (errorResponse == null) {
            return false;
        }

        if (errorResponse.getHttpStatusCode() != null && errorResponse.getHttpStatusCode().equals(HttpStatusCode.UNAUTHORIZED)) {
            return true;
        }

        //Recorder return
        if (errorResponse.getMessage() != null && (errorResponse.getMessage().equalsIgnoreCase(ServiceMessage.PREVIOUS_SESSION_NEEDED.getMessage())
                || errorResponse.getMessage().equalsIgnoreCase(ServiceMessage.CHECK_SESSION.getMessage()))) {
            return true;
        }

        return false;
    }


    protected boolean handleHttpError(VolleyError error) {
        ErrorResponse errorResponse = getErrorResponse(error);

        if (isLoseSession(errorResponse)) {
            //case contains login auth
        } else {
            return true;
        }

        return false;
    }

    protected <T> void startRequest(Request<T> request) {

        if (!DesafioApplication.getInstance().addRequestQueue(request)) {
            //TODO network fail hideProgress
            request.getErrorListener();
        }
    }

    protected <T> void startRequestWithinTag(Request<T> request, String tag) {
        request.setTag(tag);
        if (!DesafioApplication.getInstance().addRequestQueue(request)) {
            //TODO network fail hideProgress
            request.getErrorListener();
        }
    }

    protected void removeRequest(String tag) {
        DesafioApplication.getInstance().removeRequestQueue(tag);
    }

}
