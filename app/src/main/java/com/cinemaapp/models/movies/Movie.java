package com.cinemaapp.models.movies;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by Superadmin1 on 11/11/2017.
 */

// @Root(name = "records", strict = false) To map not all content on xml
@Root(name = "records")
public class Movie {

    @Attribute(name="date")
    private String date;

    @ElementList(required = false,entry = "movieinfo", inline = true)
    ArrayList<MovieInfo> movieInfo;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<MovieInfo> getMovieInfo() {
        return movieInfo;
    }

    public void setMovieInfo(ArrayList<MovieInfo> movieInfo) {
        this.movieInfo = movieInfo;
    }
}
