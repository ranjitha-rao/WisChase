package com.wischase.activity;

import android.content.Intent;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wischase.Question;
import com.wischase.R;
import com.wischase.db.DBHandler;
import com.wischase.view.CustomRadioButton;

public class AnswerEditing extends UpdateTable
{
    Question question;
    int questionId;
AnswerEditing()
{}
   public void updateQuestion(Question quest, int id)
    {
        question=quest;
        questionId=id;
                // if(questionId!=0)
            EditText op1 = (EditText) findViewById(R.id.up_option1);
            op1.setText(question.getOptionOne());
            EditText op2 = (EditText) findViewById(R.id.up_option2);
            op2.setText(question.getOptionTwo());
            EditText op3 = (EditText) findViewById(R.id.up_option3);
            op3.setText(question.getOptionThree());
            EditText op4 = (EditText) findViewById(R.id.up_option4);
            op4.setText(question.getOptionFour());
            RadioGroup radioGroup = (RadioGroup) findViewById(R.id.options);
            int index = (question.getCorrectAnswer())-1;
            //  radioGroup.check(index-1);
            ((CustomRadioButton)radioGroup.getChildAt(index-1)).setChecked(true);
//return 1;


    }


    public void updateAnswer(int success)
    {
        if(success==0)
        {

            DBHandler dbHandler=new DBHandler(this);
            int i= dbHandler.updateQuestion(question,questionId);
            if(i!=0)
                Toast.makeText(getBaseContext(), "Successfully edited", Toast.LENGTH_LONG).show();
            dbHandler.close();
            Intent intent=new Intent(this,UploadAnother.class);
            intent.putExtras(getIntent().getExtras());
            startActivity(intent);

        }
    }


}
