package com.alphanota.myswale.network.api.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.alphanota.myswale.network.RequestQueueSingleton;
import com.alphanota.myswale.network.api.volley.UriParamHelper.InvalidPathParameterException;
import com.alphanota.myswale.network.api.volley.UriParamHelper.UriQueryHelper;
import com.alphanota.myswale.network.api.volley.UriParamHelper.UrlParamHelper;

import java.util.Map;

public class CallBuilder {


    protected RequestQueue queue;
    private final String baseUrl;

    public CallBuilder(Context appContext, String baseUrl) {
        this.queue = RequestQueueSingleton.getInstance(appContext).getRequestQueue();
        this.baseUrl=baseUrl;
    }

    private String getRelativeUrl(String relativeUrl, Map<String,String> pathParams) {
        if(pathParams == null) return relativeUrl;
        else if(relativeUrl == null) return "";
        else try {
                return UrlParamHelper.replaceFields(relativeUrl,pathParams);
            } catch (InvalidPathParameterException e) {
                e.printStackTrace();
                return "";
            }
    }

    //VolleyCall(int method, String url, Map<String,String> body, Map<String,String> headers)

    private VolleyCall buildPost(String relativeUrl, Map<String,String> body, Map<String,String> pathParams, Map<String,String> headers){
        String filledRelativeUrl = baseUrl + getRelativeUrl(relativeUrl, pathParams);

        return new VolleyCall(Request.Method.POST,filledRelativeUrl,body,headers);
    }

    private VolleyCall buildGet(String relativeUrl, Map<String,String> queryParams, Map<String,String> pathParams, Map<String,String> headers){
        String filledRelativeUrl = baseUrl + getRelativeUrl(relativeUrl, pathParams);
        String filledRelativeUrlWithQueries = UriQueryHelper.appendToUrl(filledRelativeUrl,queryParams);
        return new VolleyCall(Request.Method.GET, filledRelativeUrlWithQueries, null, headers);
    }

    public VolleyCall POST(String relativeUrl, Map<String,String> body, Map<String,String> pathParams, Map<String,String> headers){
        VolleyCall call = buildPost(relativeUrl,body,pathParams,headers);
        call.setQueue(queue);
        return call;
    }

    public VolleyCall GET(String relativeUrl, Map<String,String> queryParams, Map<String,String> pathParams, Map<String,String> headers) {
        VolleyCall call = buildGet(relativeUrl,queryParams,pathParams,headers);
        call.setQueue(queue);
        return call;
    }


}
