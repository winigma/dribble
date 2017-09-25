package br.com.dribble.wisley.mydribble.network.enums;


public enum HttpStatusCode {

    //FAMILY 400
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),

    //FAMILY 500
    INTERNAL_SERVER_ERROR(500),
    BAD_GATEWAY(502),
    SERVICE_UNAVAILABLE(503),
    GATEWAY_TIMEOUT(504),
    CONFLICT(409),
    UNPROCESSABLE(422);

    private int status;

    HttpStatusCode(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
