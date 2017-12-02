package com.cinemaapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cinemaapp.views.billboard.BillboardList;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

/**
 * Created by jasmany on 10/11/2017.
 */

public class AppLauncher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        initApp();
    }

    public void initApp(){
        //Intent intent = new Intent(this, BillboardList.class);
        Intent intent = new Intent(this, CinemaSplash.class);
        startActivity(intent);
    }
}
