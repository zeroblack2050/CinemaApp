package com.cinemaapp.views.billboard;

import android.support.v4.widget.ContentLoadingProgressBar;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.cinemaapp.R;
import com.cinemaapp.models.movies.Movie;
import com.cinemaapp.models.movies.MovieInfo;
import com.cinemaapp.presenters.MoviePresenter;
import com.cinemaapp.views.Bases.BaseViews;
import com.cinemaapp.views.adapters.MovieItemAdapter;

import java.util.ArrayList;

public class Billboard extends BaseViews<MoviePresenter> implements IBillboard {

    private ListView listViewListBillboard;
    private ProgressBar progressBarBillboard;
    private MovieItemAdapter movieItemAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billboard);
        setPresenter(new MoviePresenter());
        getPresenter().inject(this,getValidateInternet());
        loadComponents();
        instanceObjects();
        loadEvents();


    }





    //Start: Own methods
    private void loadEvents() {

    }

    public void instanceObjects() {
        //movieItemAdapter = new MovieItemAdapter();
    }

    public void loadComponents() {
        listViewListBillboard = (ListView) findViewById(R.id.billboardAppListViewMovieItem);
        //progressBarBillboard = (ProgressBar) findViewById(R.id.billboardAppProgressBar);
    }



    public void callMovieAdapter(final ArrayList<MovieInfo> movieArrayList) {
        movieItemAdapter = new MovieItemAdapter(this, R.id.billboardAppListViewMovieItem, movieArrayList);
        listViewListBillboard.setAdapter(movieItemAdapter);
        listViewListBillboard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }


    //End: Own methods





    //Start: Methods from BaseViews and IbaseViews
    @Override
    public void showProgress(int message) {

    }

    @Override
    public void hideProgress() {

    }
    //End: Methods from BaseViews and IBaseViews




    //Start: Methods heritage

    @Override
    public void showMoviesList(Movie movie) {

    }

    @Override
    public void showAlertDialogInternet(int title, int message) {

    }

    @Override
    public void showAlertError(int title, String message) {

    }
    //End: Methods heritage

}
