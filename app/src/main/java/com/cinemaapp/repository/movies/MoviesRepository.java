package com.cinemaapp.repository.movies;

import com.cinemaapp.helper.ServicesFactory;
import com.cinemaapp.helper.TypeDecryption;
import com.cinemaapp.models.movies.Movie;
import com.cinemaapp.services.IServices;

import java.util.ArrayList;

import retrofit.RetrofitError;

/**
 * Created by Superadmin1 on 11/11/2017.
 */

public class MoviesRepository implements IMoviesRepository {

    private IServices services;

    public MoviesRepository() {
        ServicesFactory servicesFactory = new ServicesFactory(TypeDecryption.XML);
        services = (IServices) servicesFactory.getInstance(IServices.class);
    }

    @Override
    public Movie getMoviesModel() throws RetrofitError {
        Movie movie = services.getMoviesModel();
        return movie;
    }
}
