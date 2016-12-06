package com.wischase.activity;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.wischase.Category;
import com.wischase.Question;
import com.wischase.R;
import com.wischase.db.CreateTables;
import com.wischase.db.DBHandler;
import com.wischase.utils.SharedPref;
import com.wischase.view.CustomButton;
import com.wischase.view.CustomRadioButton;
import com.wischase.view.CustomTextView;
import com.wischase.view.menu.ScrollingActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TakeAQuiz extends ScrollingActivity {

    List<Question> questionList;
    int currentCorrectAnswer;
    int totalCorrect = 0;
    int questionIndex = 0;
    //int userEnteredAnswer = 0;
    //int[] answerOptionRadioButton = {R.id.optionOneRadio,R.id.optionTwoRadio,R.id.optionThreeRadio,R.id.optionFourRadio};
    int offset = 0;
    int noOfRadioButton=2;
    String answer;
    int correctAnswerIndex = 0;
    CustomRadioButton correctAnswerRadio;
    RadioGroup cleanRadioGroup;
    int gradeInput;
    Category userInput;
    int questId;
    long questuserId;
    long userId;
    boolean success=false;
    CustomButton deletebutton,updatebutton;
    DBHandler dbHandle = new DBHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent inputIntent = getIntent();
        userInput = (Category)(inputIntent.getParcelableExtra(ActivityConstants.USER_INPUT));
        gradeInput= (int) (inputIntent.getLongExtra(ActivityConstants.GRADE_INPUT,0) - 2);
        int categoryIdInput = userInput.getSubCategory().get(0).getCategoryId();
        setContentView(R.layout.activity_take_aquiz);
        //  offset = inputIntent.getIntExtra(ActivityConstants.QUIZAGAIN,0) * 20;
        questionList = dbHandle.getAllQuestions(categoryIdInput, gradeInput, offset);

        Chronometer timeElapsed = (Chronometer) findViewById(R.id.timeElapsed);
        updateQuestion(Boolean.FALSE, null);
        updateScoreCard(userInput, gradeInput);
        timeElapsed.start();
        SharedPref shared=new SharedPref();
        userId=shared.getId(this);
        deletebutton=(CustomButton)findViewById(R.id.del_quest);
        updatebutton=(CustomButton)findViewById(R.id.up_quest);
    //If userid of the question and userid of the login are not same, then they cannot delete the question
        if((questuserId!=userId)||(questionList.size()==0))
        {
            deletebutton.setVisibility(View.INVISIBLE);
            updatebutton.setVisibility(View.INVISIBLE);
        }
        //If both are same, then they can delete the question
        else {
            deletebutton.setVisibility(View.VISIBLE);
            updatebutton.setVisibility(View.VISIBLE);
        }
    }

    private void updateScoreCard(Category userInput, int gradeInput) {
        CustomTextView categoryText = (CustomTextView) findViewById(R.id.subject);
        categoryText.setText(userInput.getCategory());
        CustomTextView gradeText = (CustomTextView) findViewById(R.id.grade);
        gradeText.setText(String.valueOf(gradeInput));
        updateTotalQuestionAnswered();
        updateTotalCorrect();
        CustomTextView subCategory = (CustomTextView) findViewById(R.id.subCategoryTQHeading);
        subCategory.setText(userInput.getSubCategory().get(0).getSubCategory());
    }

    private void updateTotalQuestionAnswered() {
        CustomTextView questionNum = (CustomTextView) findViewById(R.id.questionNum);
        if(questionList.size()!=0) {

            questionNum.setText(String.valueOf(questionIndex + 1) + " of " + String.valueOf(questionList.size()));
        }
        else
            questionNum.setText(String.valueOf(questionIndex) + " of " + String.valueOf(questionList.size()));
    }

    private void updateTotalCorrect() {
        CustomTextView score = (CustomTextView) findViewById(R.id.score);
        score.setText(String.valueOf(totalCorrect));
    }

    /**
     * This method will called everytime a question has to be updated in the UI
     * @param displaySnackBar
     * @param explantionToDisplay
     */
    void updateQuestion(boolean displaySnackBar, String explantionToDisplay) {
        CustomButton nextQuestion = (CustomButton) findViewById(R.id.nextbutton);
        cleanRadioGroup = (RadioGroup) findViewById(R.id.answerOption);
        CustomTextView questionText = (CustomTextView) findViewById(R.id.questionText);
        if(questionList.size()==0) {
            nextQuestion.setText("Add Questions");
            questionText.setText("No Question Available");
            cleanRadioGroup.removeAllViews();
            nextQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TakeAQuiz.this, QuestionUpload.class);
                    intent.putExtras(getIntent().getExtras());
                    startActivity(intent);
                }
            });
            //deletebutton.setVisibility(View.INVISIBLE);
        }

        else {
            if (questionIndex == questionList.size()) {
                // If all the questions were already asked show results
                Intent showResult = new Intent(this, QuizResults.class);
                showResult.putExtras(getIntent().getExtras());
                Log.v("result:",String.valueOf(totalCorrect));
                String quizResult = String.valueOf(totalCorrect) + ActivityConstants.OUTOF + String.valueOf(questionList.size());
                //NextQuestion button is visible
                nextQuestion.setEnabled(Boolean.TRUE);
                showResult.putExtra(ActivityConstants.QUIZRESULTS, quizResult);
                //  showResult.putExtra(ActivityConstants.QUIZAGAIN, offset);
                startActivity(showResult);
                return;
            }
            Question questionToDisplay = questionList.get(questionIndex);

            if (displaySnackBar) {
                // This is not the first question display the result and explanation of previous question
                if (questionToDisplay.getExplanation() != null) {
                    explantionToDisplay += questionToDisplay.getExplanation();
                }
                // When user clicks the Ok button on the Snackbar display the next question
                View.OnClickListener mOnClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateQuestion(Boolean.FALSE, null);
                    }
                };

                /* Disable the Next question button when SnackBar is displayed */
                nextQuestion.setEnabled(Boolean.FALSE);
                /*Display the snackbar*/
                Snackbar snackBar = Snackbar.make(findViewById(android.R.id.content), explantionToDisplay, Snackbar.LENGTH_INDEFINITE).setAction("Ok", mOnClickListener);
                //To increase the explanation space.
                View snackbarView = snackBar.getView();

                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setMaxLines(5);  // show multiple line
                snackBar.show();
                // Preventing users from correcting the answer after evaluated
                while(noOfRadioButton>0)
                {
                    View view = cleanRadioGroup.getChildAt(noOfRadioButton-1);
                    CustomRadioButton option=(CustomRadioButton)view;
                    option.setClickable(false);
                    noOfRadioButton--;

                }
                questionIndex++;
                Log.v("question index:", String.valueOf(questionIndex));
                return;
            }

            updateTotalQuestionAnswered();
            nextQuestion.setEnabled(Boolean.TRUE);
            currentCorrectAnswer = questionToDisplay.getCorrectAnswer();
            /*Getting index of correctAnswer and getting value as answer  */
            if (currentCorrectAnswer == 1) answer = questionToDisplay.getOptionOne();
            else if (currentCorrectAnswer == 2) answer = questionToDisplay.getOptionTwo();
            else if (currentCorrectAnswer == 3) answer = questionToDisplay.getOptionThree();
            else answer = questionToDisplay.getOptionFour();
            questId=questionToDisplay.getQuestionId();
            questuserId=questionToDisplay.getUserid();
            // Set the question text

            String questionString = questionToDisplay.getQuestionText();
            questionText.setText(questionString);
// clearing the radiogroup
            cleanRadioGroup.clearCheck();
            noOfRadioButton = 2;
            String optionOneText = questionToDisplay.getOptionOne();
            String optionTwoText = questionToDisplay.getOptionTwo();
            String optionThreeText = questionToDisplay.getOptionThree();
            String optionFourText = questionToDisplay.getOptionFour();

//Adding options into array.
            ArrayList<String> options = new ArrayList<String>();
            options.add(optionOneText);
            options.add(optionTwoText);
           if ((optionThreeText!=null)&&(!(optionThreeText.equals(" ")))) {

            options.add(optionThreeText);
                noOfRadioButton = 3;
            }
            if ((optionFourText!=null)&&(!(optionFourText.equals(" ")))) {
                    options.add(optionFourText);
                    noOfRadioButton = 4;

            }
            //Shuffling the options
            Collections.shuffle(options);
            int siz=options.size();
          //  Toast.makeText(this,"length is " + siz,Toast.LENGTH_LONG).show();
// Option one

            int j = 0;
            //displaying the options which are not empty
            for (int i = 0; i <siz; i++) {
                View view = cleanRadioGroup.getChildAt(j);
                CustomRadioButton option;
             //Checking whether it is null or empty string
                if(!(TextUtils.isEmpty(options.get(i)))) {
                        option = (CustomRadioButton) view;
                        option.setClickable(true);
                        option.setVisibility(View.VISIBLE);
                        option.setText(options.get(i).toString());
                        option.setFont(R.color.colorText);
                        j++;
                    }
            }
            //hiding other radio buttons if there is no option3 or option 4
            for (; j < 4; j++) {
                View view = cleanRadioGroup.getChildAt(j);
                CustomRadioButton option = (CustomRadioButton) view;
                option.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * User entered the answer... now for the result
     * @param inputView
     */
    public void nextQuestion(View inputView)  {

        if(questionIndex<questionList.size()) {
            Log.v("question index:", String.valueOf(questionIndex));
            RadioGroup radioGroup = (RadioGroup) findViewById(R.id.answerOption);
            int index = radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));
            CustomRadioButton userAnswerRadio = (CustomRadioButton) radioGroup.getChildAt(index);
            //No answer is selected.
            if (index == -1) {
                int correctAnswerIndex = findAnswerRadio(radioGroup, noOfRadioButton);
                correctAnswerRadio = (CustomRadioButton) radioGroup.getChildAt(correctAnswerIndex);
                correctAnswerRadio.setFont(R.color.correctAnswer);
                updateQuestion(Boolean.TRUE, ActivityConstants.WRONG_ANSWER);
            }
            //If answer is selected it is evaluated.
            else {
                String userAnswer = userAnswerRadio.getText().toString();
                //Checking user entered option is correct
                if (userAnswer.equals(answer)) {
                    totalCorrect++;
                    //Log.v("Inside loop", String.valueOf(totalCorrect));
                    updateTotalCorrect();
                    userAnswerRadio.setFont(R.color.correctAnswer);
                    updateQuestion(Boolean.TRUE, ActivityConstants.RIGHT_ANSWER);
                }//If not correct answer selected by user
                else {
                    int correctAnswerIndex = findAnswerRadio(radioGroup, noOfRadioButton);
                    correctAnswerRadio = (CustomRadioButton) radioGroup.getChildAt(correctAnswerIndex);
                    correctAnswerRadio.setFont(R.color.correctAnswer);
                    userAnswerRadio.setFont(R.color.wrongAnswer);
                    updateQuestion(Boolean.TRUE, ActivityConstants.WRONG_ANSWER);
                }

            }
        }
        else
        {
            questionIndex=questionList.size();
            Log.v("No of correct",String.valueOf(totalCorrect));
            updateQuestion(Boolean.TRUE, ActivityConstants.WRONG_ANSWER);
        }
    }

    public void changeCategory(View inputView) {
        Intent chgIntent=new Intent(this,Selectionscreen.class);
        chgIntent.putExtras(getIntent().getExtras());
        startActivity(chgIntent);
    }
    /* Finding the correct answer from the radiogroup  and displaying it in green*/
    public int findAnswerRadio(RadioGroup radioGroup, int number)
    {

        for (int arr = 0; arr < number; arr++) {
            correctAnswerRadio = (CustomRadioButton) radioGroup.getChildAt(arr);
            String correctAnswer = correctAnswerRadio.getText().toString();
            if (answer.equals(correctAnswer)) correctAnswerIndex = arr;

        }
        return correctAnswerIndex;
    }
    public void delQuestion(View view)
    {
      View.OnClickListener mdel = new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              // SharedPreferences editor=getSharedPreferences(ActivityConstants.MYPREFERENCE, Context.MODE_PRIVATE);
              //long userID=editor.getLong(ActivityConstants.USER_ID, 2);
              success = dbHandle.deleteQuestion(questId);
              deletedornot(view);
          }
      };


      Snackbar snackBar = Snackbar.make(findViewById(android.R.id.content), "Do you want to really delete", Snackbar.LENGTH_INDEFINITE).setAction("Yes", mdel);
      snackBar.show();

       }
    public void deletedornot(View view)
    {
    if(success==true) {
        Toast.makeText(this, "Deleted successfully", Toast.LENGTH_LONG).show();
        nextQuestion(view);
    }}
public void updateQuest(View view){
    /*View.OnClickListener mupdate = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // SharedPreferences editor=getSharedPreferences(ActivityConstants.MYPREFERENCE, Context.MODE_PRIVATE);
            //long userID=editor.getLong(ActivityConstants.USER_ID, 2);*/
            Intent intent=new Intent(this,QuestionUpload.class);
            intent.putExtras(getIntent().getExtras());
            intent.putExtra(ActivityConstants.QUEST_NO, questId);
            startActivity(intent);

        }
    //};


    /*Snackbar snackBar = Snackbar.make(findViewById(android.R.id.content), "Do you want to edit the question", Snackbar.LENGTH_INDEFINITE).setAction("Yes", mupdate);
    snackBar.show();

}*/
    @Override
    public void onBackPressed()
    {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit the quiz?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                       TakeAQuiz.super.onBackPressed();
                    }
                }).create().show();
    }
}
