package com.wischase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wischase.Category;
import com.wischase.Question;
import com.wischase.R;
import com.wischase.db.DBHandler;
import com.wischase.view.CustomTextView;
import com.wischase.view.menu.ScrollingActivity;

import java.util.List;

    public class TakeAQuiz extends ScrollingActivity {

        List<Question> questionList;
        String currentCorrectAnswer;
        int totalCorrect = 0;
        int questionIndex = 0;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Intent inputIntent = getIntent();
            Category userInput = (Category)(inputIntent.getParcelableExtra(ActivityConstants.USER_INPUT));
            int gradeInput = inputIntent.getIntExtra(ActivityConstants.GRADE_INPUT,0);
            DBHandler dbHandle = new DBHandler(this);
            int categoryIdInput = userInput.getSubCategory().get(0).getCategoryId();
            questionList = dbHandle.getAllQuestions(categoryIdInput, gradeInput);
            setContentView(R.layout.activity_take_aquiz);
            currentCorrectAnswer = String.valueOf(questionList.get(questionIndex).getCorrectAnswer());
            Chronometer timeElapsed = (Chronometer) findViewById(R.id.timeElapsed);
            updateQuestion(questionList.get(questionIndex));
            updateScoreCard(userInput, gradeInput);
            timeElapsed.start();
        }

        private void updateScoreCard(Category userInput, int gradeInput) {
            CustomTextView categoryText = (CustomTextView) findViewById(R.id.subject);
            categoryText.setText(userInput.getCategory());
            CustomTextView gradeText = (CustomTextView) findViewById(R.id.grade);
            gradeText.setText(String.valueOf(gradeInput));
            CustomTextView questionNum = (CustomTextView) findViewById(R.id.questionNum);
            questionNum.setText(String.valueOf(questionIndex+1) + " of "+String.valueOf(questionList.size()));
            CustomTextView score = (CustomTextView) findViewById(R.id.score);
            score.setText(String.valueOf(totalCorrect));


        }

        /**
         * This method will called everytime a question has to be updated in the UI
         * @param questionToDisplay
         */
        void updateQuestion(Question questionToDisplay) {
            questionIndex++;

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

        /**
         * User entered the answer... now for the result
         * @param inputView
         */
        public void nextQuestion(View inputView)  {
            RadioGroup quizAnswer = (RadioGroup) findViewById(R.id.answerOption);
            int userEnteredAnswer = quizAnswer.getCheckedRadioButtonId();
            // TODO : If userEnteredAnswer == -1 that means the user has not chosen any option
            // Should we display a toast asking him to choose a option???

            if(userEnteredAnswer == -1) {
                Toast.makeText(this,"No answer selected(?)",Toast.LENGTH_SHORT).show();
            }
            else {
                // Get the radio button
                RadioButton userAnswer = (RadioButton) findViewById(userEnteredAnswer);
                if (currentCorrectAnswer.equals(userAnswer.getText().toString())) {
                    Toast.makeText(this, "Right Answer!", Toast.LENGTH_SHORT).show();
                    updateQuestion(questionList.get(questionIndex));
                }
            }

        }

        public void changeCategory(View inputView) {

        }
    }
