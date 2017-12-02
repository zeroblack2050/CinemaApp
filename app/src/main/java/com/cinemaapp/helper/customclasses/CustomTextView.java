package com.cinemaapp.helper.customclasses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by jasmany on 26/11/2017.
 */

@SuppressLint("AppCompatCustomView")
public class CustomTextView extends TextView {


    public CustomTextView(Context context) {
        super(context);
        setAllCaps(false);
        setTextAlignment(TEXT_ALIGNMENT_TEXT_START);
        setPadding(2,2,2,2);
        setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        setTypeface(Typeface.DEFAULT_BOLD);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setAllCaps(false);
        setTextAlignment(TEXT_ALIGNMENT_TEXT_START);
        setPadding(2,2,2,2);
        setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        setTypeface(Typeface.DEFAULT_BOLD);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAllCaps(false);
        setTextAlignment(TEXT_ALIGNMENT_TEXT_START);
        setPadding(2,2,2,2);
        setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        setTypeface(Typeface.DEFAULT_BOLD);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setAllCaps(false);
        setTextAlignment(TEXT_ALIGNMENT_TEXT_START);
        setPadding(2,2,2,2);
        setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        setTypeface(Typeface.DEFAULT_BOLD);
    }
}
