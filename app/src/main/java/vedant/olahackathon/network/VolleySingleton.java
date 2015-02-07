package vedant.olahackathon.network;

/**
 * Created by prabhat on 21/10/14.
 */

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.Volley;
import org.apache.http.impl.client.DefaultHttpClient;

public class VolleySingleton  {

    public static final String TAG = VolleySingleton.class
            .getSimpleName();

    private RequestQueue mRequestQueue;
    private RequestQueue mRequestQueueCache;
    private static VolleySingleton mInstance;
    private static Context mCtx;

    private VolleySingleton(Context context)
    {
        this.mCtx = context;
    }


    public static synchronized VolleySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext(),new HttpClientStack(new DefaultHttpClient()));
        }

        return mRequestQueue;
    }
    public RequestQueue getRequestQueueWithCache() {
        if (mRequestQueueCache == null) {
            mRequestQueueCache = Volley.newRequestQueue(mCtx.getApplicationContext(),new HttpClientStack(new DefaultHttpClient()));
        }
        return mRequestQueueCache;
    }

    public <T> void addToRequestQueueWithCache(Request<T> req, String tag) {
        req.setTag(tag);
        getRequestQueueWithCache().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req,String tag) {
        req.setTag(tag);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(String tag) {

        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
        if(mRequestQueueCache!=null){
            mRequestQueueCache.cancelAll(tag);
        }
    }
}