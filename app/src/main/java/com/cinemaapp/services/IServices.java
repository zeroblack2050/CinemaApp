package com.cinemaapp.services;

import com.cinemaapp.models.movies.Movie;

import retrofit.http.GET;

/**
 * Created by leidyzulu on 16/09/17.
 */

public interface IServices {

    /*@GET("/products")
    ArrayList<Product> getProductList();

    @POST("/products")
    Product createProduct(@Body Product product);

    @DELETE("/products/{id}")
    DeleteResponse deleteProduct(@Path("id") String id);

    @GET("/user/auth")
    User login(@Query("email") String user, @Query("password") String password);


    @GET("/user")
    User autoLogin(@Header("Authorization") String token);

    @GET("/note.xml")
    Note getNote();

    @GET("/simple.xml")
    BreakFastMenu getBreakFastMenu();*/

    @GET("/current.xml")
    Movie getMoviesModel();
}
