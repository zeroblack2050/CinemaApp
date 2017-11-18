package com.cinemaapp.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cinemaapp.models.movies.MovieInfo;

import java.util.ArrayList;

/**
 * Created by jasmany on 17/11/2017.
 */

public class MovieCoverFlowAdapter extends BaseAdapter {

    ArrayList<MovieInfo> movieInfoArrayList = new ArrayList<>();
    Context context;

    public MovieCoverFlowAdapter(ArrayList<MovieInfo> movieInfoArrayList, Context context) {
        this.movieInfoArrayList = movieInfoArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return movieInfoArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return movieInfoArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        //View view = inflater.inflate()
        return null;
    }
}
