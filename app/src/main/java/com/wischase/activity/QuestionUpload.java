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

import com.wischase.Category;
import com.wischase.Question;
import com.wischase.R;
import com.wischase.view.CustomTextView;
import com.wischase.view.menu.ScrollingActivity;

public class QuestionUpload extends UpdateTable {
int grade;
    Category userInput;
    int categoryIdInput;    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_upload);
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent=getIntent();
        userInput=(Category)(intent.getParcelableExtra(ActivityConstants.USER_INPUT));
        grade=intent.getIntExtra(ActivityConstants.GRADE_INPUT,0);
        super.updateCategoryTable(userInput, grade);
        categoryIdInput= userInput.getSubCategory().get(0).getCategoryId();
        //  String l=ActivityConstants.getInstance().getUSERNAME();
        //Toast.makeText(getBaseContext(),l,Toast.LENGTH_LONG).show();
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
        upload.setQuestionText(question_upload);
        upload.setGrade(grade);
        upload.setCategoryId(categoryIdInput);
        intent.putExtra(ActivityConstants.QUESTIONS, upload);
        intent.putExtra(ActivityConstants.GRADE_INPUT,grade);
        intent.putExtra(ActivityConstants.USER_INPUT,userInput);
        startActivity(intent);
    }
    public void backToOptionScreen(View view)
    {
        setContentView(R.layout.activity_optionscreen);
        Intent intent=new Intent(this,Optionscreen.class);
        startActivity(intent);
    }

}
