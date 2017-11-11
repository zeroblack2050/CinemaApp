package com.cinemaapp.models.movies;

import org.simpleframework.xml.Element;


/**
 * Created by Superadmin1 on 11/11/2017.
 */


@Element(name = "poster")
public class Poster {

    @Element(name ="location")
    String location;

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
