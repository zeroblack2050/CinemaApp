package com.cinemaapp.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by Superadmin1 on 17/10/2017.
 */

public class CustomSharedPreferences {

    /*private SharedPreferences sharedPreferences;

    public CustomSharedPreferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
    }

    public void saveObjectUser(String key, LoginModel user){
        Gson gson = new Gson();
        String json = gson.toJson(user);
        addValue(key, json);
    }

    private void addValue(String key, String json) {
        sharedPreferences.edit().putString(key, json).commit();
    }

    public LoginModel getObjectuser(String key){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(key, "");
        LoginModel user = gson.fromJson(json,LoginModel.class);
        return user;
    }

    public void deleteValue(String key){
        sharedPreferences.edit().remove(key).commit();
    }*/
}
