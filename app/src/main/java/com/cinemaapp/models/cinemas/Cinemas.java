package com.cinemaapp.models.cinemas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

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

    @SerializedName("__v")
    @Expose
    private String __v;

    @SerializedName("locationsList")
    @Expose
    private ArrayList<LocationsList> locationsList;


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

    public String get__v() {
        return __v;
    }

    public void set__v(String __v) {
        this.__v = __v;
    }

    public ArrayList<LocationsList> getLocationsList() {
        return locationsList;
    }

    public void setLocationsList(ArrayList<LocationsList> locationsList) {
        this.locationsList = locationsList;
    }
}
