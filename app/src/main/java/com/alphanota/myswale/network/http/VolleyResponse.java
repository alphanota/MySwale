package com.alphanota.myswale.network.http;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;

import java.util.Map;

public class VolleyResponse<T>  implements com.alphanota.myswale.network.http.Response<T> {

    private T body;
    private int code;
    private boolean isSuccessful;
    private String errorBody;
    private String message;

    private NetworkResponse networkResponse;
    private Response<T> parsedResponse;

    public VolleyResponse(Object response,
                          NetworkResponse networkResponse,
                          Response<T> parsedResponse) {
        body = (T)response;
        code = networkResponse.statusCode;
        isSuccessful = parsedResponse.isSuccess();
        errorBody = isSuccessful ? null:new String(networkResponse.data);
        message = body.toString();
        this.networkResponse = networkResponse;
        this.parsedResponse = parsedResponse;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public boolean isSuccessful() {
        return isSuccessful;
    }

    @Override
    public T body() {
        return body;
    }

    @Override
    public Object errorBody() {
        return errorBody;
    }

    @Override
    public String message() {
        return message;
    }

    public Map<String,String> getHeaders(){
        return networkResponse.headers;
    }
}
