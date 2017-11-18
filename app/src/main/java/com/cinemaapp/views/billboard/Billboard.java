package com.cinemaapp.views.billboard;

import android.content.Intent;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cinemaapp.R;
import com.cinemaapp.helper.Constants;
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

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().callThreadToGetMovies();
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



    public void callMovieAdapter(final ArrayList<MovieInfo> movieInfoArrayList) {
        movieItemAdapter = new MovieItemAdapter(this, R.id.billboardAppListViewMovieItem, movieInfoArrayList);
        listViewListBillboard.setAdapter(movieItemAdapter);
        listViewListBillboard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long ld) {
                /*Intent intent = new Intent(Billboard.this, BillboardDetail.class);
                intent.putExtra(Constants.ARRAY_MOVIES,movieInfoArrayList.get(position));
                startActivity(intent);*/
                Toast.makeText(Billboard.this, "En construción", Toast.LENGTH_LONG).show();

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
    public void showMoviesList(final ArrayList<MovieInfo> movieInfoArrayList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //progress.hide();
                callMovieAdapter(movieInfoArrayList);
            }
        });
    }

    @Override
    public void showAlertDialogInternet(int title, int message) {

    }

    @Override
    public void showAlertError(int title, String message) {

    }
    //End: Methods heritage

}
