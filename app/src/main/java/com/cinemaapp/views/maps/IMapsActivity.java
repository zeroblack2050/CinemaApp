package com.cinemaapp.views.maps;

import com.cinemaapp.models.cinemas.Cinemas;
import com.cinemaapp.views.Bases.IBaseViews;

import java.util.ArrayList;

/**
 * Created by Superadmin1 on 02/12/2017.
 */

public interface IMapsActivity extends IBaseViews {

    void getCinemasMaps(ArrayList<Cinemas> cinemas);

}
