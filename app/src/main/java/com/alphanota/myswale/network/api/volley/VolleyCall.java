package com.alphanota.myswale.network.api.volley;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.alphanota.myswale.network.api.adapter.VolleyListener;

import org.json.JSONObject;

import java.util.Map;

public class VolleyCall<R> {

    private RequestQueue queue;


    private String mUrl;
    private JSONObject body;
    private Map<String,String> customHeaders;
    private Request<R> request;
    private Class<R> clazz;
    int method;

    Response.ErrorListener errorListener;
    VolleyListener responseListener;

    VolleyCall(int method, String url, Map<String,String> body, Map<String,String> headers) {
        this.method = method;
        this.mUrl = url;
        this.body =  (body == null) ? null : new JSONObject(body);
        this.customHeaders = headers;
    }

    public void setQueue(RequestQueue queue) {
        this.queue = queue;
    }

    public void setKlass(Class<R> clazz) {
        this.clazz = clazz;
    }

    public void setErrorListener(Response.ErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    public void setResponseListener(VolleyListener<R> listener) {
        this.responseListener = listener;
    }

    private void buildRequest(){

        request = new GsonRequest<R>(method,mUrl,clazz,this.customHeaders, responseListener, errorListener) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public byte[] getBody()  {
                return (body == null) ? new byte[0]: body.toString().getBytes();
            }
        };

    }

    private void setRetryPolicy(){
        request.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }


    public void enqueue(VolleyListener listener, Response.ErrorListener errorListener) {
        setResponseListener(listener);

        setErrorListener(errorListener);

        buildRequest();
        setRetryPolicy();


        queue.add(request);
    }
}
