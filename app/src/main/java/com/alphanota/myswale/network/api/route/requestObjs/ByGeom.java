package com.alphanota.myswale.network.api.route.requestObjs;

import com.google.gson.annotations.SerializedName;

public class ByGeom extends Parameter {

    @SerializedName("$where")
    public String where;

    public ByGeom(double lat, double lon, double radius_m) {
        this.where = String.format("within_circle(the_geom, %f, %f, %f)", lat, lon, radius_m );
    }
}
