package com.cinemaapp.presenters;

import com.cinemaapp.R;
import com.cinemaapp.models.movies.MovieModel;
import com.cinemaapp.repository.MapperError;
import com.cinemaapp.repository.RepositoryError;
import com.cinemaapp.repository.movies.MoviesRepository;
import com.cinemaapp.views.billboard.IBillboard;

import java.util.ArrayList;

import retrofit.RetrofitError;

/**
 * Created by jasmany on 10/11/2017.
 */

public class MovieModelPresenter extends BasePresenter<IBillboard> {

    private MoviesRepository moviesRepository;

    public MovieModelPresenter() {
        moviesRepository = new MoviesRepository();

    }

    public void createThreadMoviesList() {
        if (getValidateInternet().isConnected()) {
            createThreadMovies();
        } else {
            getView().showAlertDialogInternet(1, 1);
        }
    }

    private void createThreadMovies() {
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
            ArrayList<MovieModel> movieModelArrayList = moviesRepository.getMoviesModel();
            getView().showMoviesList(movieModelArrayList);

        } catch (RetrofitError retrofitError) {

            RepositoryError repositoryError = MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
            getView().showAlertError(R.string.error, repositoryError.getMessage());

        }/*finally {
            getView().hideProgress();
        }*/
    }

}
