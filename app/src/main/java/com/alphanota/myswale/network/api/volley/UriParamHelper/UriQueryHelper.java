package com.alphanota.myswale.network.api.volley.UriParamHelper;

import java.util.Iterator;
import java.util.Map;

public class UriQueryHelper {

    public  static String formQueryString(Map<String,String> queries) {
        StringBuilder builder = new StringBuilder();
        Iterator<Map.Entry<String, String>> iterator = queries.entrySet().iterator();
        int i = 1;
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            if (i == 1) {
                builder.append("?" + entry.getKey() + "=" + entry.getValue());
            } else {
                builder.append("&" + entry.getKey() + "=" + entry.getValue());
            }
            iterator.remove(); // avoids a ConcurrentModificationException
            i++;
        }
        String str = builder.toString();
        return str;
    }
    public static String appendToUrl(String baseUrl, Map<String,String> queries) {
        return (queries == null) ?  baseUrl : baseUrl + formQueryString(queries);
    }
}
