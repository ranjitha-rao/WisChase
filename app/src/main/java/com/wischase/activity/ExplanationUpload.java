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

import com.wischase.Question;
import com.wischase.R;
import com.wischase.db.DBHandler;
import com.wischase.view.menu.ScrollingActivity;

public class ExplanationUpload extends ScrollingActivity {
    Question question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explanation_upload);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent=getIntent();
        question=intent.getParcelableExtra("answer");
        String s=question.getQuestionText();
        Toast.makeText(getBaseContext(),s,Toast.LENGTH_LONG).show();

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

        Parcel questionParcel=null;
        question.writeToParcel(questionParcel,0);
        DBHandler dbHandler=new DBHandler(this);
        long i= dbHandler.insertQuestion(question);

        Toast.makeText(getBaseContext(),"Successfully uploaded",Toast.LENGTH_LONG).show();
    }
}
