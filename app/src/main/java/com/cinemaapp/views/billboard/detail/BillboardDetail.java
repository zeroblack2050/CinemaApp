package com.cinemaapp.views.billboard.detail;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cinemaapp.R;
import com.cinemaapp.helper.Constants;
import com.cinemaapp.models.movies.MovieInfo;
import com.cinemaapp.presenters.BillboardDetailPresenter;
import com.cinemaapp.views.Bases.BaseViews;

import java.util.ArrayList;

public class BillboardDetail extends BaseViews<BillboardDetailPresenter> implements IBillboardDetail {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    public ArrayList<MovieInfo> movieInfoArrayList;
    public int position = 0;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private PlaceholderFragment mplaceholderFragment;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private Toolbar toolbar;
    FloatingActionButton floatingActionButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_billboard);
        setPresenter(new BillboardDetailPresenter());
        getPresenter().inject(this,getValidateInternet());
        receiveMovieInfo();
        loadDataFragments();
        loadComponents();
        loadMap();


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.


        // Set up the ViewPager with the sections adapter.




    }

    private void loadDataFragments() {
        mplaceholderFragment = new PlaceholderFragment();
        mplaceholderFragment.receiveMovieInfo(movieInfoArrayList,position);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mSectionsPagerAdapter.receiveMovieInfo(movieInfoArrayList,position);
        Log.i("Paso", "receiveMethod");
    }

    private void receiveMovieInfo() {
        movieInfoArrayList = (ArrayList<MovieInfo>) getIntent().getSerializableExtra(Constants.ARRAY_MOVIES);
        position = (int) getIntent().getSerializableExtra(Constants.POSITION_MOVIES);
    }

    private void loadMap() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void loadComponents() {
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_detail_billboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }






    //Methods from IBillboardDetail
    @Override
    public void showToast(int message) {

    }

    @Override
    public void showToast(String message) {

    }
}
