package com.alphanota.myswale.db.converter;


import com.alphanota.myswale.model.Geometry;
import com.google.gson.Gson;

import androidx.room.TypeConverter;

public class CoordinatesConverter {
    @TypeConverter
    public static double[] toCoord(String c) {
        return new Gson().fromJson(c, double[].class);
    }

    @TypeConverter
    public static String toString(double[] c) {return new Gson().toJson(c);}
}
