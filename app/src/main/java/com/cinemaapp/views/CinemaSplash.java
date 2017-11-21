package com.cinemaapp.views;

import android.animation.Animator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.cinemaapp.R;
import com.cinemaapp.views.billboard.Billboard;

public class CinemaSplash extends AppCompatActivity {

    LottieAnimationView animationView;
    int flo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cinema_splash);


        loadComponents();
        loadSplash();

    }



    private void loadComponents() {
        animationView = (LottieAnimationView) findViewById(R.id.animation_view);
    }

    private void loadSplash() {
        flo = 10000;
        animationView.setAnimation("progress_bar.json");
        animationView.loop(false);
        animationView.playAnimation();
        animationView.setTop(flo);
        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

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

            }
        });

    }

    public void loadBillboard(){
        Intent intent = new Intent(this, Billboard.class);
        startActivity(intent);
    }

}
