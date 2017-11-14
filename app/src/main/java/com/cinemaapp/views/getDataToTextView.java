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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data_to_text_view);
        textView = (TextView) findViewById(R.id.checkMovieModel);
        moviesRepository = new MoviesRepository();
        loadEvents();
    }

    private void loadEvents() {
        ArrayList<Movie> movieArray = moviesRepository.getMoviesModel();
        for (int i = 0; i < 2;i++) {
            Movie movie = movieArray.get(i);
            ArrayList<MovieInfo> movieInfoArray = movie.getMovieInfo();

            for (int j =0 ; j<2;j++){
                MovieInfo movieInfo = movieInfoArray.get(j);
                Info info = movieInfo.getInfoArrayList().get(0);
                Cast cast = movieInfo.getCastArrayList().get(0);
                Genre genre = movieInfo.getGenreArrayList().get(0);
                Poster poster = movieInfo.getPoster();
                Preview preview = movieInfo.getPreview();
                StringBuffer a = new StringBuffer("");
                a.append("Title: "+info.getTitle()+"\n");
                //a.append("Runtime: "+info.getRuntime()+"\n");
                //a.append("Rating: "+info.getRating()+"\n");
                //a.append("Studio: "+info.getStudio()+"\n");
                //a.append("Postdate: "+info.getPostdate()+"\n");
                //a.append("Release date: "+info.getReleasedate()+"\n");
                //a.append("Copyright: "+info.getCopyright()+"\n");
                //a.append("Director: "+info.getDirector()+"\n");
                //a.append("Description: "+info.getDescription()+"\n");

                //a.append("Cast: "+cast.getCast()+"\n");
                //a.append("Genre: "+genre.getGenre()+"\n");
                //a.append("Poster l: "+poster.getLocation()+"\n");
                //a.append("Poster x: "+poster.getXlarge()+"\n");
                //a.append("Preview: "+preview.getLarge()+"\n");

                textView.setText(a);

            }




            //Log.i("BreakFastMenu",a.toString());

        }
    }
}
