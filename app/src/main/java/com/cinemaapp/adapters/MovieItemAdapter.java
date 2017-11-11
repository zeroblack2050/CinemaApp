package com.cinemaapp.adapters;


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
import com.cinemaapp.models.MovieModel;

import java.util.ArrayList;

/**
 * Created by Superadmin1 on 04/11/2017.
 */

public class MovieItemAdapter extends ArrayAdapter<MovieModel> {

    private ArrayList<MovieModel> movieModelArrayList;
    private Activity context;
    private MovieModel movieModel;
    private ImageView imageMovieItem;
    private TextView titleMovieItem;
    private TextView genreMovieItem;
    private RatingBar ratingMovieItem;


    public MovieItemAdapter(Activity context, int resource, ArrayList<MovieModel> movieModel) {
        super(context, resource, movieModel);
        this.context = context;
        this.movieModelArrayList = movieModel;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_movie_items, parent, false);
        this.movieModel = this.movieModelArrayList.get(position);
        loadView(convertView);
        //imageMovieItem.setImageBitmap(movieModel.getImage());
        titleMovieItem.setText(movieModel.getTitle());
        genreMovieItem.setText(movieModel.getGenre());
        ratingMovieItem.setNumStars(movieModel.getRating());
        return convertView;
    }

    private void loadView(View convertView){
        imageMovieItem = (ImageView) convertView.findViewById(R.id.imageMovieItem);
        titleMovieItem = (TextView) convertView.findViewById(R.id.titleMovieItem);
        genreMovieItem = (TextView) convertView.findViewById(R.id.genreMovieItem);
        ratingMovieItem = (RatingBar) convertView.findViewById(R.id.genreMovieItem);

    }



}
