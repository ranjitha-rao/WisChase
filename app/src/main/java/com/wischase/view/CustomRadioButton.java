package com.wischase.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RadioButton;

public class CustomRadioButton extends RadioButton {
    public CustomRadioButton(Context context) {
        super(context);
        setFont();
    }
    public CustomRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }
    public CustomRadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFont();
    }

    private void setFont() {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "font/Linds.ttf");
        setTypeface(font, Typeface.NORMAL);
        //setButtonTintList();
    }
}
