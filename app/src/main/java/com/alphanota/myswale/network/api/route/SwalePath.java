package com.alphanota.myswale.network.api.route;

import com.alphanota.myswale.model.Swale;
import com.alphanota.myswale.network.api.ApiResponse;
import com.alphanota.myswale.network.api.adapter.LiveDataVolleyAdapter;
import com.alphanota.myswale.network.api.route.requestObjs.ByGeom;
import com.alphanota.myswale.network.api.volley.VolleyCall;

import java.util.Map;

import androidx.lifecycle.LiveData;

public class SwalePath extends Path {
    public static LiveData<ApiResponse< Swale[] >> getAll(){
        String relativeUrl = "";

        VolleyCall< Swale[] > call = builder.GET(relativeUrl,null,null,addDefaultHeaders(null));
        call.setKlass(Swale[].class);

        LiveDataVolleyAdapter<Swale[]> adapter = new LiveDataVolleyAdapter<>(Swale[].class);
        return adapter.adapt(call);
    }

    public static LiveData<ApiResponse< Swale[] >> get(double lat, double lon, double radius_m){
        String relativeUrl = "";
        Map<String,String> query = new ByGeom(lat,lon, radius_m).mapify();

        VolleyCall< Swale[] > call = builder.GET(relativeUrl,query,null,addDefaultHeaders(null));
        call.setKlass(Swale[].class);

        LiveDataVolleyAdapter<Swale[]> adapter = new LiveDataVolleyAdapter<>(Swale[].class);
        return adapter.adapt(call);
    }
}
