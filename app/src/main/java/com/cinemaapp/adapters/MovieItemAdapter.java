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
import com.cinemaapp.models.movies.Cast;
import com.cinemaapp.models.movies.Genre;
import com.cinemaapp.models.movies.Info;
import com.cinemaapp.models.movies.MovieInfo;
import com.cinemaapp.models.movies.MovieModel;
import com.cinemaapp.models.movies.Poster;
import com.cinemaapp.models.movies.Preview;

import java.util.ArrayList;

/**
 * Created by Superadmin1 on 04/11/2017.
 */

public class MovieItemAdapter extends ArrayAdapter<MovieModel> {

    private ArrayList<MovieModel> movieModelArrayList;
    private ArrayList<MovieInfo> movieInfoArrayList;
    private Activity context;
    private ImageView imageMovieItem;
    private TextView titleMovieItem;
    private TextView genreMovieItem;
    private RatingBar ratingMovieItem;

    private Cast cast;
    private Genre genre;
    private Info info;
    private MovieInfo movieInfo;
    private MovieModel movieModel;
    private Poster poster;
    private Preview preview;







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
        obtainedMoviesInfo(convertView);

        for (int i = 0; i< movieModel.getMovieInfo().size();i++){
            movieInfo = movieModel.getMovieInfo().get(0);
            info = movieInfo.getInfoArrayList().get(0);
            genre = movieInfo.getGenreArrayList().get(0);
            //imageMovieItem.setImageBitmap(movieModel.getImage());
            titleMovieItem.setText(info.getTitle());
            genreMovieItem.setText(genre.getGenre());
            ratingMovieItem.setNumStars(5);
        }
        return convertView;
    }

    public void obtainedMoviesInfo(View convertView){
        movieInfoArrayList = movieModel.getMovieInfo();

    }

    private void loadView(View convertView){
        imageMovieItem = (ImageView) convertView.findViewById(R.id.imageMovieItem);
        titleMovieItem = (TextView) convertView.findViewById(R.id.titleMovieItem);
        genreMovieItem = (TextView) convertView.findViewById(R.id.genreMovieItem);
        ratingMovieItem = (RatingBar) convertView.findViewById(R.id.genreMovieItem);

    }



}
