package com.cinemaapp.views.billboard;

import com.cinemaapp.models.cinemas.Cinemas;
import com.cinemaapp.views.Bases.IBaseViews;

import java.util.ArrayList;

/**
 * Created by jasmany on 19/11/2017.
 */

public interface IBillboardDetail extends IBaseViews{

    void getCinemas(ArrayList<Cinemas> cinemas);
}
