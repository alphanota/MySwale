package com.alphanota.myswale.network.api.route;

import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

class AuthenticatedPath extends Path {
    protected static Map<String,String> authenticationHeaders = new HashMap<>();

    public static void addAuthHeader(String key, String value) {
        authenticationHeaders.put(key,value);
    }

    public static void clearAuthHeaders() {
        authenticationHeaders.clear();
    }

    public static Map<String,String> addDefaultHeaders(Map<String,String> givenHeaders) {


        if (authenticationHeaders.isEmpty()) {
            // throw error
            Timber.d("Required authentication headers are missing");
        }

        Map<String,String> headers = new HashMap<>();
        if(defaultHeaders != null) headers.putAll(defaultHeaders);
        if(givenHeaders != null) headers.putAll(givenHeaders);
        if(authenticationHeaders != null) headers.putAll(authenticationHeaders);

        return headers;
    }

}
