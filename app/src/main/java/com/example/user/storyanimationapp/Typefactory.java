package com.example.user.storyanimationapp;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by user on 12/17/2017.
 */

public class Typefactory {

    final String BUNGEE_REGULAR = "fonts/Sansita-BoldItalic.ttf";

    Typeface regular;

    public Typefactory(Context context){
        regular = Typeface.createFromAsset(context.getAssets(),BUNGEE_REGULAR);

    }

    public Typeface getRegular(){
        return regular;
    }


}
