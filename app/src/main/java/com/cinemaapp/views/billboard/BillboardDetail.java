package com.cinemaapp.views.billboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cinemaapp.R;
import com.cinemaapp.helper.Constants;
import com.cinemaapp.models.movies.MovieInfo;

import java.util.ArrayList;

public class BillboardDetail extends AppCompatActivity {

    ArrayList<MovieInfo> movieInfoArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billboard_detail);


        movieInfoArrayList = (ArrayList<MovieInfo>) getIntent().getSerializableExtra(Constants.ARRAY_MOVIES);

    }
}
