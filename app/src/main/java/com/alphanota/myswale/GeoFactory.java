package com.alphanota.myswale;

import com.alphanota.myswale.model.Geometry;
import com.google.android.gms.maps.model.LatLng;

public class GeoFactory {
    public static LatLng GeomToLatLng(Geometry g) {
        LatLng latLng = new LatLng(g.coordinates[1], g.coordinates[0]);
        return  latLng;
    }
}
