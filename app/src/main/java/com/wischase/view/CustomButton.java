package com.wischase.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class CustomButton extends Button {
        public CustomButton(Context context) {
            super(context);
            setFont();
        }
        public CustomButton(Context context, AttributeSet attrs) {
            super(context, attrs);
            setFont();
        }
        public CustomButton(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            setFont();
        }

        private void setFont() {
            Typeface font = Typeface.createFromAsset(getContext().getAssets(), "font/Linds.ttf");
            setTypeface(font, Typeface.BOLD);
        }
}
