package com.cinemaapp.views.billboard.detail;

/**
 * Created by jasmany on 19/11/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cinemaapp.R;
import com.cinemaapp.models.movies.MovieInfo;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private ArrayList<MovieInfo> movieInfoArrayList;
    private int position;
    private String title;


    /**
     * The fragment argument representing the section number for this
     * fragment.
     */


    public PlaceholderFragment() {
    }

    public void receiveMovieInfo(ArrayList<MovieInfo> movieInfoArrayList, int position){
        this.movieInfoArrayList = movieInfoArrayList;
        this.position = position;
        Log.i("Tamaño ","Receive in PlacheholderFragment"+this.position);
    }
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail_billboard, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        TextView jasmany = (TextView) rootView.findViewById(R.id.jasmany);
        setDataMovie();
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        jasmany.setText(title);
        return rootView;
    }

    public void setDataMovie() {
        //title = new String("jasmany");
        if (position==0){
            title = "justo";
        }if (position==1){
            title = "Jasmany";
        }if (position==2){
            title = "Jaramillo";
        }

    }
}
