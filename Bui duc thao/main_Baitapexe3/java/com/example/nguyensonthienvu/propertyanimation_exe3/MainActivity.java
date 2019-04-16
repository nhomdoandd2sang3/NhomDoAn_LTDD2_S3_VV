package com.example.nguyensonthienvu.propertyanimation_exe3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {
    AnimatorSet sunAnimatorSet;
    AnimatorSet cloud1AnimatorSet;
    AnimatorSet cloud2AnimatorSet;
    ValueAnimator skyAnimator;
    ValueAnimator groundAnimator;
    ValueAnimator sunAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Sun
        sunAnimatorSet = (AnimatorSet)AnimatorInflater.loadAnimator(this, R.animator.sun_movement);
        ImageView sun = (ImageView) findViewById(R.id.sun);
        sun.animate().alpha(0.3f);
        ObjectAnimator alphafromAnimator = ObjectAnimator.ofFloat(sun, "alpha", 1f);
        alphafromAnimator.setDuration(10000);
//        alphafromAnimator.setEvaluator(new ArgbEvaluator());
        alphafromAnimator.setRepeatCount(ValueAnimator.INFINITE);
        alphafromAnimator.setRepeatMode(ValueAnimator.REVERSE);

        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(sun, "scaleX", 0.5f);
        scaleXAnimator.setDuration(10000);
//        scaleXAnimator.setEvaluator(new ArgbEvaluator());
        scaleXAnimator.setRepeatCount(ValueAnimator.INFINITE);
        scaleXAnimator.setRepeatMode(ValueAnimator.REVERSE);

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(sun, "scaleY", 0.5f);
        scaleYAnimator.setDuration(10000);
//        scaleYAnimator.setEvaluator(new ArgbEvaluator());
        scaleYAnimator.setRepeatCount(ValueAnimator.INFINITE);
        scaleYAnimator.setRepeatMode(ValueAnimator.REVERSE);
        sunAnimatorSet.playTogether(alphafromAnimator,scaleXAnimator,scaleYAnimator);

        sunAnimatorSet.setTarget(sun);

        // cloud1
        ImageView cloud1 = (ImageView) findViewById(R.id.cloud1);
        cloud1AnimatorSet = (AnimatorSet)AnimatorInflater.loadAnimator(this, R.animator.cloud_movement);
        cloud1.animate().alpha(0.3f);
        ObjectAnimator cloud1Animator = ObjectAnimator.ofFloat(cloud1, "alpha", 1f);
        cloud1Animator.setDuration(8000);

        cloud1Animator.setRepeatCount(ValueAnimator.INFINITE);
        cloud1Animator.setRepeatMode(ValueAnimator.REVERSE);
        cloud1AnimatorSet.play(cloud1Animator);
        cloud1AnimatorSet.setTarget(cloud1);

        // cloud2
        ImageView cloud2 = (ImageView) findViewById(R.id.cloud2);
        cloud2AnimatorSet = (AnimatorSet)AnimatorInflater.loadAnimator(this, R.animator.cloud_movement);
        cloud2.animate().alpha(0.2f);
        ObjectAnimator cloud2Animator = ObjectAnimator.ofFloat(cloud1, "alpha", 1f);
        cloud2Animator.setDuration(5000);

        cloud2Animator.setRepeatCount(ValueAnimator.INFINITE);
        cloud2Animator.setRepeatMode(ValueAnimator.REVERSE);
        cloud2AnimatorSet.play(cloud2Animator);
        cloud2AnimatorSet.setTarget(cloud2);

        // sky

        skyAnimator = ObjectAnimator.ofInt
                (findViewById(R.id.sky), "backgroundColor",
                        Color.rgb(0x00, 0x00, 0x4c), Color.rgb(0xae, 0xc2, 0xff));

        // ground
        groundAnimator = ObjectAnimator.ofInt
                (findViewById(R.id.ground), "backgroundColor",
                        Color.rgb(0x00, 0x47, 0x00), Color.rgb(0x85, 0xae, 0x85));
        //set same duration and animation properties as others
        groundAnimator.setDuration(10000);
        groundAnimator.setEvaluator(new ArgbEvaluator());
        groundAnimator.setRepeatCount(ValueAnimator.INFINITE);
        groundAnimator.setRepeatMode(ValueAnimator.REVERSE);

        //set same duration and animation properties as others
        skyAnimator.setDuration(10000);
        skyAnimator.setEvaluator(new ArgbEvaluator());
        skyAnimator.setRepeatCount(ValueAnimator.INFINITE);
        skyAnimator.setRepeatMode(ValueAnimator.REVERSE);
        // goi ham listen sun
        sunAnimatorSet.addListener(

                new AnimatorListenerAdapter() {

                    public void onAnimationStart(Animator animation) {
                        Toast.makeText(getApplicationContext(), "Animation started!",
                                Toast.LENGTH_SHORT).show();

                    }

                    public void onAnimationEnd(Animator animation) {
                        Toast.makeText(getApplicationContext(), "Animation ended!",
                                Toast.LENGTH_SHORT).show();
                    }

                });

        //sky event

        skyAnimator.addUpdateListener(

                new ValueAnimator.AnimatorUpdateListener() {

                    TextView textView = (TextView) findViewById(R.id.textView);
                    float animatedFractionPrev = 0.0f;
                    float animatedFractionCurr = 0.0f;

                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        animatedFractionCurr = valueAnimator.getAnimatedFraction();
                        if (animatedFractionCurr > animatedFractionPrev) {
                            if (animatedFractionCurr > 0.0 && animatedFractionCurr <= 0.70) {
                                textView.setText("Good morning!");

                            } else {
                                textView.setText("Good day!");
                            }
                        } else {
                            if (animatedFractionCurr >= 0.8) {
                                textView.setText("Good day!");
                            } else if (animatedFractionCurr < 0.8 && animatedFractionCurr >= 0.1) {
                                textView.setText("Good afternoon!");
                            } else {
                                textView.setText("Good Evening!");
                            }
                        }
                        animatedFractionPrev = animatedFractionCurr;
                    }
                }
        );



    }
    //start or stop
    public void onToggleClicked(View view) {

        boolean on = ((ToggleButton) view).isChecked();

        if (on) {
            sunAnimatorSet.start();
            skyAnimator.start();
            groundAnimator.start();
            cloud1AnimatorSet.start();
            cloud2AnimatorSet.start();
        } else {
            sunAnimatorSet.cancel();

            skyAnimator.cancel();
            groundAnimator.cancel();
            cloud1AnimatorSet.cancel();
            cloud2AnimatorSet.cancel();
        }

    }
    //

    @Override
    protected void onResume() {
        super.onResume();
        ((ToggleButton)findViewById(R.id.toggleAnimate)).setChecked(false);
    }
}
