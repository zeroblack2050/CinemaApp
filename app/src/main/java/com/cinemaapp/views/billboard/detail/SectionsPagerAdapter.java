package com.cinemaapp.views.billboard.detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.cinemaapp.models.movies.MovieInfo;

import java.util.ArrayList;

/**
 * Created by jasmany on 19/11/2017.
 */

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<MovieInfo> movieInfoArrayList;
    private int positionmov;

    public void receiveMovieInfo(ArrayList<MovieInfo> movieInfoArrayList, int position){
        this.movieInfoArrayList = movieInfoArrayList;
        this.positionmov = position;
    }

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + positionmov);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        //Log.i("Tamaño ",""+positionmov);
        return movieInfoArrayList.size();
    }



}
