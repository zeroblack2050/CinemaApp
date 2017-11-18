package com.cinemaapp.models.movies;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jasmany on 10/11/2017.
 */

@Element(name = "movieinfo")
public class MovieInfo implements Serializable{

    @SerializedName("id")
    @Attribute(name="id")
    String id;

    @SerializedName("info")
    @Element(name = "info")
    Info info;

    @SerializedName("cast")
    @ElementList(required = false,entry = "cast", inline = true)
    ArrayList<Cast> castArrayList;

    @SerializedName("genre")
    @ElementList(required = false,entry = "genre", inline = true)
    ArrayList<Genre> genreArrayList;

    @SerializedName("poster")
    @Element(name = "poster")
    Poster poster;

    @SerializedName("preview")
    @Element(name = "preview")
    Preview preview;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public ArrayList<Cast> getCastArrayList() {
        return castArrayList;
    }

    public void setCastArrayList(ArrayList<Cast> castArrayList) {
        this.castArrayList = castArrayList;
    }

    public ArrayList<Genre> getGenreArrayList() {
        return genreArrayList;
    }

    public void setGenreArrayList(ArrayList<Genre> genreArrayList) {
        this.genreArrayList = genreArrayList;
    }

    public Poster getPoster() {
        return poster;
    }

    public void setPoster(Poster poster) {
        this.poster = poster;
    }

    public Preview getPreview() {
        return preview;
    }

    public void setPreview(Preview preview) {
        this.preview = preview;
    }
}
