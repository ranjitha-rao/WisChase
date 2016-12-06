package com.wischase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wischase.Category;
import com.wischase.R;

public class UploadAnother extends AppCompatActivity {

    int grade;
    Category userInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_another);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
Intent gtIntent=getIntent();
        userInput=(Category)(gtIntent.getParcelableExtra(ActivityConstants.USER_INPUT));
        grade=(int)(gtIntent.getLongExtra(ActivityConstants.GRADE_INPUT,0)-2);
        TextView text;
       // Toast.makeText(this,"Successfully Uploaded",Toast.LENGTH_LONG).show();
    }
public void anotherQuestion(View view)
{
    Intent intent=new Intent(this, QuestionUpload.class);
  //  intent.putExtra(ActivityConstants.USER_INPUT,userInput);
    //intent.putExtra(ActivityConstants.GRADE_INPUT,grade);
    intent.putExtras(getIntent().getExtras());
    startActivity(intent);

}
    public void mainMenu(View view)
    {
        Intent intent=new Intent(this, Selectionscreen.class);
        startActivity(intent);

    }
}
