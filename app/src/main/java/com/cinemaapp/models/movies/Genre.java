package com.cinemaapp.models.movies;

import org.simpleframework.xml.Element;


/**
 * Created by Superadmin1 on 11/11/2017.
 */


@Element(name = "genre")
public class Genre {

    @Element(name ="genre")
    String genre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
