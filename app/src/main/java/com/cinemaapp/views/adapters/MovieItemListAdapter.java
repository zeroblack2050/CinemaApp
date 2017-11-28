package com.cinemaapp.views.adapters;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cinemaapp.R;
import com.cinemaapp.models.movies.Genre;
import com.cinemaapp.models.movies.MovieInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Superadmin1 on 04/11/2017.
 */

public class MovieItemListAdapter extends ArrayAdapter<MovieInfo> {


    private Activity context;

    private ImageView imageMovieItem;
    private TextView titleMovieItem;
    private TextView genreMovieItem;
    private TextView studioMovieItem;
    private TextView ratingMovieItem;
    private ArrayList<MovieInfo> movieInfoArrayList;
    private MovieInfo movieInfo;


    public MovieItemListAdapter(Activity context, int resource, ArrayList<MovieInfo> movieInfoArrayList) {
        super(context, resource, movieInfoArrayList);
        this.context = context;
        this.movieInfoArrayList = movieInfoArrayList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_items_listview, parent, false);
        loadView(convertView);
        this.movieInfo = this.movieInfoArrayList.get(position);

        Picasso.with(context)
                .load(movieInfo.getPoster().getXlarge())
                .resize(160, 230)
                .centerCrop()
                .error(R.drawable.image_not_founded)
                .into(imageMovieItem);
        titleMovieItem.setText(movieInfo.getInfo().getTitle());
        genreMovieItem.setText(genreToString(movieInfo.getGenreArrayList()));
        studioMovieItem.setText(movieInfo.getInfo().getStudio());
        ratingMovieItem.setText(movieInfo.getInfo().getRating());


        return convertView;
    }


    private StringBuffer genreToString(ArrayList<Genre> arrayList){

        StringBuffer endString = new StringBuffer("");

        for (int i = 0; i < arrayList.size();i++){
            for (int j = 0; j < arrayList.get(i).getName().size();j++){
                endString.append(arrayList.get(i).getName().get(j));
                if (j<arrayList.get(i).getName().size()-1){
                    endString.append(", ");
                }
            }




        }
        return endString;
    }

    public void commaOrPoint(){}

    private void loadView(View convertView){
        imageMovieItem = (ImageView) convertView.findViewById(R.id.imageMovieItem);
        titleMovieItem = (TextView) convertView.findViewById(R.id.titleMovieItem);
        genreMovieItem = (TextView) convertView.findViewById(R.id.genreMovieItem);
        studioMovieItem = (TextView) convertView.findViewById(R.id.studioMovieItem);
        ratingMovieItem = (TextView) convertView.findViewById(R.id.ratingMovieItem);

    }



}
