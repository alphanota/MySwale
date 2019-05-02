package com.alphanota.myswale.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Making fields public so that getter/setter methods are not needed
@Entity(tableName = "swales")
public class Swale {
    @Embedded
    public Geometry the_geom;
    @PrimaryKey
    public long asset_id;
    public String gi_id;
    public String dep_contra;
    public String dep_cont_1;
    public String project_ty;
    public String project_na;
    public String asset_type;
    public String status;
    public double asset_x_co;
    public double asset_y_co;
    public String borough;
    public String sewer_type;
    public String cso_tribut;
    public String waterbody;
    public String street_add;
    public String nearest_in;
    public long bbl;
    public long secondary;
    public long community;
    public long city_conc;
    public String assembly_d;
    public long asset_d;
    public long asset_widt;
    public long asset_area;
    public String gi_feature;
    public String tree_speci;
    public String tree_culti;
    public long calc_rain;
}
