package com.alphanota.myswale.model;

import com.alphanota.myswale.db.converter.CoordinatesConverter;

import androidx.room.TypeConverters;

public class Geometry {
    public String type;
    @TypeConverters(CoordinatesConverter.class)
    public double[] coordinates;
}
