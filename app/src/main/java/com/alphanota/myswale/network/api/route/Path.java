package com.alphanota.myswale.network.api.route;


import com.alphanota.myswale.network.api.volley.CallBuilder;

import java.util.HashMap;
import java.util.Map;

class Path {
    protected static CallBuilder builder;

    protected static Map<String,String> defaultHeaders = new HashMap<>();

    public static void addDefaultHeader(String key, String value) {
        defaultHeaders.put(key,value);
    }

    public static void setBuilder(CallBuilder newBuilder) {
        builder = newBuilder;
    }

    public static Map<String,String> addDefaultHeaders(Map<String,String> givenHeaders) {

        Map<String,String> headers = new HashMap<>();
        if(defaultHeaders != null) headers.putAll(defaultHeaders);
        if(givenHeaders != null) headers.putAll(givenHeaders);
        return headers;
    }
}
