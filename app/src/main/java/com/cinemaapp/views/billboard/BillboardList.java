package com.cinemaapp.views.billboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.BounceInterpolator;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cinemaapp.App;
import com.cinemaapp.R;
import com.cinemaapp.helper.Constants;
import com.cinemaapp.helper.CustomSharedPreferences;
import com.cinemaapp.models.movies.MovieInfo;
import com.cinemaapp.presenters.BillboardMoviePresenter;
import com.cinemaapp.views.Bases.BaseViews;
import com.cinemaapp.views.adapters.MovieItemListAdapter;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;

import tourguide.tourguide.ChainTourGuide;
import tourguide.tourguide.Overlay;
import tourguide.tourguide.Sequence;
import tourguide.tourguide.ToolTip;

public class BillboardList extends BaseViews<BillboardMoviePresenter> implements IBillboard {

    private ArrayList<MovieInfo> movieInfoArrayList;
    private ListView listViewListBillboard;
    private android.support.v7.widget.Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private ContentLoadingProgressBar progressBarBillboard;

    private MovieItemListAdapter movieItemListAdapter;
    private AlphaAnimation enterAnimation, exitAnimation;
    private Sequence sequence;
    private CustomSharedPreferences sharedPreferencesBill;
    private Tracker mTracker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billboard_listview);
        setPresenter(new BillboardMoviePresenter());
        getPresenter().inject(this,getValidateInternet());
        loadComponents();
        progressBarBillboard.show();
        initAnimationTour();
        runAnimationTour();


        App myApp = (App) getApplication();
        mTracker = myApp.gedDefaultTracker();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getPresenter().callThreadToGetMovies();
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBarBillboard.show();
        getPresenter().callThreadToGetMovies();
    }

    /*
    *
    *
    *
    * */



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

        ChainTourGuide tourGuideBillboard = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        })
                        .setTitle(getString(R.string.billboard_listview_billboard))
                        .setDescription(getString(R.string.billboard_listview_tour_description_list))
                        .setGravity(Gravity.BOTTOM)

                )
                .playLater(appBarLayout);

        ChainTourGuide tourGuideMenu = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        })
                        .setTitle(getString(R.string.twitter))
                        .setDescription(getString(R.string.twitter_descriptions))
                        .setGravity(Gravity.BOTTOM)

                )
                .playLater(toolbar);

        sequence = new Sequence.SequenceBuilder()
                .add(tourGuideBillboard,tourGuideMenu)
                .setDefaultOverlay(new Overlay()
                        .setEnterAnimation(enterAnimation)
                        .setExitAnimation(exitAnimation)

                ).setDefaultPointer(null)
                .setContinueMethod(Sequence.ContinueMethod.Overlay)
                .build();

        if (sharedPreferencesBill.getTourGuide(Constants.BILLBOARD_TOUR_GUIDE_KEY) != Constants.TOUR_GUIDE_MADE){
            ChainTourGuide.init(this).playInSequence(sequence);
            sharedPreferencesBill.saveTourGuide(Constants.BILLBOARD_TOUR_GUIDE_KEY,Constants.TOUR_GUIDE_MADE);
        }
        //sharedPreferencesBill.deleteTourGuide(Constants.BILLBOARD_TOUR_GUIDE_KEY);

    }


    public void loadComponents() {
        sharedPreferencesBill = new CustomSharedPreferences(this);
        listViewListBillboard =  findViewById(R.id.billboardAppListViewMovieItem);
        progressBarBillboard =  findViewById(R.id.billboardAppProgressBar);
        appBarLayout = findViewById(R.id.billboard_appbar_layout);
        toolbar = findViewById(R.id.billboard_toolbar_menu);
        toolbar.setTitle(Constants.EMPTY);
        setSupportActionBar(toolbar);
    }



    public void callMovieAdapter(final ArrayList<MovieInfo> movieInfoArrayList) {
        this.movieInfoArrayList = movieInfoArrayList;
        movieItemListAdapter = new MovieItemListAdapter(this, R.id.billboardAppListViewMovieItem, movieInfoArrayList);
        listViewListBillboard.setAdapter(movieItemListAdapter);
        listViewListBillboard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long ld) {

                mTracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Pelicula")
                        .setAction(movieInfoArrayList.get(position).getInfo().getTitle())
                        .build());

                Intent intent = new Intent(BillboardList.this, BillboardDetail.class);
                intent.putExtra(Constants.MOVIES_ARRAY_LIST,movieInfoArrayList.get(position));
                startActivity(intent);
                //Toast.makeText(BillboardList.this, "En construción", Toast.LENGTH_LONG).show();


            }
        });
    }


    //End: Own methods





    //Start: Methods from BaseViews and IbaseViews
    @Override
    public void showProgress(int message) {

    }

    //End: Methods from BaseViews and IBaseViews




    //Start: Methods heritage

    @Override
    public void showMoviesList(final ArrayList<MovieInfo> movieInfoArrayList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBarBillboard.hide();
                callMovieAdapter(movieInfoArrayList);
            }
        });
    }

    @Override
    public void showAlertDialogInternet(int title, int message) {

    }

    @Override
    public void showAlertError(int title, String message) {

    }
    //End: Methods heritage

    //Start: Option Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_init_session){
            //CustomSharedPreferences customSharedPreferences = new CustomSharedPreferences(this);
            //customSharedPreferences.deleteValue(Constants.USER);
            Intent intent = new Intent(BillboardList.this, SessionWithTwitter.class);
            startActivity(intent);
            onBackPressed();
            return true;
        }

        if (item.getItemId() == R.id.menu_tour_guide){
            sharedPreferencesBill.deleteTourGuide(Constants.BILLBOARD_TOUR_GUIDE_KEY);
            runAnimationTour();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_billboard_list,menu);

        return super.onCreateOptionsMenu(menu);
    }
    //End: Option Menu

}
