package com.example.user.storyanimationapp;

import android.app.Application;
import android.graphics.Typeface;

/**
 * Created by user on 12/17/2017.
 */

public class CustomApplication extends Application {

    private static CustomApplication mInstance;
    private Typefactory fontFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized CustomApplication getInstance(){
        return mInstance;
    }

    public Typeface getTypeface(int type){
        if (fontFactory == null)
            fontFactory = new Typefactory(this);

        switch (type){
            case Constants.REGULAR:return fontFactory.getRegular();
            default: return fontFactory.getRegular();
        }
    }

    public interface  Constants{
        int REGULAR=1;
        int BOLD = 2;
        int ITALIC = 3;
    }
}
