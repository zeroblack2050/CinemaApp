package com.cinemaapp.repository.movies;

import com.cinemaapp.models.movies.MovieInfo;
import com.cinemaapp.models.movies.MovieModel;

import java.util.ArrayList;

/**
 * Created by Superadmin1 on 11/11/2017.
 */

public interface IMoviesRepository {

    public ArrayList<MovieModel> getMoviesModel();
}
