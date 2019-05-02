package com.alphanota.myswale.network.api.volley.UriParamHelper;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlParamHelper {

    private static final String PARAM = "[a-zA-Z][a-zA-Z0-9_-]*";
    private static final Pattern PARAM_URL_REGEX = Pattern.compile("\\{(" + PARAM + ")\\}");
    private static final Pattern PARAM_NAME_REGEX = Pattern.compile(PARAM);

    public static String replaceFields(String url, Map<String,String> params) throws InvalidPathParameterException {

        Set<String> givenKeys = params.keySet();
        Set<String> expectedKeys = getUrlParams(url);

        if(!expectedKeys.equals(givenKeys)) {throw new InvalidPathParameterException();}
        else return replaceAllFields(url, params);

    }

    private static String replaceAllFields(String url, Map<String,String> params) {
        String newUrl = url;

        for(Map.Entry<String,String> entry : params.entrySet()) {
            String param = entry.getKey();
            String value = entry.getValue();

            Matcher m = genKeyPattern(param).matcher(newUrl);

            newUrl = m.replaceAll(value);
        }
        return newUrl;
    }

    private static Set<String> getUrlParams(String path) {
        Matcher m = PARAM_URL_REGEX.matcher(path);

        Set<String> patterns = new LinkedHashSet<>();
        while (m.find()) {
            patterns.add(m.group(1));
        }
        return patterns;
    }

    private static Pattern genKeyPattern(String entry) {
        return Pattern.compile("\\{(" + entry + ")\\}");
    }




}
