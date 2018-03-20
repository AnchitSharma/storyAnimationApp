package com.example.user.storyanimationapp.activity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.example.user.storyanimationapp.R;


public class StorySplashScreen extends AppCompatActivity {

    private static final String TAG = "StorySplashScreen";
    private ImageView imageView;
    private Animation animation;
    private ViewGroup mRoot;
    private RelativeLayout animLayout;
    private LottieAnimationView btn, box;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.story_splash_temp);
        handler = new Handler(getMainLooper());
        mRoot = (ViewGroup)findViewById(R.id.text_container);
        animLayout = (RelativeLayout)findViewById(R.id.container_b);
        box = (LottieAnimationView)findViewById(R.id.animation_view);

        btn = (LottieAnimationView) findViewById(R.id.startBtn);
        animation = AnimationUtils.loadAnimation(this, R.anim.translate_y);
        mRoot.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                handler.post(runnable);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
                    viewMenu();
                }else {
                    box.clearAnimation();
                    startActivity(new Intent(StorySplashScreen.this, StoryActivity.class));
                    finish();
                }

            }
        });
    }

    private void viewMenu() {

        int x = animLayout.getRight();
        int y = animLayout.getBottom();

        int startRadius = 0;
        int endRadius = (int) Math.hypot(animLayout.getWidth(), animLayout.getHeight());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Animator anim = ViewAnimationUtils.createCircularReveal(animLayout, x, y, startRadius, endRadius);
            startActivity(new Intent(StorySplashScreen.this, StoryActivity.class));
            anim.start();
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    finish();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            box.setVisibility(View.VISIBLE);
        }
    };


    /* Runnable runnable = new Runnable() {
        @Override
        public void run() {
            StartFragment fragment = new StartFragment();
            FragmentTransaction fragmentTransaction =
        }
    }
*/


}
