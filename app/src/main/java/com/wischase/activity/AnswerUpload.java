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
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wischase.Category;
import com.wischase.Question;
import com.wischase.R;
import com.wischase.db.DBHandler;
import com.wischase.view.menu.ScrollingActivity;

import java.util.List;

public class AnswerUpload extends UpdateTable {
Question question;
    int grade;
    Category userInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_upload);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
   //Toast.makeText(getBaseContext(),q,Toast.LENGTH_LONG).show();
        Intent inputIntent = getIntent();
        question = inputIntent.getParcelableExtra(ActivityConstants.QUESTIONS);
        userInput=(Category)(inputIntent.getParcelableExtra(ActivityConstants.USER_INPUT));
        grade=inputIntent.getIntExtra(ActivityConstants.GRADE_INPUT,0);
        super.updateCategoryTable(userInput, grade);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public int validatingAnswer(View view) {
        EditText op1 = (EditText) findViewById(R.id.up_option1);
        String option1 = op1.getText().toString();
        EditText op2 = (EditText) findViewById(R.id.up_option2);
        String option2 = op2.getText().toString();
        EditText op3 = (EditText) findViewById(R.id.up_option3);
        String option3 = op3.getText().toString();
        EditText op4 = (EditText) findViewById(R.id.up_option4);
        String option4 = op4.getText().toString();
        int i=-1;
           if ((option1.equals(" ")) || (option2.equals(" "))) {
                Toast.makeText(getBaseContext(), "Need minimum 2 options to upload", Toast.LENGTH_LONG).show();
            } else {
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.options);
                int index = radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));
                if (index == -1) {
                    Toast.makeText(getBaseContext(), "Please select correct answer", Toast.LENGTH_LONG).show();

                } else {
                    i = 0;
                    question.setOptionOne(option1);
                    question.setOptionTwo(option2);
                    question.setOptionThree(option3);
                    question.setOptionFour(option4);
                    question.setCorrectAnswer(index + 1);
                    /* Need to set category id, userid,grade*/

                }
            }
        return i;
    }
    public void uploadAnswer(View view)
    {
        if((validatingAnswer(view))==0)
        {
            Parcel questionParcel=null;
            question.writeToParcel(questionParcel,0);
            DBHandler dbHandler=new DBHandler(this);
           long i= dbHandler.insertQuestion(question);
            Toast.makeText(getBaseContext(),"successful",Toast.LENGTH_LONG).show();
        }
    }
    public void nextExplanation(View view)
    {
        if((validatingAnswer(view))==0)
        {
            setContentView(R.layout.activity_explanation_upload);
            Intent intent=new Intent(this,ExplanationUpload.class);
            intent.putExtra(ActivityConstants.QUESTIONS,question );
            intent.putExtra(ActivityConstants.GRADE_INPUT, grade);
            intent.putExtra(ActivityConstants.USER_INPUT,userInput);
            startActivity(intent);

        }

    }
}