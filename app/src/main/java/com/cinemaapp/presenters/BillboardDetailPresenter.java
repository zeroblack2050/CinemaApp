package com.cinemaapp.presenters;

import android.util.Log;

import com.cinemaapp.models.cinemas.Cinemas;
import com.cinemaapp.repository.cinemas.ICinemasRepository;
import com.cinemaapp.views.billboard.IBillboardDetail;

import java.util.ArrayList;

import retrofit.RetrofitError;

/**
 * Created by jasmany on 19/11/2017.
 */

public class BillboardDetailPresenter extends BasePresenter<IBillboardDetail> {

    private ICinemasRepository cinemasRepository;

    public BillboardDetailPresenter(ICinemasRepository iCinemasRepository) {
        this.cinemasRepository = iCinemasRepository;
    }


    public void getThreadCinemasList(){
        if(getValidateInternet().isConnected()){
            createThreadGetCinemas();
            Log.e("Jasmany presenter ", "internet");
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
                Log.e("Jasmany presenter ", "call to detail presenter");
            }
        } catch (RetrofitError retrofitError) {

            //RepositoryError repositoryError = MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
            //getView().showAlertError(R.string.error, repositoryError.getMessage());

        }/*finally {
            getView().hideProgress();
        }*/
    }
}
