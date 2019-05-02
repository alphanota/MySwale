package com.alphanota.myswale.network.api.adapter.call;

import com.alphanota.myswale.network.api.adapter.VolleyListener;
import com.android.volley.Response;

public abstract class Call<R> {
    public abstract void enqueue(VolleyListener<R> responseListener, Response.ErrorListener errorListener);
}
