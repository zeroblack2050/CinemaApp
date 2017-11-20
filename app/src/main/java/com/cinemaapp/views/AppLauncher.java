package com.cinemaapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cinemaapp.views.billboard.Billboard;

/**
 * Created by jasmany on 10/11/2017.
 */

public class AppLauncher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initApp();
    }

    public void initApp(){
        //Intent intent = new Intent(this, Billboard.class);
        Intent intent = new Intent(this, CinemaSplash.class);
        startActivity(intent);
    }
}
