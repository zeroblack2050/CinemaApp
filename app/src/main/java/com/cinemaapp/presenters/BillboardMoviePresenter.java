package com.cinemaapp.presenters;

import android.util.Log;

import com.cinemaapp.R;
import com.cinemaapp.models.movies.Movie;
import com.cinemaapp.models.movies.MovieInfo;
import com.cinemaapp.repository.MapperError;
import com.cinemaapp.repository.RepositoryError;
import com.cinemaapp.repository.movies.MoviesRepository;
import com.cinemaapp.views.billboard.IBillboard;

import java.util.ArrayList;

import retrofit.RetrofitError;

/**
 * Created by jasmany on 10/11/2017.
 */

public class BillboardMoviePresenter extends BasePresenter<IBillboard> {

    private MoviesRepository moviesRepository;

    public BillboardMoviePresenter() {
        moviesRepository = new MoviesRepository();

    }

    //Start: Public method to get list movie from billboard view
    public void callThreadToGetMovies() {
        if (getValidateInternet().isConnected()) {
            createThreadToGetMovies();
            Log.i("Pass from here: ","callThreadToGetMovies");
        } else {
            getView().showAlertDialogInternet(1, 1);
        }
        createThreadToGetMovies();
    }

    private void createThreadToGetMovies() {
        // getView().showProgress(R.string.loading_message);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getMoviesList();
            }
        });
        thread.start();

    }



    private void getMoviesList() {

        try {
            Movie movie = moviesRepository.getMoviesModel();
            ArrayList<MovieInfo> movieInfoArrayList = movie.getMovieInfoArrayList();
            getView().showMoviesList(movieInfoArrayList);
        } catch (RetrofitError retrofitError) {

            RepositoryError repositoryError = MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
            getView().showAlertError(R.string.error, repositoryError.getMessage());

        }/*finally {
            getView().hideProgress();
        }*/
    }

}
