package com.example.mymemoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;

import android.animation.ObjectAnimator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class splashactivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashactivity);

        // Load the views
        ImageView imageView = findViewById(R.id.iv_top);
        TextView textView1 = findViewById(R.id.acc1);
        TextView textView2 = findViewById(R.id.acc2);

        // Create the animations
        ObjectAnimator rotateAnimation = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
        rotateAnimation.setDuration(2000);

        ObjectAnimator translateAnimation1 = ObjectAnimator.ofFloat(textView1, "translationX", 0f, 200f);
        translateAnimation1.setDuration(2000);

        ObjectAnimator translateAnimation2 = ObjectAnimator.ofFloat(textView2, "translationX", 0f, -200f);
        translateAnimation2.setDuration(2000);

        // Create an AnimatorSet to play the animations together
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(rotateAnimation, translateAnimation1, translateAnimation2);

        // Set a listener to start the IntroActivity when the animations end
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                // Animation started
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // Animation ended, start IntroActivity
                Intent intent = new Intent(splashactivity.this, intro_activity.class);
                startActivity(intent);
                finish(); // Finish splash activity to prevent going back to it
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // Animation canceled
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // Animation repeated
            }
        });

        // Start the animations
        animatorSet.start();
    }
}