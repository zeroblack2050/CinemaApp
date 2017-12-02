package com.cinemaapp.repository.cinemas;

import com.cinemaapp.helper.ServicesFactory;
import com.cinemaapp.helper.TypeDecryption;
import com.cinemaapp.models.cinemas.Cinemas;
import com.cinemaapp.services.IServices;

import retrofit.RetrofitError;

/**
 * Created by Superadmin1 on 02/12/2017.
 */

public class CinemasRepository implements ICinemasRepository {

    private IServices services;

    public CinemasRepository() {
        ServicesFactory servicesFactory = new ServicesFactory(TypeDecryption.JSON);
        services = (IServices) servicesFactory.getInstance(IServices.class);
    }

    @Override
    public Cinemas getCinemasModel() throws RetrofitError {
        Cinemas cinemas = services.getCinmeasModel();
        return cinemas;
    }
}
