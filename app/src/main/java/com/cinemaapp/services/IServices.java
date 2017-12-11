package com.cinemaapp.services;

import com.cinemaapp.models.cinemas.Cinemas;
import com.cinemaapp.models.movies.Movie;

import java.util.ArrayList;

import retrofit.http.GET;

/**
 * Created by leidyzulu on 16/09/17.
 */

public interface IServices {

    @GET("/current.xml")
    Movie getMoviesModel();

    @GET("/cinemas")
    ArrayList<Cinemas> getCinemasModel();
}
