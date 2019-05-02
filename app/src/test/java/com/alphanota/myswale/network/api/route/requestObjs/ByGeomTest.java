package com.alphanota.myswale.network.api.route.requestObjs;

import org.junit.Test;

import static org.junit.Assert.*;

public class ByGeomTest {

    @Test
    public void mapify(){
        ByGeom geom = new ByGeom(47.333,-71.7, 1000);

        assertEquals("{$where=within_circle(the_geom, 47.333000, -71.700000, 1000.000000)}", geom.mapify().toString());
    }

}