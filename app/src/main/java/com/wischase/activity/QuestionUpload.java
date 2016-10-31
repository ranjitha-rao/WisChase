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
import com.wischase.db.DBHandler;
import com.wischase.view.CustomTextView;
import com.wischase.view.menu.ScrollingActivity;

public class QuestionUpload extends UpdateTable {
int grade;
    Category userInput;
    int categoryIdInput;
    int questionId;
    Question question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_upload);
        Intent intent=getIntent();
        userInput =(Category)(intent.getParcelableExtra(ActivityConstants.USER_INPUT));
        grade=(int)(intent.getLongExtra(ActivityConstants.GRADE_INPUT,0)-2);
        questionId=(int)(intent.getIntExtra(ActivityConstants.QUEST_NO, 0));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.updateCategoryTable(userInput, grade);
        categoryIdInput= userInput.getSubCategory().get(0).getCategoryId();
        //  String l=ActivityConstants.getInstance().getUSERNAME();
        //Toast.makeText(getBaseContext(),l,Toast.LENGTH_LONG).show();
      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        if(questionId!=0)
        {
            EditText existing_question=(EditText)findViewById(R.id.question);
            DBHandler dbHandler=new DBHandler(this);
            question=dbHandler.getAQuestion(questionId);
           String questionTxt= question.getQuestionText();
            existing_question.setText(questionTxt);
        }

    }

    public void addAnswerToUpload(View view)
    {
        /* Getting the value of question edittext and save it in string question_upload*/
        EditText editText=(EditText)findViewById(R.id.question);
        String question_upload=editText.getText().toString();
        if((question_upload!=null)||(question_upload!=" ")) {
            //setContentView(R.layout.activity_answer_upload);
            Intent intent = new Intent(this, AnswerUpload.class);
            if (questionId != 0) {
                intent.putExtra(ActivityConstants.QUESTIONS, question);
                intent.putExtras(getIntent().getExtras());
                startActivity(intent);
            }
            else {
                Question upload = new Question();
                upload.setQuestionText(question_upload);
                //After shared preference done need to add this user id
                // upload.setUserid();
                upload.setGrade(grade);
                upload.setCategoryId(categoryIdInput);

                intent.putExtra(ActivityConstants.QUESTIONS, upload);
                intent.putExtras(getIntent().getExtras());
                //  intent.putExtra(ActivityConstants.GRADE_INPUT,grade);
                //intent.putExtra(ActivityConstants.USER_INPUT,userInput);
                startActivity(intent);
            }
        }
        else
            Toast.makeText(this,"Please add a question",Toast.LENGTH_LONG).show();
    }
    public void backToOptionScreen(View view)
    {
        //setContentView(R.layout.activity_optionscreen);
        Intent intent=new Intent(this,Optionscreen.class);
        startActivity(intent);
    }

}
