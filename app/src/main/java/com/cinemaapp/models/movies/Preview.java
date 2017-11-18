package com.cinemaapp.models.movies;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.io.Serializable;

/**
 * Created by Superadmin1 on 11/11/2017.
 */

@Element(name = "preview")
public class Preview implements Serializable{

    @SerializedName("large")
    @Element(name = "large")
    PreviewLarge previewLarge;

    public PreviewLarge getPreviewLarge() {
        return previewLarge;
    }

    public void setPreviewLarge(PreviewLarge previewLarge) {
        this.previewLarge = previewLarge;
    }
}
