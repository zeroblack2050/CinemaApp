package com.cinemaapp.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Superadmin1 on 17/10/2017.
 */

public class CustomSharedPreferences {

    private SharedPreferences sharedPreferences;

    public CustomSharedPreferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
    }


    /**
     *** Start: Methods to save twitter information
     ***/
    public void saveStateButtons(String changeButton,int StateButton){
        sharedPreferences.edit().putInt(changeButton, StateButton).commit();
    }

    public int getStateButtons(String changeButton){
        return  sharedPreferences.getInt(changeButton,0);
    }

    public void deleteStateButtons(String changeButton){
        sharedPreferences.edit().remove(changeButton).commit();
    }
    /**
     *** End: Methods to save twitter information
     ***/



    /**
     *** Start: Methods to save twitter information
     ***/
    public void saveTwitterData(String key, String Object){
        sharedPreferences.edit().putString(key, Object).commit();
    }
    public String getTwitterData(String key){
        return sharedPreferences.getString(key,null);
    }
    public void deleteTwitterData(String key){
        sharedPreferences.edit().remove(key).commit();
    }
    /**
     *** End: Methods to save twitter information
     ***/

    /**
    *** Start: Methods to save state tour guide
    ***/
    public void saveTourGuide(String tourGuideKey,int tourGuideMade){
        sharedPreferences.edit().putInt(tourGuideKey, tourGuideMade).commit();
    }

    public int getTourGuide(String tourGuideKey){
        return  sharedPreferences.getInt(tourGuideKey,0);
    }

    public void deleteTourGuide(String tourGuideKey){
        sharedPreferences.edit().remove(tourGuideKey).commit();
    }
    /**
     *** End: Methods to save state tour guide
     ***/

}
