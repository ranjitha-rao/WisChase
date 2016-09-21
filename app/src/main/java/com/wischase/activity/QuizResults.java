package com.wischase.activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import com.wischase.R;
import com.wischase.view.CustomButton;
import com.wischase.view.CustomTextView;
import com.wischase.view.menu.ScrollingActivity;

public class QuizResults extends ScrollingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);
        displayQuizResults();
    }

    private void displayQuizResults() {
        String showResults = getIntent().getStringExtra(ActivityConstants.QUIZRESULTS);
        CustomTextView showResultTextView = (CustomTextView) findViewById(R.id.showResult);
        showResultTextView.setText(showResults);
    }
    public void onClickQuizAgain(View view) {
        Intent quizAgainIntent = new Intent(this, TakeAQuiz.class);
        quizAgainIntent.putExtras(getIntent().getExtras());
        int offset = getIntent().getIntExtra(ActivityConstants.QUIZAGAIN,0);
        quizAgainIntent.putExtra(ActivityConstants.QUIZAGAIN, ++offset);
        startActivity(quizAgainIntent);
    }

    public void mainMenu(View view) {

        Intent mainIntent = new Intent(this, Selectionscreen.class);
        mainIntent.putExtras(getIntent().getExtras());
        startActivity(mainIntent);
    }
}
