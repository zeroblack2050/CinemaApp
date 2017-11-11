package com.cinemaapp.models;

import android.widget.RatingBar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jasmany on 10/11/2017.
 */

public class MovieModel implements Serializable{

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("director")
    @Expose
    private String director;

    @SerializedName("cast")
    @Expose
    private String cast;

    @SerializedName("genre")
    @Expose
    private String genre;

    @SerializedName("synopsis")
    @Expose
    private String synopsis;

    @SerializedName("rating")
    @Expose
    private int rating;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
