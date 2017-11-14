package com.cinemaapp.models.movies;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by Superadmin1 on 11/11/2017.
 */

@Root(name = "records")
public class Movie {

    @Element(name="date")
    private String date;

    @ElementList(entry = "movieinfo", inline = true)
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
