package com.cinemaapp.repository.cinemas;

import android.util.Log;

import com.cinemaapp.helper.ServicesFactory;
import com.cinemaapp.helper.TypeDecryption;
import com.cinemaapp.models.cinemas.Cinemas;
import com.cinemaapp.services.ICinemaServices;

import java.util.ArrayList;

import retrofit.RetrofitError;

/**
 * Created by Superadmin1 on 02/12/2017.
 */

public class CinemasRepository implements ICinemasRepository {

    private ICinemaServices services;

    public CinemasRepository() {
        ServicesFactory servicesFactory = new ServicesFactory(TypeDecryption.JSON);
        services = (ICinemaServices) servicesFactory.getInstance(ICinemaServices.class);
        Log.e("Jasmany ", "call to servicesFactory_FromCinemasRepository");
    }

    @Override
    public ArrayList<Cinemas> getCinemasModel() throws RetrofitError {
        ArrayList<Cinemas> cinemas = services.getCinemasModel();
        Log.e("Jasmany ", "call to getCinemasModel_FromCinemasRepository ");
        return cinemas;
    }
}
