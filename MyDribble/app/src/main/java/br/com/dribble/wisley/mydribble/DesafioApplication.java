package br.com.dribble.wisley.mydribble;

import android.app.Application;
import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.net.CookieManager;
import java.net.CookiePolicy;

import br.com.dribble.wisley.mydribble.network.OkHttpStack;
import br.com.dribble.wisley.mydribble.util.NetworkUtils;
import br.com.dribble.wisley.mydribble.util.Utils;

/**
 * Created by Wisley on 10/09/17.
 */

public class DesafioApplication extends Application {

    private static final int REQUEST_TIMEOUT = 180000;


    private static DesafioApplication mInstance;
    private RequestQueue queue;
    private Request request;
    private OkHttpClient httpClient;
    private Context context;


    private Gson gson;


    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponents();
    }

    public static DesafioApplication getInstance() {
        return mInstance;
    }

    private void initializeComponents() {
        mInstance = this;

        this.gson = new GsonBuilder().setDateFormat(Utils.JSON_DATE_FORMAT).create();

        httpClient = new OkHttpClient();

        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        httpClient.setCookieHandler(cookieManager);
    }

    public Gson getGson() {
        return gson;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    private RequestQueue getRequestQueue() {
        if (queue == null) {
            queue = Volley.newRequestQueue(getApplicationContext(), new OkHttpStack(httpClient));
        }
        return queue;
    }

    public <T> boolean addRequestQueue(Request<T> req) {
        if (NetworkUtils.isNetworkAvailable(this)) {
            req.setRetryPolicy(retryPolicy());
            getRequestQueue().add(req);
            return true;
        }
        return false;
    }

    private DefaultRetryPolicy retryPolicy() {
        return new DefaultRetryPolicy(
                REQUEST_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    public void removeRequestQueue(final String TAG) {
        if (NetworkUtils.isNetworkAvailable(context)) {
            if (getRequestQueue()  != null) {
                getRequestQueue().cancelAll(TAG);
            }
        }
    }

    public boolean cancelRequest(Request request) {
        if (request != null && !request.isCanceled()) {
            request.cancel();
            return true;
        }

        return false;
    }

    public void cancelAllRequest() {
        if (NetworkUtils.isNetworkAvailable(context)) {
            if (getRequestQueue()  != null) {

                getRequestQueue().cancelAll(new RequestQueue.RequestFilter() {
                    @Override
                    public boolean apply(Request<?> request) {
                        return true;
                    }
                });
                queue = Volley.newRequestQueue(getApplicationContext(), new OkHttpStack(httpClient));
            }
        }
    }
}
