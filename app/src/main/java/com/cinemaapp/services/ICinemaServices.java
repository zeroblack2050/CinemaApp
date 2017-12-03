package com.cinemaapp.services;

import com.cinemaapp.models.cinemas.Cinemas;

import java.util.ArrayList;

import retrofit.http.GET;

/**
 * Created by jasmany on 2/12/2017.
 */

public interface ICinemaServices {

    @GET("/cinemas")
    ArrayList<Cinemas> getCinemasModel();
}
