package com.cinemaapp.models.movies;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Created by Superadmin1 on 11/11/2017.
 */

@Element(name = "preview")
public class Preview {

    @Element(name = "large")
    PreviewLarge previewLarge;

    public PreviewLarge getPreviewLarge() {
        return previewLarge;
    }

    public void setPreviewLarge(PreviewLarge previewLarge) {
        this.previewLarge = previewLarge;
    }
}
