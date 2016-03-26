package com.wischase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wischase.Question;
import com.wischase.R;
import com.wischase.view.menu.ScrollingActivity;

public class QuestionUpload extends ScrollingActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_upload);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Toast.makeText(getBaseContext(),ActivityConstants.getInstance().getSubcategory(),Toast.LENGTH_LONG).show();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void addAnswerToUpload(View view)
    {
        /* Getting the value of question edittext and save it in string question_upload*/
        EditText editText=(EditText)findViewById(R.id.question);
        String question_upload=editText.getText().toString();
        setContentView(R.layout.activity_answer_upload);
         Intent intent=new Intent(this,AnswerUpload.class);
        Question upload=new Question();
//        ac.setQuestionTrial(question_upload);
        upload.setQuestionText(question_upload);
        intent.putExtra(ActivityConstants.QUESTIONS,upload );
        startActivity(intent);
    }
    public void backToOptionScreen(View view)
    {
        setContentView(R.layout.activity_optionscreen);
        Intent intent=new Intent(this,Optionscreen.class);
        startActivity(intent);
    }

}
