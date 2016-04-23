package com.wischase.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.wischase.R;

public class CustomRadioButton extends AppCompatRadioButton  {
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
        setTextColor(ContextCompat.getColor(getContext(), R.color.colorText));
    }

    public void setFont(int id) {

        setTextColor(ContextCompat.getColor(getContext(), id));
        setSupportButtonTintList(
                ContextCompat.getColorStateList(getContext(),
                        id));
    }

}
