package com.cinemaapp.models.movies;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Superadmin1 on 11/11/2017.
 */

// @Root(name = "records", strict = false) To map not all content on xml
@Root(name = "records")
public class Movie implements Serializable{

    @SerializedName("date")
    @Attribute(name="date")
    private String date;

    @SerializedName("movieinfo")
    @ElementList(required = false,entry = "movieinfo", inline = true)
    ArrayList<MovieInfo> movieInfoArrayList;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<MovieInfo> getMovieInfoArrayList() {
        return movieInfoArrayList;
    }

    public void setMovieInfoArrayList(ArrayList<MovieInfo> movieInfo) {
        this.movieInfoArrayList = movieInfo;
    }
}
