package com.cinemaapp.models.movies;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import java.util.ArrayList;

/**
 * Created by jasmany on 10/11/2017.
 */

@Element(name = "movieinfo")
public class MovieInfo {

    @Element(name = "id")
    String id;

    @ElementList(entry = "info", inline = true)
    ArrayList<Info> infoArrayList;

    @ElementList(entry = "cast", inline = true)
    ArrayList<Cast> castArrayList;

    @ElementList(entry = "genre", inline = true)
    ArrayList<Genre> genreArrayList;

    @ElementList(entry = "poster", inline = true)
    Poster poster;

    @ElementList(entry = "preview", inline = true)
    Preview preview;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Info> getInfoArrayList() {
        return infoArrayList;
    }

    public void setInfoArrayList(ArrayList<Info> infoArrayList) {
        this.infoArrayList = infoArrayList;
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
