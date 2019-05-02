package com.alphanota.myswale.network.api.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.alphanota.myswale.network.api.adapter.VolleyListener;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GsonRequest<T> extends Request<T> {
    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Map<String, String> headers;
    private final VolleyListener listener;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url URL of the request to make
     * @param clazz Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     */
    GsonRequest(int method, String url, Class<T> clazz, Map<String, String> headers,
                       VolleyListener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> superHeaders = super.getHeaders();
        if (superHeaders == null
                || superHeaders.equals(Collections.emptyMap())) {
            superHeaders = new HashMap<>();
        }
        // For example
        //headers.put("Cookie",user.getSessionId());
        //this overrides any default headers that were already in request
        if(headers != null) superHeaders.putAll(headers);
        return superHeaders;
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        this.listener.setNetworkResponse(response);

        Response<T> parsedResponse;

        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            parsedResponse = Response.success(
                    gson.fromJson(json, clazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            parsedResponse = Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            parsedResponse = Response.error(new ParseError(e));
        }

        this.listener.setParsedResponse(parsedResponse);

        return parsedResponse;
    }
}
