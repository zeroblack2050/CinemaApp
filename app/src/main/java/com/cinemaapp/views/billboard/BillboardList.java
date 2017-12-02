package com.cinemaapp.views.billboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.BounceInterpolator;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.cinemaapp.R;
import com.cinemaapp.helper.Constants;
import com.cinemaapp.models.movies.MovieInfo;
import com.cinemaapp.presenters.BillboardMoviePresenter;
import com.cinemaapp.views.Bases.BaseViews;
import com.cinemaapp.views.adapters.MovieItemListAdapter;

import java.util.ArrayList;

import tourguide.tourguide.ChainTourGuide;
import tourguide.tourguide.Overlay;
import tourguide.tourguide.Sequence;
import tourguide.tourguide.ToolTip;

public class BillboardList extends BaseViews<BillboardMoviePresenter> implements IBillboard {

    private ArrayList<MovieInfo> movieInfoArrayList;
    private ListView listViewListBillboard;
    private android.support.v7.widget.Toolbar toolbar;
    private ProgressBar progressBarBillboard;
    private MovieItemListAdapter movieItemListAdapter;
    private AlphaAnimation enterAnimation, exitAnimation;
    private Menu menu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billboard_listview);
        setPresenter(new BillboardMoviePresenter());
        getPresenter().inject(this,getValidateInternet());
        loadComponents();
        instanceObjects();
        loadEvents();
        initAnimationTour();
        runAnimationTour();
    }


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

        ChainTourGuide tourGuideBIllboard = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        })
                        .setTitle("Cartelera")
                        .setDescription("En esta sección encontrara las últimas peliculas")
                        .setGravity(Gravity.BOTTOM)

                )
                .playLater(listViewListBillboard);

        ChainTourGuide tourGuideMenu = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        })
                        .setTitle("Twitter")
                        .setDescription("El menu desplegable le permite iniciar sesión con twitter")
                        .setGravity(Gravity.BOTTOM)

                )
                .playLater(toolbar);

        Sequence sequence = new Sequence.SequenceBuilder()
                .add(tourGuideBIllboard,tourGuideMenu)
                .setDefaultOverlay(new Overlay()
                        .setEnterAnimation(enterAnimation)
                        .setExitAnimation(exitAnimation)

                ).setDefaultPointer(null)
                .setContinueMethod(Sequence.ContinueMethod.Overlay)
                .build();

        ChainTourGuide.init(this).playInSequence(sequence);
    }







    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().callThreadToGetMovies();
    }

    //Start: Own methods
    private void loadEvents() {

    }

    public void instanceObjects() {
        //movieItemListAdapter = new MovieItemListAdapter();
    }

    public void loadComponents() {
        listViewListBillboard =  findViewById(R.id.billboardAppListViewMovieItem);
        //progressBarBillboard =  findViewById(R.id.billboardAppProgressBar);
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

    @Override
    public void hideProgress() {

    }
    //End: Methods from BaseViews and IBaseViews




    //Start: Methods heritage

    @Override
    public void showMoviesList(final ArrayList<MovieInfo> movieInfoArrayList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //progress.hide();
                callMovieAdapter(movieInfoArrayList);
                hideProgress();
            }
        });
    }

    @Override
    public void showMoviesGrid(ArrayList<MovieInfo> movieInfoArrayList) {

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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_billboard,menu);

        return super.onCreateOptionsMenu(menu);
    }
    //End: Option Menu

}
