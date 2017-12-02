package com.cinemaapp.helper.customclasses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.Button;

import com.cinemaapp.R;

/**
 * Created by jasmany on 26/11/2017.
 */

@SuppressLint("AppCompatCustomView")
public class CustomButton extends Button {

    private int TEXTSIZE = 22;

    public CustomButton(Context context) {
        super(context);
        setAllCaps(false);
        setBackground(ContextCompat.getDrawable(context,R.drawable.custom_button));
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextColor(ContextCompat.getColor(context,R.color.colorWhite));
        setTextSize(TEXTSIZE);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAllCaps(false);
        setBackground(ContextCompat.getDrawable(context,R.drawable.custom_button));
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextColor(ContextCompat.getColor(context,R.color.colorWhite));
        setTextSize(TEXTSIZE);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAllCaps(false);
        setBackground(ContextCompat.getDrawable(context,R.drawable.custom_button));
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextColor(ContextCompat.getColor(context,R.color.colorWhite));
        setTextSize(TEXTSIZE);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setAllCaps(false);
        setBackground(ContextCompat.getDrawable(context,R.drawable.custom_button));
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextColor(ContextCompat.getColor(context,R.color.colorWhite));
        setTextSize(TEXTSIZE);
    }
}
