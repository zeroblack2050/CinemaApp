package com.cinemaapp.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.cinemaapp.R;
import com.cinemaapp.models.movies.Cast;
import com.cinemaapp.models.movies.Genre;
import com.cinemaapp.models.movies.Info;
import com.cinemaapp.models.movies.Movie;
import com.cinemaapp.models.movies.MovieInfo;
import com.cinemaapp.models.movies.Poster;
import com.cinemaapp.models.movies.Preview;
import com.cinemaapp.repository.movies.MoviesRepository;

import java.util.ArrayList;

public class getDataToTextView extends AppCompatActivity {

    TextView textView;
    MoviesRepository moviesRepository;
    Movie movieArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data_to_text_view);
        textView = findViewById(R.id.checkMovieModel);
        moviesRepository = new MoviesRepository();
        callRepository();


    }

    public void callRepository(){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Movie movieArray = moviesRepository.getMoviesModel();
                getmovie(movieArray);
                Movie movie = movieArray;
                ArrayList<MovieInfo> movieInfoArray = movie.getMovieInfo();
                MovieInfo movieInfo = movieInfoArray.get(0);
                Info info = movieInfo.getInfo();
                Cast cast = movieInfo.getCastArrayList().get(0);
                Genre genre = movieInfo.getGenreArrayList().get(0);
                Poster poster = movieInfo.getPoster();
                Preview preview = movieInfo.getPreview();
                StringBuffer a = new StringBuffer();
                a.append("Title: "+info.getTitle().toString()+"\n");
                Log.i("Title: ",info.getTitle().toString());

            }
        });
        thread.start();
    }

    public void getmovie(Movie movie){
        movieArray = movie;
        if (movie != null) {
            loadEvents();
        }

    }

    private void loadEvents() {


        //textView.setText("Prueba"+a);
        //Log.i("Title: ",info.getTitle().toString());


    }


}
