package com.wischase.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.TextView;

import com.wischase.R;

public class CustomTextView extends TextView {
        public CustomTextView(Context context) {
            super(context);
            setFont();
        }
        public CustomTextView(Context context, AttributeSet attrs) {
            super(context, attrs);
            setFont();
        }
        public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            setFont();
        }

        private void setFont() {
            Typeface font = Typeface.createFromAsset(getContext().getAssets(), "font/Linds.ttf");
            setTypeface(font, Typeface.NORMAL);
            setTextColor(ContextCompat.getColor(getContext(), R.color.colorText));
        }
    }
