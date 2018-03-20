package com.example.user.storyanimationapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by user on 12/17/2017.
 */

public class CustomTextView extends AppCompatTextView {

    private int typefaceType;

    public CustomTextView(Context context,@Nullable AttributeSet attr) {
        super(context, attr);

        TypedArray array = context.getTheme().obtainStyledAttributes(
                attr,
                R.styleable.CustomTextView,
                0, 0
        );
        try {
            typefaceType = array.getInteger(R.styleable.CustomTextView_font_name, 0);
        } finally {
            array.recycle();
        }

        if (!isInEditMode()) {
            setTypeface(CustomApplication.getInstance().getTypeface(typefaceType));
        }


    }


}
