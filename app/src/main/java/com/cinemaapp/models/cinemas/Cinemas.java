package com.cinemaapp.models.cinemas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Superadmin1 on 02/12/2017.
 */

public class Cinemas implements Serializable {

    @SerializedName("_id")
    @Expose
    private String _id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("_v")
    @Expose
    private String _v;

    @SerializedName("locationsList")
    @Expose
    private LocationsList[] locationsList;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_v() {
        return _v;
    }

    public void set_v(String _v) {
        this._v = _v;
    }

    public LocationsList[] getLocationsList() {
        return locationsList;
    }

    public void setLocationsList(LocationsList[] locationsList) {
        this.locationsList = locationsList;
    }
}
