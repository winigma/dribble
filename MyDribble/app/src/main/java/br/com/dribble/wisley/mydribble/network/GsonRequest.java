package br.com.dribble.wisley.mydribble.network;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import br.com.dribble.wisley.mydribble.DesafioApplication;

public class GsonRequest<T> extends Request<T> {
    final Gson gson = DesafioApplication.getInstance().getGson();
    final Class<T> clazz;
    final Response.Listener<T> listener;
    final Object object;


    /**
     * Make a request and return a parsed object from JSON.
     *
     * @param method Method of the request (GET, POST, PUT, DELETE)
     * @param url    URL of the request to make
     * @param clazz  Relevant class object, for Gson's reflection
     * @param object Object to call POST and PUT
     */
    public GsonRequest(int method, String url, Class<T> clazz, Object object,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.object = object;
        this.listener = listener;

    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer "+APIs.DRIBBBLE_CLIENT_ACCESS_TOKEN);




        return headers;
    }

    public String getBodyContentType() {
        return "application/json; charset=utf-8";
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return gson.toJson(object).getBytes();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(gson.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        return super.parseNetworkError(volleyError);
    }
}
