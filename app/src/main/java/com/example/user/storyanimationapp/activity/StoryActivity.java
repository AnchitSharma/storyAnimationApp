package com.example.user.storyanimationapp.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.user.storyanimationapp.MyDialogFragment;
import com.example.user.storyanimationapp.R;
import com.example.user.storyanimationapp.Subtitles;
import java.util.ArrayList;

import static android.view.View.GONE;

public class StoryActivity extends AppCompatActivity implements View.OnClickListener,
        MyDialogFragment.PlayStoryListener {

    private static final String TAG = "StoryActivity";
    private ImageView rabbitImg, turtleImg;
    private FrameLayout vg_one, vg_two, vg_system;
    private ViewGroup rootView;
    private TextView turtle_text, rabbit_text, system_text;
    private Animation animation;
    private static Handler handler;
    private MediaPlayer mediaPlayer;
    private FrameLayout myLayout;
    int counter = 0;
    ArrayList<Subtitles> arrayList;
    Subtitles subtitles;
    private BroadcastReceiver receiver;
    int windowWidth, windowHeight;
    float x, y;
    private boolean isItOk = true;// this is for restricting the counter to update
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_story);
        handler = new Handler(getMainLooper());
        rootView = (ViewGroup)findViewById(R.id.container_a);
        myLayout = (FrameLayout) findViewById(R.id.framelayout);

        rabbitImg = (ImageView) findViewById(R.id.imageView9);
        turtleImg = (ImageView) findViewById(R.id.imageView8);

        vg_one = (FrameLayout) findViewById(R.id.turtle_sub);
        vg_two = (FrameLayout) findViewById(R.id.rabbit_sub);
        vg_system = (FrameLayout)findViewById(R.id.system_sub);
        vg_one.setVisibility(GONE);
        vg_two.setVisibility(GONE);
        vg_system.setVisibility(GONE);
        turtle_text = (TextView) findViewById(R.id.turtle_text);
        rabbit_text = (TextView) findViewById(R.id.rabbit_text);
        system_text = (TextView) findViewById(R.id.system_text);
        arrayList = new ArrayList<>();
        addElements();
        windowMetrics();
        rabbitImg.setOnClickListener(this);
        turtleImg.setOnClickListener(this);
        vg_system.setOnClickListener(this);
        vg_two.setOnClickListener(this);
        vg_one.setOnClickListener(this);
        startAnim();
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                handler.post(runnable);
            }
        };

        handler.postDelayed(runnable_sub, 4000);

    }



    private void windowMetrics() {
        windowWidth = getWindowManager().getDefaultDisplay().getWidth();
        windowHeight = getWindowManager().getDefaultDisplay().getHeight();
    }

    private void addElements() {
        String[] str = getResources().getStringArray(R.array.rabbit_subtitles);
        arrayList.add(new Subtitles(str[0] , 4000, R.id.rabbit_text,R.raw.sr_one ));
        arrayList.add(new Subtitles(str[1] , 4000, R.id.rabbit_text,R.raw.sr_two ));
        arrayList.add(new Subtitles(str[2] , 7000, R.id.rabbit_text,R.raw.sr_tthree ));
        arrayList.add(new Subtitles(getString(R.string.tl1) , 4000, R.id.turtle_text,R.raw.st_four));
        arrayList.add(new Subtitles(getString(R.string.tl2) , 4000, R.id.turtle_text,R.raw.st_five));
        arrayList.add(new Subtitles(getString(R.string.sys1) , 4000, R.id.system_text,R.raw.sys_one));
        arrayList.add(new Subtitles(getString(R.string.rl2), 4000, R.id.rabbit_text,R.raw.sr_six));
        arrayList.add(new Subtitles(getString(R.string.r3), 4000, R.id.rabbit_text,R.raw.sr_seven));
        arrayList.add(new Subtitles(getString(R.string.sa1), 7000, R.id.system_text,R.raw.japki));
        arrayList.add(new Subtitles(getString(R.string.sa2), 7000, R.id.system_text,R.raw.twin));
		
        arrayList.add(new Subtitles(getString(R.string.rl3), 6000, R.id.rabbit_text,R.raw.dokh));
        arrayList.add(new Subtitles(getString(R.string.r4), 7000, R.id.rabbit_text,R.raw.ekbar1));
        arrayList.add(new Subtitles(getString(R.string.rl4), 7000, R.id.rabbit_text,R.raw.kaunjeta));
        arrayList.add(new Subtitles(getString(R.string.ra1), 5000, R.id.rabbit_text,R.raw.rabtheek));

        arrayList.add(new Subtitles(getString(R.string.tl3), 4000, R.id.turtle_text,R.raw.turttheek));
        arrayList.add(new Subtitles(getString(R.string.t4), 7000, R.id.turtle_text,R.raw.turekbaar));
        arrayList.add(new Subtitles(getString(R.string.t5), 7000, R.id.turtle_text,R.raw.shergufa));
        arrayList.add(new Subtitles(getString(R.string.s2), 7000, R.id.system_text,R.raw.nomoree));
        arrayList.add(new Subtitles(getString(R.string.s3), 7000, R.id.system_text,R.raw.rabbitwin));
        arrayList.add(new Subtitles(getString(R.string.s4), 7000, R.id.system_text,R.raw.rabbitdance));
        arrayList.add(new Subtitles(getString(R.string.tl4), 7000, R.id.turtle_text,R.raw.gadbad));
        arrayList.add(new Subtitles(getString(R.string.t6), 10000, R.id.turtle_text,R.raw.ekbaarmay));
        arrayList.add(new Subtitles(getString(R.string.r15), 7000, R.id.rabbit_text,R.raw.chaloekbaar));
        arrayList.add(new Subtitles(getString(R.string.r16), 7000, R.id.rabbit_text,R.raw.abyehfinal));
        arrayList.add(new Subtitles(getString(R.string.r17), 7000, R.id.rabbit_text,R.raw.rabtheek));
        arrayList.add(new Subtitles(getString(R.string.r18), 7000, R.id.rabbit_text,R.raw.abphadi));
        arrayList.add(new Subtitles(getString(R.string.s6), 7000, R.id.system_text,R.raw.jisrastese));
        arrayList.add(new Subtitles(getString(R.string.s7), 7000, R.id.system_text,R.raw.wahanadi));
        arrayList.add(new Subtitles(getString(R.string.s8), 10000, R.id.system_text,R.raw.kharcharka));
        arrayList.add(new Subtitles(getString(R.string.r19), 7000, R.id.rabbit_text,R.raw.abkyakaru));
        arrayList.add(new Subtitles(getString(R.string.ra2), 7000, R.id.rabbit_text,R.raw.jadlbazi));
        arrayList.add(new Subtitles(getString(R.string.ra3), 9000, R.id.rabbit_text,R.raw.sys));
        arrayList.add(new Subtitles(getString(R.string.ta1), 5000, R.id.turtle_text,R.raw.sys));
        arrayList.add(new Subtitles(getString(R.string.s9), 9000, R.id.system_text,R.raw.sys));
        arrayList.add(new Subtitles(getString(R.string.ta2), 5000, R.id.turtle_text,R.raw.sys));
        arrayList.add(new Subtitles(getString(R.string.ta3), 10000, R.id.turtle_text,R.raw.sys));
        arrayList.add(new Subtitles(getString(R.string.ta4), 10000, R.id.turtle_text,R.raw.sys));
        arrayList.add(new Subtitles(getString(R.string.ta5), 10000, R.id.turtle_text,R.raw.sys));
        arrayList.add(new Subtitles(getString(R.string.ta6), 10000, R.id.turtle_text,R.raw.sys));
        arrayList.add(new Subtitles(getString(R.string.ta7), 10000, R.id.turtle_text,R.raw.sys));
        arrayList.add(new Subtitles(getString(R.string.s10), 15000, R.id.system_text,R.raw.sys));
        arrayList.add(new Subtitles(getString(R.string.s11), 9000, R.id.system_text,R.raw.sys));

    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter("com.example.user.storyanimationapp.someaction");
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);

    }


    private void startAnim() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(StoryActivity.this, R.anim.translate);
                Animation animation2 = AnimationUtils.loadAnimation(StoryActivity.this, R.anim.translate_right);
                rabbitImg.startAnimation(animation2);
                turtleImg.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //soundManager.playSound();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }

                });


            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.rabbit_sub:
                subtitles = arrayList.get(counter-1);
                mediaPlayer = MediaPlayer.create(StoryActivity.this, subtitles.getSong_id());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.release();
                    }
                });
                break;
            case R.id.turtle_sub:
                subtitles = arrayList.get(counter-1);
                mediaPlayer = MediaPlayer.create(StoryActivity.this, subtitles.getSong_id());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.release();
                    }
                });
                break;
            case R.id.system_sub:
                subtitles = arrayList.get(counter-1);
                mediaPlayer = MediaPlayer.create(StoryActivity.this, subtitles.getSong_id());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.release();
                    }
                });
                break;
            //Rabbit image
            case R.id.imageView9:
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        animation = AnimationUtils.loadAnimation(StoryActivity.this, R.anim.bounce);
                        rabbitImg.startAnimation(animation);
                        animation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                                mediaPlayer = MediaPlayer.create(StoryActivity.this,R.raw.jump);
                                mediaPlayer.start();
                                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        mediaPlayer.release();
                                    }
                                });
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                            }
                        });
                    }
                });

                break;
            //turtle Image
            case R.id.imageView8:
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        animation = AnimationUtils.loadAnimation(StoryActivity.this, R.anim.together);
                        turtleImg.startAnimation(animation);
                        animation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                            }
                        });
                    }
                });

                break;

        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onBackPressed() {
        isItOk = false;
        MyDialogFragment fragment = new MyDialogFragment();
        fragment.show(getSupportFragmentManager(), "popdialog");

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            setContentView(R.layout.temp_layout_end);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            StoryActivity.super.onBackPressed();
        }
    };

    @Override
    public void onYesClick() {
        super.onBackPressed();
    }

    @Override
    public void onNoClick() {
        isItOk = true;
        handler.postDelayed(runnable_sub, 2000);
    }

    private Runnable runnable_sub = new Runnable() {
        @Override
        public void run() {
            subtitles = arrayList.get(counter);

            if (subtitles.isChangeBackground()){
//                rootView.setBackgroundResource();
            }
            if (isItOk) {
                switch (subtitles.getWhichView()) {
                    case R.id.turtle_text:
                        vg_two.setVisibility(GONE);
                        vg_system.setVisibility(GONE);
                        vg_one.setVisibility(View.VISIBLE);
                        turtle_text.setText(subtitles.getMsg());
                        break;

                    case R.id.rabbit_text:
                        vg_one.setVisibility(GONE);
                        vg_two.setVisibility(View.VISIBLE);
                        vg_system.setVisibility(GONE);
                        rabbit_text.setText(subtitles.getMsg());
                        break;
                    case R.id.system_text:
                        vg_one.setVisibility(GONE);
                        vg_two.setVisibility(GONE);
                        vg_system.setVisibility(View.VISIBLE);
                        system_text.setText(subtitles.getMsg());
                }
                if (counter < arrayList.size() - 1) {
                    counter++;
                } else {
                    handler.removeCallbacks(runnable_sub);

                }
                handler.postDelayed(runnable_sub, subtitles.getTimeToDisplay());
            }

        }
    };


}
