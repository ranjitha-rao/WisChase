package com.wischase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
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
import com.wischase.view.menu.ScrollingActivity;

public class ExplanationUpload extends UpdateTable {
    Question question;
    Category userInput;
    int grade,questionId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explanation_upload);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent=getIntent();
        question=intent.getParcelableExtra(ActivityConstants.QUESTIONS);
        //String s=question.getQuestionText();
        //Toast.makeText(getBaseContext(),s,Toast.LENGTH_LONG).show();
        userInput=(Category)(intent.getParcelableExtra(ActivityConstants.USER_INPUT));
        grade=(int)(intent.getLongExtra(ActivityConstants.GRADE_INPUT, 0)-2);
        questionId=(int)intent.getIntExtra(ActivityConstants.QUEST_NO,0);

        super.updateCategoryTable(userInput, grade);
        EditText explanation=(EditText)findViewById(R.id.explanation);

        if(questionId!=0)
{
    explanation.setText(question.getExplanation());
}
        else
        explanation.setText(" ");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

   public void uploadExplanation(View view)
    {
        EditText explan=(EditText)findViewById(R.id.explanation);
        String s=explan.getText().toString();
        question.setExplanation(s);
        DBHandler dbHandler=new DBHandler(this);
        if(questionId!=0)
        {
long i=dbHandler.updateQuestion(question, questionId);
            if (i!=0)
            Toast.makeText(this,"Successfully edited",Toast.LENGTH_LONG).show();
            dbHandler.close();
        }
        else {
            //  Parcel questionParcel=null;
            //    question.writeToParcel(questionParcel,0);
            long i = dbHandler.insertQuestion(question);
            Toast.makeText(this, "qn no is" + i, Toast.LENGTH_LONG).show();
            dbHandler.close();
        }
        Intent intent=new Intent(this,UploadAnother.class);
        getIntent().removeExtra(ActivityConstants.QUESTIONS);
       // intent.putExtra(ActivityConstants.GRADE_INPUT, grade);
        intent.putExtras(getIntent().getExtras());
        startActivity(intent);

       // Toast.makeText(getBaseContext(),"Successfully uploaded",Toast.LENGTH_LONG).show();
    }
}
