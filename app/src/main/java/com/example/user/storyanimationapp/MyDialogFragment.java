package com.example.user.storyanimationapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;

/**
 * Created by user on 12/17/2017.
 */

public class MyDialogFragment extends DialogFragment implements View.OnClickListener {
    private static final String TAG = "MyDialogFragment";
    private Button yesBtn, noBtn;
    private PlayStoryListener listener;
    public interface PlayStoryListener{
        void onYesClick();
        void onNoClick();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.listener = (PlayStoryListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.dialog_fragment,container,false);
//        listener = (PlayStoryListener)getActivity();
        yesBtn = (Button)view.findViewById(R.id.btnyes);
        noBtn = (Button)view.findViewById(R.id.btnno);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCancelable(false);
        yesBtn.setOnClickListener(this);
        noBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnno:
                listener.onNoClick();
                this.dismiss();
                break;
            case R.id.btnyes:
                listener.onYesClick();
                this.dismiss();
                break;
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        Log.d(TAG, "onCancel: ");
        super.onCancel(dialog);
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Log.d(TAG, "onCreateAnimation: ");
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        listener.onNoClick();
        super.onDismiss(dialog);
    }
}
