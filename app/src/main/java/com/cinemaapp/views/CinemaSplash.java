package com.cinemaapp.views;

import android.animation.Animator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.cinemaapp.R;
import com.cinemaapp.views.billboard.BillboardList;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class CinemaSplash extends AppCompatActivity {

    private LottieAnimationView animationView;
    private int TIMEOUT_ANIMATION = 90000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.cinema_splash);


        loadComponents();
        loadSplash();

    }


    @Override
    protected void onResume() {
        super.onResume();
        loadSplash();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadSplash();
    }

    private void loadComponents() {
        animationView = (LottieAnimationView) findViewById(R.id.animation_view);
    }

    private void loadSplash() {
        animationView.setAnimation("progress_bar.json");
        animationView.playAnimation();
        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                animationView.loop(false);
                animationView.setTop(TIMEOUT_ANIMATION);

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                loadBillboard();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                animationView.playAnimation();
            }
        });

    }

    public void loadBillboard(){
        Intent intent = new Intent(this, BillboardList.class);
        startActivity(intent);
    }

}
