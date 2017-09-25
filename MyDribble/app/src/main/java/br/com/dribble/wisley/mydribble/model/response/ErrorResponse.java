package br.com.dribble.wisley.mydribble.model.response;


import br.com.dribble.wisley.mydribble.network.enums.HttpStatusCode;


public class ErrorResponse {

    private HttpStatusCode httpStatusCode;

    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(HttpStatusCode httpStatusCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }

    public HttpStatusCode getHttpStatusCode() {
        return httpStatusCode;
    }

    private void setHttpStatusCode(HttpStatusCode httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setHttpStatusCode(int statusCode){
        switch (statusCode){
            case 400:
                setHttpStatusCode(HttpStatusCode.BAD_REQUEST);
                break;
            case 401:
                setHttpStatusCode(HttpStatusCode.UNAUTHORIZED);
                break;
            case 403:
                setHttpStatusCode(HttpStatusCode.FORBIDDEN);
                break;
            case 404:
                setHttpStatusCode(HttpStatusCode.NOT_FOUND);
                break;
            case 409:
                setHttpStatusCode(HttpStatusCode.CONFLICT);
                break;
            case 422:
                setHttpStatusCode(HttpStatusCode.UNPROCESSABLE);
                break;
            case 500:
                setHttpStatusCode(HttpStatusCode.INTERNAL_SERVER_ERROR);
                break;
            case 502:
                setHttpStatusCode(HttpStatusCode.BAD_GATEWAY);
                break;
            case 503:
                setHttpStatusCode(HttpStatusCode.SERVICE_UNAVAILABLE);
                break;
            case 504:
                setHttpStatusCode(HttpStatusCode.GATEWAY_TIMEOUT);
                break;

        }
    }
}
