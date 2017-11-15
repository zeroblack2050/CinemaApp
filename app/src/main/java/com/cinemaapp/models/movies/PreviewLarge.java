package com.cinemaapp.models.movies;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Text;

/**
 * Created by Superadmin1 on 14/11/2017.
 */

@Element(name = "large")
public class PreviewLarge {

    @Text
    private String content;

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
