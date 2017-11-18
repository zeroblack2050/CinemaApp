package com.cinemaapp.models.movies;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by Superadmin1 on 11/11/2017.
 */


@Element(name = "genre")
public class Genre implements Serializable{

    @SerializedName("name")
    @ElementList(entry= "name", inline=true)
    ArrayList<String> name;



    public ArrayList<String> getName() {
        return name;
    }

    public void setName(ArrayList<String> name) {
        this.name = name;
    }

}
