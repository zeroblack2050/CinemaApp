package com.cinemaapp.models.movies;

import org.simpleframework.xml.Element;


/**
 * Created by Superadmin1 on 11/11/2017.
 */


@Element(name = "cast")
public class Cast {

    @Element(name ="cast")
    String cast;

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }
}
