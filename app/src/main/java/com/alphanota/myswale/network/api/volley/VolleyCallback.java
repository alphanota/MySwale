package com.alphanota.myswale.network.api.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.alphanota.myswale.network.http.VolleyResponse;

public abstract class VolleyCallback<T> {
    public NetworkResponse networkResponse;
    public abstract void onResponse(VolleyResponse<T> volleyResponse);
    public abstract  void onFailure(VolleyError error);
}