
package com.alphanota.myswale.network.api.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class NetworkRequest {

    private String mUrl;
    private JSONObject body;
    private Map<String,String> customHeaders;
    int method;
    VolleyCallback callback;
    NetworkResponse networkResponse;

    NetworkRequest(int method, String url, Map<String,String> body, Map<String,String> headers, VolleyCallback callback) {
        this.method = method;
        this.mUrl = url;
        this.body = new JSONObject(body);
        this.customHeaders = headers;
        this.callback = callback;
    }

    StringRequest buildStringRequest(){
        StringRequest request = new StringRequest(method, mUrl,
                response -> {
                        //if(callBack!= null) callBack.//do something with string response
                },
                error -> {
                }
        ) {
//            @Override
//            public Map<String, String> getParams() {
//                return (body == null) ? new HashMap<>(): body;
//            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = super.getHeaders();

                if (headers == null
                        || headers.equals(Collections.emptyMap())) {
                    headers = new HashMap<>();
                }

                // For example
                //headers.put("Cookie",user.getSessionId());
                //this overrides any default headers that were already in request
                if(customHeaders != null) headers.putAll(customHeaders);

                return headers;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public byte[] getBody()  {
                return (body == null) ? new byte[0]: body.toString().getBytes();
            }


            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                // since we don't know which of the two underlying network vehicles
                // will Volley use, we have to handle and store session cookies manually
                //MyApp.get().checkSessionCookie(response.headers);
                //

                //response.

                networkResponse = response;
                if(callback != null)callback.networkResponse = response;

                return super.parseNetworkResponse(response);
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


        return request;
    }

    public void test(){

    }

}
