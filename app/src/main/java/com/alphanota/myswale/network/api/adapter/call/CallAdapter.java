package com.alphanota.myswale.network.api.adapter.call;


import com.alphanota.myswale.network.api.volley.VolleyCall;
import com.alphanota.myswale.network.api.volley.VolleyCallback;

public interface CallAdapter<R, LiveData > {
    Class<R> responseType();
    LiveData adapt(VolleyCall<R> call);
    LiveData adapt(VolleyCall<R> call, VolleyCallback callback);
}
