package com.cinemaapp.views.billboard;

import com.cinemaapp.models.movies.Movie;
import com.cinemaapp.models.movies.MovieInfo;
import com.cinemaapp.views.Bases.IBaseViews;

import java.util.ArrayList;

/**
 * Created by Superadmin1 on 11/11/2017.
 */

public interface IBillboard extends IBaseViews {

    void showMoviesList(ArrayList<MovieInfo> movieInfoArrayList);
    void showAlertDialogInternet(int title, int message);
    void showAlertError(int title, String message);


}
