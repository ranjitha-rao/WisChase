package com.wischase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.wischase.Question;
import com.wischase.R;
import com.wischase.db.DBHandler;
import com.wischase.view.CustomTextView;
import com.wischase.view.menu.ScrollingActivity;

import java.util.List;

public class TakeAQuiz extends ScrollingActivity {

    List<Question> questionList;
    int currentCorrect = 0;
    int totalCurrent = 0;
    int questionIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent inputIntent = getIntent();
        Question question = (Question)(inputIntent.getParcelableExtra(ActivityConstants.QUESTION_INPUT));
        DBHandler dbHandle = new DBHandler(this);
        questionList = dbHandle.getAllQuestions(question.getCategoryId(), question.getGrade());
        setContentView(R.layout.activity_take_aquiz);
        updateQuestion(questionList.get(questionIndex));
        questionIndex++;

    }

    void updateQuestion(Question questionToDisplay) {
        // Set the question text
        CustomTextView questionText = (CustomTextView)findViewById(R.id.questionText);
        String questionString = questionToDisplay.getQuestionText();
        questionText.setText(questionString);

        // Option one

        CustomTextView optionOne = (CustomTextView)findViewById(R.id.optionOneText);
        String optionOneText = questionToDisplay.getOptionOne();
        optionOne.setText(optionOneText);

        // Option two

        CustomTextView optionTwo = (CustomTextView)findViewById(R.id.optionTwoText);
        String optionTwoText = questionToDisplay.getOptionTwo();
        optionTwo.setText(optionTwoText);

        // Option three

        CustomTextView optionThree = (CustomTextView)findViewById(R.id.optionThreeText);
        String optionThreeText = questionToDisplay.getOptionThree();
        optionThree.setText(optionThreeText);

        // Option four

        CustomTextView optionFour = (CustomTextView)findViewById(R.id.optionFourText);
        String optionFourText = questionToDisplay.getOptionFour();
        optionFour.setText(optionFourText);


    }

}
