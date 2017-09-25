package br.com.dribble.wisley.mydribble.network;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class GsonSimpleRequest<T> extends GsonRequest<T> {


    /**
     * @param method        Method of the request (GET, POST, PUT, DELETE)
     * @param url           URL of the request to make
     * @param clazz         Relevant class object, for Gson's reflection
     * @param object        Object to call POST and PUT
     * @param listener
     * @param errorListener
     */
    public GsonSimpleRequest(int method, String url, Class<T> clazz,
                             Object object, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, clazz, object, listener, errorListener);
    }

    /**
     * @param method        Method of the request (GET, POST, PUT, DELETE)
     * @param url           URL of the request to make
     * @param clazz         Relevant class object, for Gson's reflection
     * @param listener
     * @param errorListener
     */
    public GsonSimpleRequest(int method, String url, Class<T> clazz,
                             Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, clazz, null, listener, errorListener);
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {


        try {

            String json = new String(response.data, HttpHeaderParser
                    .parseCharset(response.headers));
            try {
                if (json.equals(""))
                    return (Response<T>) Response.success(null,
                            HttpHeaderParser.parseCacheHeaders(response));

                JSONObject jsonObject = new JSONArray(json).getJSONObject(0);//new JSONObject(json);
                final JSONObject params = new JSONObject();
                params.put("shots",  new JSONArray(json));
                final String userErrorKey = "userErrorKey";
                if (jsonObject.has(userErrorKey)) {
                    VolleyError error = new VolleyError(params
                            .getString(userErrorKey));
                    return Response.error(error);
                }

                if (clazz.getSimpleName().equals("String")) {
                    return (Response<T>) Response.success(params.toString(),
                            HttpHeaderParser.parseCacheHeaders(response));
                } else {
                    return Response.success(gson.fromJson(params.toString(), clazz),
                            HttpHeaderParser.parseCacheHeaders(response));
                }


            } catch (JSONException e) {
                return Response.error(new ParseError(e));
            }
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {

        try {
            if (volleyError != null && volleyError.networkResponse != null) {
                String json = new String(volleyError.networkResponse.data, HttpHeaderParser
                        .parseCharset(volleyError.networkResponse.headers));

                if (json.equals(""))
                    return super.parseNetworkError(volleyError);

                JSONObject jsonObject = new JSONObject(json);
                final String result = "result";
                if (jsonObject.has(result)) {
                    final String errors = "errors";
                    jsonObject = jsonObject.getJSONObject(result);
                    if (jsonObject.has(errors))
                        return new VolleyError(jsonObject
                                .getString(errors));
                }
            } else {
                return volleyError;
            }
        } catch (UnsupportedEncodingException e) {
            return new VolleyError(new ParseError(e));
        } catch (JSONException e) {
            return new VolleyError(new ParseError(e));
        } catch (Exception e) {
            return new VolleyError(new ParseError(e));
        }
        return super.parseNetworkError(volleyError);
    }

}
