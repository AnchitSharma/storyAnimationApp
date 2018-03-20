package com.example.user.storyanimationapp;

/**
 * Created by user on 12/20/2017.
 */

public class Subtitles {
    String msg;
    long timeToDisplay;
    int whichView;
    int song_id;
    private boolean isChangeBackground = false;
    private int frame;

    public Subtitles(String msg, long timeToDisplay, int whichView, int song_id) {
        this.msg = msg;
        this.timeToDisplay = timeToDisplay;
        this.whichView = whichView;
        this.song_id = song_id;
    }

    public Subtitles(String msg, long timeToDisplay, int whichView, int song_id, boolean isChangeBackground, int frame) {
        this.msg = msg;
        this.timeToDisplay = timeToDisplay;
        this.whichView = whichView;
        this.song_id = song_id;
        this.isChangeBackground = isChangeBackground;
        this.frame = frame;
    }

    public boolean isChangeBackground() {
        return isChangeBackground;
    }

    public int getFrame() {
        return frame;
    }

    public int getSong_id() {
        return song_id;
    }

    public String getMsg() {
        return msg;
    }

    public long getTimeToDisplay() {
        return timeToDisplay;
    }

    public int getWhichView() {
        return whichView;
    }
}
