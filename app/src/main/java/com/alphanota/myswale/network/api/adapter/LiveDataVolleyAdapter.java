package com.alphanota.myswale.network.api.adapter;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.alphanota.myswale.network.api.ApiResponse;
import com.alphanota.myswale.network.api.adapter.call.CallAdapter;
import com.alphanota.myswale.network.api.volley.VolleyCall;
import com.alphanota.myswale.network.api.volley.VolleyCallback;
import com.alphanota.myswale.network.http.VolleyResponse;

import java.util.concurrent.atomic.AtomicBoolean;

import androidx.lifecycle.LiveData;

public class LiveDataVolleyAdapter<R> implements CallAdapter<R, LiveData<ApiResponse<R>>> {
    private final Class<R> klazz;

    public LiveDataVolleyAdapter(Class<R> responseType) {
        this.klazz = responseType;

    }

    @Override
    public Class<R> responseType() {
        return klazz;
    }

    @Override
    public LiveData<ApiResponse<R>>  adapt(VolleyCall<R> call) {
        return adapt(call,null);
    }

    @Override
    public LiveData<ApiResponse<R>> adapt(VolleyCall<R> call, VolleyCallback callback) {
        return new LiveData<ApiResponse<R>>() {
            AtomicBoolean started = new AtomicBoolean(false);
            @Override
            protected void onActive() {
                super.onActive();

                if (started.compareAndSet(false, true)) {


                    VolleyListener<R> responseListener = new VolleyListener<R>() {
                        @Override
                        public void onResponse(R response) {
                            VolleyResponse<R> volleyResponse =
                                    new VolleyResponse<>(response,
                                    this.getNetworkResponse(),
                                    this.getParsedResponse());
                            if(callback!= null) callback.onResponse(volleyResponse);
                            postValue(new ApiResponse<>(volleyResponse));
                        }
                    };

                    Response.ErrorListener errorListener = new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if(callback!= null) callback.onFailure(error);
                            postValue(new ApiResponse<>(error));
                        }
                    };

                    call.setKlass(klazz);
                    call.enqueue(responseListener,errorListener);
                };
            }
        };
    }
}
