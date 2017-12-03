package com.cinemaapp.models.cinemas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Superadmin1 on 02/12/2017.
 */

public class LocationCine implements Serializable {

    @SerializedName("coordinates")
    @Expose
    private Double []  coordinates;

    @SerializedName("type")
    @Expose
    private String type;

    public Double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Double[] coordinates) {
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
