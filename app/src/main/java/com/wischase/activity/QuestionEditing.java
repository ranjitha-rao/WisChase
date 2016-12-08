package com.wischase.activity;

import android.widget.EditText;
import android.widget.Toast;

import com.wischase.Question;
import com.wischase.R;
import com.wischase.db.DBHandler;

/**
 * Created by disha on 11/17/2016.
 */
public class QuestionEditing extends UpdateTable {
    int questionId;
    Question question;
public Question updateQuestion(Question quest,int qid)
{
    question=quest;
    questionId=qid;
    EditText existing_question=(EditText)findViewById(R.id.question);
    DBHandler dbHandler=new DBHandler(this);
    question=dbHandler.getAQuestion(questionId);
    String questionTxt= question.getQuestionText();
    existing_question.setText(questionTxt);
    //Toast.makeText(this, "categoryid is " + question.getCategoryId(), Toast.LENGTH_LONG).show();
return question;
}
}
