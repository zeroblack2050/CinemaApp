package com.cinemaapp.views.adapters;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cinemaapp.R;
import com.cinemaapp.models.movies.Cast;
import com.cinemaapp.models.movies.Genre;
import com.cinemaapp.models.movies.Info;
import com.cinemaapp.models.movies.Movie;
import com.cinemaapp.models.movies.MovieInfo;
import com.cinemaapp.models.movies.Poster;
import com.cinemaapp.models.movies.Preview;

import java.util.ArrayList;

/**
 * Created by Superadmin1 on 04/11/2017.
 */

public class MovieItemAdapter extends ArrayAdapter<MovieInfo> {


    private Activity context;

    private ImageView imageMovieItem;
    private TextView titleMovieItem;
    private TextView genreMovieItem;
    private TextView ratingMovieItem;
    //
    private ArrayList<Genre> genreArrayList;
    private Genre genre;
    private Info info;
    private ArrayList<MovieInfo> movieInfoArrayList;
    private MovieInfo movieInfo;

    public MovieItemAdapter(Activity context, int resource, ArrayList<MovieInfo> movieInfo) {
        super(context, resource, movieInfo);
        this.context = context;
        this.movieInfoArrayList = movieInfo;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_movie_items, parent, false);
        this.movieInfo = this.movieInfoArrayList.get(position);
        this.info = this.movieInfo.getInfo();
        this.genreArrayList = this.movieInfo.getGenreArrayList();
        this.genre = this.genreArrayList.get(0);
        loadView(convertView);

        titleMovieItem.setText(info.getTitle());
        genreMovieItem.setText(genre.getName().get(0));
        ratingMovieItem.setText(info.getRating());


        return convertView;
    }


    private void loadView(View convertView){
        imageMovieItem = (ImageView) convertView.findViewById(R.id.imageMovieItem);
        titleMovieItem = (TextView) convertView.findViewById(R.id.titleMovieItem);
        genreMovieItem = (TextView) convertView.findViewById(R.id.genreMovieItem);
        ratingMovieItem = (TextView) convertView.findViewById(R.id.genreMovieItem);

    }



}
