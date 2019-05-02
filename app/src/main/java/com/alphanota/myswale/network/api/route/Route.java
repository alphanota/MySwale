package com.alphanota.myswale.network.api.route;

import com.alphanota.myswale.model.Swale;
import com.alphanota.myswale.network.api.ApiResponse;
import androidx.lifecycle.LiveData;

public class Route extends Path {

    public static void setAuthenticationHeader(String header, String value) {
        AuthenticatedPath.addAuthHeader(header,value);
    }

    public static class Swale extends Path {
        public static LiveData<ApiResponse<com.alphanota.myswale.model.Swale[]>> getSwales() {
            return SwalePath.getAll();
        }
        public static LiveData<ApiResponse<com.alphanota.myswale.model.Swale[]>> getByGeom(double lat, double lon, double radius_m) {
            return SwalePath.get(lat,lon,radius_m);
        }

    }
}
