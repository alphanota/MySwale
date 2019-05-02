package com.alphanota.myswale.network.api.route.requestObjs;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

class Parameter {
    static final Type type = new TypeToken<Map<String, String>>() {
    }.getType();
    static final Gson gson = new Gson();

    public Map<String, String> mapify() {
        String json = gson.toJson(this,this.getClass());
        return new Gson().fromJson(json, type);
    }
}
