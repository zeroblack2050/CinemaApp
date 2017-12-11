package com.cinemaapp.presenters;

import android.util.Log;

import com.cinemaapp.helper.TypeDecryption;
import com.cinemaapp.models.cinemas.Cinemas;
import com.cinemaapp.repository.movies.MoviesRepository;
import com.cinemaapp.views.maps.IMapsActivity;

import java.util.ArrayList;

import retrofit.RetrofitError;

/**
 * Created by Superadmin1 on 02/12/2017.
 */

public class MapsPresenter extends BasePresenter<IMapsActivity>{

    private MoviesRepository moviesRepository;

    public MapsPresenter() {
        moviesRepository = new MoviesRepository(TypeDecryption.JSON);
    }


    public void getThreadCinemasList(){
        if(getValidateInternet().isConnected()){
            createThreadGetCinemas();
        }else {

        }
    }

    private void createThreadGetCinemas() {
        // getView().showProgress(R.string.loading_message);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getCinemasFromServices();
            }
        });
        thread.start();
    }


    private void getCinemasFromServices() {
        try {
            ArrayList<Cinemas> cinemas = moviesRepository.getCinemas();
            getView().getCinemasMaps(cinemas);
            Log.e("Maps P", "Leego " + cinemas.size());
        } catch (RetrofitError retrofitError) {

            //RepositoryError repositoryError = MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
            //getView().showAlertError(R.string.error, repositoryError.getMessage());

        }/*finally {
            getView().hideProgress();
        }*/
    }



}
