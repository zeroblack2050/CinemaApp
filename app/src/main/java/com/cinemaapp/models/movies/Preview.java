package com.cinemaapp.models.movies;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Created by Superadmin1 on 11/11/2017.
 */

@Element(name = "preview")
public class Preview {



    @Attribute(name="filesize")
    private String filesice;


    @Element(name ="large")
    String large;

    public String getFilesice() {
        return filesice;
    }

    public void setFilesice(String filesice) {
        this.filesice = filesice;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}
