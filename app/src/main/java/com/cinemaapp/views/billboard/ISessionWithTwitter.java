package com.cinemaapp.views.billboard;

import com.cinemaapp.views.Bases.IBaseViews;

/**
 * Created by jasmany on 28/11/2017.
 */

public interface ISessionWithTwitter extends IBaseViews {

    void showToast(int message);
    void showToast(String message);

}
