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

public class MovieItemAdapter extends ArrayAdapter<Movie> {

    private ArrayList<Movie> movieArrayList;
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
    private Movie movie;
    private Poster poster;
    private Preview preview;







    public MovieItemAdapter(Activity context, int resource, ArrayList<Movie> movie) {
        super(context, resource, movie);
        this.context = context;
        this.movieArrayList = movie;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_movie_items, parent, false);
        this.movie = this.movieArrayList.get(position);
        loadView(convertView);
        obtainedMoviesInfo(convertView);

        for (int i = 0; i< movie.getMovieInfo().size(); i++){
            movieInfo = movie.getMovieInfo().get(0);
            info = movieInfo.getInfoArrayList().get(0);
            genre = movieInfo.getGenreArrayList().get(0);
            //imageMovieItem.setImageBitmap(movie.getImage());
            titleMovieItem.setText(info.getTitle());
            genreMovieItem.setText(genre.getGenre());
            ratingMovieItem.setNumStars(3);
        }
        return convertView;
    }

    public void obtainedMoviesInfo(View convertView){
        movieInfoArrayList = movie.getMovieInfo();

    }

    private void loadView(View convertView){
        imageMovieItem = (ImageView) convertView.findViewById(R.id.imageMovieItem);
        titleMovieItem = (TextView) convertView.findViewById(R.id.titleMovieItem);
        genreMovieItem = (TextView) convertView.findViewById(R.id.genreMovieItem);
        ratingMovieItem = (RatingBar) convertView.findViewById(R.id.genreMovieItem);

    }



}
