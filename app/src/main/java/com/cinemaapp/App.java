package com.cinemaapp;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by Superadmin1 on 02/12/2017.
 */

public class App extends Application {

    private Tracker mTracker;

    synchronized public Tracker gedDefaultTracker(){
        if (mTracker == null){
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return  mTracker;
    }

}
