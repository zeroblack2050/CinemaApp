package com.cinemaapp.presenters;

import com.cinemaapp.models.cinemas.Cinemas;
import com.cinemaapp.repository.cinemas.CinemasRepository;
import com.cinemaapp.repository.cinemas.ICinemasRepository;
import com.cinemaapp.views.maps.IMapsActivity;

import java.util.ArrayList;

import retrofit.RetrofitError;

/**
 * Created by Superadmin1 on 02/12/2017.
 */

public class MapsPresenter extends BasePresenter<IMapsActivity>{

    private ICinemasRepository cinemasRepository;

    public MapsPresenter() {
        cinemasRepository = new CinemasRepository();
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
            ArrayList<Cinemas> cinemas = cinemasRepository.getCinemasModel();
            getView().getCinemas(cinemas);
            if (cinemas != null){
                //Log.e("Jasmany ", "Llamar al presenter");
            }
        } catch (RetrofitError retrofitError) {

            //RepositoryError repositoryError = MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
            //getView().showAlertError(R.string.error, repositoryError.getMessage());

        }/*finally {
            getView().hideProgress();
        }*/
    }



}
