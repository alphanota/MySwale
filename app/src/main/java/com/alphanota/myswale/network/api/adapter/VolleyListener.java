package com.alphanota.myswale.network.api.adapter;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;

public abstract class VolleyListener<T> implements Response.Listener<T> {

    private NetworkResponse networkResponse;

    private Response<T> parsedResponse;

    public void setNetworkResponse(NetworkResponse response) {
        this.networkResponse = response;
    }

    public NetworkResponse getNetworkResponse(){
        return networkResponse;
    }

    public Response<T> getParsedResponse() {
        return parsedResponse;
    }

    public void setParsedResponse(Response<T> parsedResponse) {
        this.parsedResponse = parsedResponse;
    }
}
