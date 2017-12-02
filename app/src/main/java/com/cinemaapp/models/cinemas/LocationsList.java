package com.cinemaapp.models.cinemas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Superadmin1 on 02/12/2017.
 */

public class Cinemas implements Serializable {

    @SerializedName("_id")
    @Expose
    private String _id;

    @SerializedName("_v")
    @Expose
    private String _v;


}
