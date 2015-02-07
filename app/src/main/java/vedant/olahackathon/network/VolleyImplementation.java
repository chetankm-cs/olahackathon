package vedant.olahackathon.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;

/**
 * Created by nikhil on 20/10/14
 */
public class VolleyImplementation {
    private Context context;
    private Response.Listener responseListener;
    private final String TAG = "VolleyImplementation";
    private ProgressDialog ringProgressDialog;

    public VolleyImplementation(Context context, Response.Listener responseListener) {
        this.context = context;
        this.responseListener = responseListener;
    }

    public VolleyImplementation(Context context,Response.Listener responseListener,Response.ErrorListener demoErrorListener)
    {
        this.context =context;
        this.responseListener = responseListener;
        this.demoErrorListener = demoErrorListener;
    }

    public void postRequest(String tag, String jsonString) {
        try {
            Log.w(TAG,"URL :"+URL.getFullURL(tag));
            Log.w(TAG,"Request :" + jsonString);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL.getFullURL(tag), new JSONObject(jsonString), demoResponseListener, demoErrorListener);

            RetryPolicy policy = new DefaultRetryPolicy(WebServicesConstants.volleyRequestTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            jsonObjectRequest.setRetryPolicy(policy);
            VolleySingleton.getInstance(context).addToRequestQueueWithCache(jsonObjectRequest,tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void postRequestWitDialog(String tag, String jsonString) {
        try {
            ringProgressDialog = ProgressDialog.show(context, null, "Loading...", true);
            ringProgressDialog.setCancelable(false);
            postRequest(tag,jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopProgressDialog() {
        if (ringProgressDialog != null && ringProgressDialog.isShowing())
            ringProgressDialog.dismiss();
    }

    private Response.Listener demoResponseListener = new Response.Listener() {
        @Override
        public void onResponse(Object o) {
            stopProgressDialog();
            Log.w(TAG, "Response : " + o);
            responseListener.onResponse(o);
        }
    };

    private Response.ErrorListener demoErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            stopProgressDialog();
            if( error instanceof NetworkError) {
                Toast.makeText(context, "Network Error", Toast.LENGTH_LONG).show();
            } else if( error instanceof ServerError) {
                Toast.makeText(context, "Server Error.", Toast.LENGTH_LONG).show();
            } else if( error instanceof AuthFailureError) {
                Toast.makeText(context, "Authentication Failure.", Toast.LENGTH_LONG).show();
            } else if( error instanceof NoConnectionError) {
                Toast.makeText(context, "No Connection", Toast.LENGTH_LONG).show();
            } else if( error instanceof TimeoutError) {
                Toast.makeText(context, "Connection Timeout", Toast.LENGTH_LONG).show();
            } else{
                Toast.makeText(context, "Network error", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "Error Volley : " + error);
            error.printStackTrace();
        }
    };

    public void stopRequest(String TAG) {
        stopProgressDialog();
        VolleySingleton.getInstance(context).cancelPendingRequests(TAG);
    }
}
