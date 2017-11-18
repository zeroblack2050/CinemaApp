package com.cinemaapp.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.cinemaapp.R;

public class CinemaSplash extends AppCompatActivity {

    LottieAnimationView animationView;
    float flo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_splash);

        animationView = (LottieAnimationView) findViewById(R.id.animation_view);
        animationView.setAnimation("progress_bar.json");
        animationView.loop(true);
        animationView.playAnimation();
        flo = 100;
        animationView.setMaxProgress(flo);

    }
}
