package com.wischase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wischase.activity.TakeAQuiz;
import com.wischase.view.menu.ScrollingActivity;


public class MainActivity extends ScrollingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void takeAQuiz(View inputView)  {
        setContentView(R.layout.activity_take_aquiz);
        Intent intent = new Intent(this, TakeAQuiz.class);
        startActivity(intent);
    }
}