package com.cinemaapp.models.movies;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.ArrayList;


/**
 * Created by Superadmin1 on 11/11/2017.
 */


@Element(name = "cast")
public class Cast {

    @ElementList(entry ="name", inline = true)
    ArrayList<String> name;

    public ArrayList<String> getName() {
        return name;
    }

    public void setName(ArrayList<String> name) {
        this.name = name;
    }
}
