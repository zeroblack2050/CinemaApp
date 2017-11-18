package com.cinemaapp.models.movies;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Element;

import java.io.Serializable;

/**
 * Created by Superadmin1 on 11/11/2017.
 */

@Element(name = "info")
public class Info implements Serializable{

    @SerializedName("title")
    @Element(name ="title")
    private String title;

    @SerializedName("runtime")
    @Element(name ="runtime")
    private String runtime;

    @SerializedName("rating")
    @Element(name ="rating")
    private String rating;

    @SerializedName("studio")
    @Element(name ="studio")
    private String studio;

    @SerializedName("postdate")
    @Element(name ="postdate")
    private String postdate;

    @SerializedName("releasedate")
    @Element(required = false,name ="releasedate")
    private String releasedate;

    @SerializedName("copyright")
    @Element(name ="copyright")
    private String copyright;

    @SerializedName("director")
    @Element(name ="director")
    private String director;

    @SerializedName("description")
    @Element(name ="description")
    private String description;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getPostdate() {
        return postdate;
    }

    public void setPostdate(String postdate) {
        this.postdate = postdate;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
