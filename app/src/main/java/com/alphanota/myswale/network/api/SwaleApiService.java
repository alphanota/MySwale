package com.alphanota.myswale.network.api;

import android.content.Context;

import com.alphanota.myswale.BuildConfig;
import com.alphanota.myswale.model.Swale;
import com.alphanota.myswale.network.api.route.Route;
import com.alphanota.myswale.network.api.volley.CallBuilder;

import androidx.lifecycle.LiveData;


public class SwaleApiService implements SwaleApi {

    final CallBuilder builder;

    public SwaleApiService(Context context) {
        builder = new CallBuilder(context, BuildConfig.API_URL);
        Route.setBuilder(builder);
    }

    @Override
    public LiveData<ApiResponse<Swale[]>> getAllSwales() {
        return Route.Swale.getSwales();
    }

    @Override
    public LiveData<ApiResponse<Swale[]>> fetchSwaleByLocation(double lat, double lon, double radius_m) {
        return Route.Swale.getByGeom(lat,lon,radius_m);
    }
}
