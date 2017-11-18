package com.cinemaapp.models.movies;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Element;

import java.io.Serializable;


/**
 * Created by Superadmin1 on 11/11/2017.
 */


@Element(name = "poster")
public class Poster implements Serializable{

    @SerializedName("location")
    @Element(name ="location")
    String location;

    @SerializedName("xlarge")
    @Element(name ="xlarge")
    String xlarge;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getXlarge() {
        return xlarge;
    }

    public void setXlarge(String xlarge) {
        this.xlarge = xlarge;
    }
}
