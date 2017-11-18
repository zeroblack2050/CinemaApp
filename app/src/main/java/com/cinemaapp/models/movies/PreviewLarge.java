package com.cinemaapp.models.movies;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Text;

import java.io.Serializable;

/**
 * Created by Superadmin1 on 14/11/2017.
 */

@Element(name = "large")
public class PreviewLarge implements Serializable{

    @Text
    private String content;

    @SerializedName("filesize")
    @Attribute(name = "filesize")
    private String filesize;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }
}
