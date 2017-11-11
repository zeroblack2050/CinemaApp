package com.cinemaapp.models.movies;

import org.simpleframework.xml.Element;

/**
 * Created by Superadmin1 on 11/11/2017.
 */

@Element(name = "preview")
public class Preview {

    @Element(name ="large")
    String large;

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}
