package com.wischase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.wischase.Category;
import com.wischase.Question;
import com.wischase.R;
import com.wischase.db.DBHandler;
import com.wischase.view.menu.ScrollingActivity;

import java.util.List;

public class AnswerUpload extends ScrollingActivity {

    List<Question> questionList;
    int currentCorrect = 0;
    int totalCurrent = 0;
    int questionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       Intent inputIntent = getIntent();
       Question question =  inputIntent.getParcelableExtra("question");
        String s=question.getQuestionText();
        Log.i(s, "just display");
 /*       Question userInput = (Question)(inputIntent.writeToParcel(parcel));
        int gradeInput = inputIntent.getIntExtra(ActivityConstants.GRADE_INPUT,0);
        DBHandler dbHandle = new DBHandler(this);
        int categoryIdInput = userInput.getSubCategory().get(0).getCategoryId();
        questionList = dbHandle.getAllQuestions(categoryIdInput, gradeInput);
*/
        setContentView(R.layout.activity_answer_upload);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void uploadAnswer(View view)
    {


    }

}
