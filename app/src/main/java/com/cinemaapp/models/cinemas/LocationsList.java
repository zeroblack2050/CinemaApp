package com.cinemaapp.models.cinemas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Superadmin1 on 02/12/2017.
 */

public class LocationsList implements Serializable {

    @SerializedName("_id")
    @Expose
    private String _id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("location")
    @Expose
    private Location location;


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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
