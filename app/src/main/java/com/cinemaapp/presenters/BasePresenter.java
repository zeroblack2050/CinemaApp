package com.cinemaapp.presenters;

import com.cinemaapp.helper.IValidateInternet;
import com.cinemaapp.views.Bases.IBaseViews;

/**
 * Created by jasmany on 10/11/2017.
 */

public class BasePresenter<T extends IBaseViews> {

    private T view;
    private IValidateInternet validateInternet;

    public void inject(T view, IValidateInternet iValidateInternet) {
        this.view = view;
        this.validateInternet = this.validateInternet;
    }

    public T getView() {
        return view;
    }

    public IValidateInternet getValidateInternet() {
        return validateInternet;
    }

}
