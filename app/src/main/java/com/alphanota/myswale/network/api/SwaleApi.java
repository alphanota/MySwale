package com.alphanota.myswale.network.api;

import com.alphanota.myswale.model.Swale;

import androidx.lifecycle.LiveData;

public interface SwaleApi {

    LiveData<ApiResponse<Swale[]>> getAllSwales();
    LiveData<ApiResponse<Swale[]>> fetchSwaleByLocation(double lat, double lon, double radius_m);

}
