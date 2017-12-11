package com.cinemaapp.views.billboard;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.cinemaapp.R;
import com.cinemaapp.helper.Constants;
import com.cinemaapp.helper.CustomSharedPreferences;
import com.cinemaapp.helper.customclasses.CustomButton;
import com.cinemaapp.models.movies.Cast;
import com.cinemaapp.models.movies.Genre;
import com.cinemaapp.models.movies.MovieInfo;
import com.cinemaapp.presenters.BillboardDetailPresenter;
import com.cinemaapp.views.Bases.BaseViews;
import com.cinemaapp.views.maps.MapsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tourguide.tourguide.ChainTourGuide;
import tourguide.tourguide.Overlay;
import tourguide.tourguide.Sequence;
import tourguide.tourguide.ToolTip;

public class BillboardDetail extends BaseViews<BillboardDetailPresenter> implements IBillboardDetail {

    private MovieInfo movieInfo;
    private ImageView imageView;
    private TextView title, genre, rating, studio, director, cast, description;
    private VideoView videoView;
    private CustomButton buttonLaunchMap, buttonBackToBillboard;
    private Intent intent;;

    private AlphaAnimation enterAnimation, exitAnimation;
    private Sequence sequence;
    private LinearLayout linearBasic,linearSynopsis;
    private Toolbar toolbar;
    private CustomSharedPreferences sharedPreferencesDetail;
    
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

        initAnimationTour();
        runAnimationTour();


    }

    @Override
    protected void onResume() {
        super.onResume();
        setPresenter(new BillboardDetailPresenter());
        getPresenter().inject(this, getValidateInternet());
    }

    private void loadActions() {
        buttonLaunchMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(BillboardDetail.this, MapsActivity.class);
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


    /*
    ** Init methods Tour Guide
    **/

    private void initAnimationTour() {
        enterAnimation = new AlphaAnimation(0f, 1f);
        enterAnimation.setDuration(600);
        enterAnimation.setFillAfter(true);
        enterAnimation.setInterpolator(new BounceInterpolator());
        exitAnimation = new AlphaAnimation(0f, 1f);
        exitAnimation.setDuration(600);
        exitAnimation.setFillAfter(true);
    }

    private void runAnimationTour() {

        ChainTourGuide tourGuideBasics = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        })
                        .setTitle(getString(R.string.billboard_detail_basics))
                        .setDescription(getString(R.string.billboard_detail_basics_descriptions))
                        .setGravity(Gravity.BOTTOM)

                )
                .playLater(linearBasic);

        ChainTourGuide tourGuideSynopsis = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        })
                        .setTitle(getString(R.string.billboard_detail_synopsis_tour))
                        .setDescription(getString(R.string.billboard_detail_synopsis_description))
                        .setGravity(Gravity.BOTTOM)

                )
                .playLater(linearSynopsis);

        ChainTourGuide tourGuideMaps = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        })
                        .setTitle(getString(R.string.billboard_detail_maps))
                        .setDescription(getString(R.string.billboard_detail_maps_description))
                        .setGravity(Gravity.TOP)

                )
                .playLater(buttonLaunchMap);


        sequence = new Sequence.SequenceBuilder()
                .add(tourGuideBasics,tourGuideSynopsis,tourGuideMaps)
                .setDefaultOverlay(new Overlay()
                        .setEnterAnimation(enterAnimation)
                        .setExitAnimation(exitAnimation)

                ).setDefaultPointer(null)
                .setContinueMethod(Sequence.ContinueMethod.Overlay)
                .build();

        if (sharedPreferencesDetail.getTourGuide(Constants.DETAIL_TOUR_GUIDE_KEY) != Constants.TOUR_GUIDE_MADE){
            ChainTourGuide.init(this).playInSequence(sequence);
            sharedPreferencesDetail.saveTourGuide(Constants.DETAIL_TOUR_GUIDE_KEY,Constants.TOUR_GUIDE_MADE);
        }
        //sharedPreferencesDetail.deleteTourGuide(Constants.DETAIL_TOUR_GUIDE_KEY);

    }


    //Start: Option Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_detail_tour_guide){
            sharedPreferencesDetail.deleteTourGuide(Constants.DETAIL_TOUR_GUIDE_KEY);
            runAnimationTour();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_billboard_detail,menu);

        return super.onCreateOptionsMenu(menu);
    }
    //End: Option Menu




    /*
    ** End methods Tour Guide
    **/





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



        MediaController mc = new MediaController(this);
        mc.setMediaPlayer(videoView);
        videoView.setMediaController(mc);
        videoView.setVideoURI(Uri.parse(movieInfo.getPreview().getPreviewLarge().toString()));
        videoView.start();




    }

    private void findComponents() {

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

        sharedPreferencesDetail = new CustomSharedPreferences(this);
        toolbar = findViewById(R.id.detailToolbar);
        toolbar.setTitle(Constants.EMPTY);
        setSupportActionBar(toolbar);
        linearBasic = findViewById(R.id.billboard_detail_linear_basic);
        linearSynopsis = findViewById(R.id.billboard_detail_linear_synopsis);

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

}
