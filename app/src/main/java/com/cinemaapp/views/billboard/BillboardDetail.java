package com.cinemaapp.views.billboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;
import android.widget.VideoView;

import com.cinemaapp.R;
import com.cinemaapp.helper.Constants;
import com.cinemaapp.helper.customclasses.CustomButton;
import com.cinemaapp.models.movies.Cast;
import com.cinemaapp.models.movies.Genre;
import com.cinemaapp.models.movies.MovieInfo;
import com.cinemaapp.presenters.BillboardDetailPresenter;
import com.cinemaapp.views.Bases.BaseViews;
import com.cinemaapp.views.maps.MapsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BillboardDetail extends BaseViews<BillboardDetailPresenter> implements IBillboardDetail {

    private Toolbar toolbar;
    private MovieInfo movieInfo;
    private ImageView imageView;
    private TextView title, genre, rating, studio, director, cast, description;
    private VideoView videoView;
    private CustomButton buttonLaunchMap, buttonBackToBillboard;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billboard_detail);
        setPresenter(new BillboardDetailPresenter());
        getPresenter().inject(this, getValidateInternet());
        receiveMovies();
        findComponents();
        setDataComponents();
        loadActions();



    }

    private void loadActions() {
        buttonLaunchMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BillboardDetail.this, MapsActivity.class);
                startActivity(intent);
            }
        });
        buttonBackToBillboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setDataComponents() {


        Picasso.with(this)
                .load(movieInfo.getPoster().getXlarge())
                .resize(220,320)
                .centerCrop()
                .error(R.drawable.image_not_founded)
                .into(imageView);


        title.setText(movieInfo.getInfo().getTitle());
        genre.setText(genreToString(movieInfo.getGenreArrayList()));
        rating.setText(movieInfo.getInfo().getRating());
        studio.setText(movieInfo.getInfo().getStudio());
        director.setText(movieInfo.getInfo().getDirector());
        cast.setText(castToString(movieInfo.getCastArrayList()));

        description.setText(movieInfo.getInfo().getDescription());



        /*MediaController mc = new MediaController(this);
        mc.setMediaPlayer(videoView);
        videoView.setMediaController(mc);
        videoView.setVideoURI(Uri.parse(movieInfo.getPreview().getPreviewLarge().toString()));
        videoView.start();*/




    }

    private void findComponents() {
        //toolbar = findViewById(R.id.detailTtoolbar);
        imageView = findViewById(R.id.detailImageView);
        title = findViewById(R.id.detailTitle);
        genre = findViewById(R.id.detailGenre);
        rating = findViewById(R.id.detailRating);
        studio = findViewById(R.id.detailStudio);
        director = findViewById(R.id.detailDirector);
        cast = findViewById(R.id.detailCast);

        description = findViewById(R.id.detailTextView);
        videoView = findViewById(R.id.detailVideoView);
        buttonLaunchMap = findViewById(R.id.detailButtonToMap);
        buttonBackToBillboard = findViewById(R.id.detailBackButton);


    }

    private void receiveMovies() {
        movieInfo = (MovieInfo) getIntent().getSerializableExtra(Constants.MOVIES_ARRAY_LIST);
    }



    private StringBuffer castToString(ArrayList<Cast> arrayList){

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


    //Methos from BaseViews and IBillboardDetail

    @Override
    public void showToast(int message) {

    }

    @Override
    public void showToast(String message) {

    }
}
