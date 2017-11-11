package com.cinemaapp.views.Bases;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cinemaapp.helper.IValidateInternet;
import com.cinemaapp.helper.ShowAlertDialog;
import com.cinemaapp.helper.ValidateInternet;
import com.cinemaapp.presenters.BasePresenter;

/**
 * Created by jasmany on 10/11/2017.
 */

public class BaseViews <T extends BasePresenter> extends AppCompatActivity implements IBaseViews{

    private IValidateInternet validateInternet;
    private ProgressDialog progressDialog;
    private T presenter;
    private ShowAlertDialog showAlertDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        validateInternet = new ValidateInternet(BaseViews.this);
        this.showAlertDialog = new ShowAlertDialog(this);

    }

    public ShowAlertDialog getShowAlertDialog() {
        return showAlertDialog;
    }

    @Override
    public void showProgress(int message) {
        progressDialog.setMessage(getResources().getString(message));
        progressDialog.show();
    }

    public void createProgressDialog(){
        this.progressDialog = new ProgressDialog(this);
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    public IValidateInternet getValidateInternet() {
        return validateInternet;
    }


    public T getPresenter() {
        return presenter;
    }

    public void setPresenter(T presenter) {
        this.presenter = presenter;
    }


}
