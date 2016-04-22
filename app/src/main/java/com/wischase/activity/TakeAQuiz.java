package com.wischase.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wischase.Category;
import com.wischase.Question;
import com.wischase.R;
import com.wischase.db.DBHandler;
import com.wischase.view.CustomButton;
import com.wischase.view.CustomRadioButton;
import com.wischase.view.CustomTextView;
import com.wischase.view.menu.ScrollingActivity;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

    public class TakeAQuiz extends ScrollingActivity {

        List<Question> questionList;
        int currentCorrectAnswer;
        int totalCorrect = 0;
        int questionIndex = 0;
        int userEnteredAnswer = 0;
        int[] answerOptionRadioButton = {R.id.optionOneRadio,R.id.optionTwoRadio,R.id.optionThreeRadio,R.id.optionFourRadio};
        int offset = 0;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Intent inputIntent = getIntent();
            Category userInput = (Category)(inputIntent.getParcelableExtra(ActivityConstants.USER_INPUT));
            int gradeInput = (int) inputIntent.getLongExtra(ActivityConstants.GRADE_INPUT,0) - 2;
            DBHandler dbHandle = new DBHandler(this);
            int categoryIdInput = userInput.getSubCategory().get(0).getCategoryId();
            offset = inputIntent.getIntExtra(ActivityConstants.QUIZAGAIN,0) * 3;
            questionList = dbHandle.getAllQuestions(categoryIdInput, gradeInput, offset);
            setContentView(R.layout.activity_take_aquiz);
            Chronometer timeElapsed = (Chronometer) findViewById(R.id.timeElapsed);
            updateQuestion(Boolean.FALSE, null);
            updateScoreCard(userInput, gradeInput);
            timeElapsed.start();
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
            questionNum.setText(String.valueOf(questionIndex + 1) + " of " + String.valueOf(questionList.size()));
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
            if(questionIndex == questionList.size()) {
                // If all the questions were already asked show results
                Intent showResult = new Intent(this, QuizResults.class);
                showResult.putExtras(getIntent().getExtras());
                String quizResult = String.valueOf(totalCorrect) + ActivityConstants.OUTOF + String.valueOf(questionList.size());
                showResult.putExtra(ActivityConstants.QUIZRESULTS, quizResult);
              //  showResult.putExtra(ActivityConstants.QUIZAGAIN, offset);
                startActivity(showResult);
                return;
            }
            Question questionToDisplay = questionList.get(questionIndex);
            CustomButton nextQuestion = (CustomButton) findViewById(R.id.nextbutton);
            if(displaySnackBar) {
                // This is not the first question display the result and explanation of previous question
                if(questionToDisplay.getExplanation() != null)  {
                    explantionToDisplay+=   questionToDisplay.getExplanation();
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

                questionIndex++;
                return;
            }


            nextQuestion.setEnabled(Boolean.TRUE);
            currentCorrectAnswer = questionToDisplay.getCorrectAnswer();
            // Set the question text
            CustomTextView questionText = (CustomTextView)findViewById(R.id.questionText);
            String questionString = questionToDisplay.getQuestionText();
            questionText.setText(questionString);
            RadioGroup cleanRadioGroup = (RadioGroup) findViewById(R.id.answerOption);
            cleanRadioGroup.clearCheck();
            // Option one

            CustomRadioButton optionOne = (CustomRadioButton)findViewById(R.id.optionOneRadio);
            String optionOneText = questionToDisplay.getOptionOne();
            optionOne.setText(optionOneText);
            optionOne.setFont(R.color.colorText);

            // Option two

            CustomRadioButton optionTwo = (CustomRadioButton)findViewById(R.id.optionTwoRadio);
            String optionTwoText = questionToDisplay.getOptionTwo();
            optionTwo.setText(optionTwoText);
            optionTwo.setFont(R.color.colorText);

            // Option three

            CustomRadioButton optionThree = (CustomRadioButton)findViewById(R.id.optionThreeRadio);
            String optionThreeText = questionToDisplay.getOptionThree();
            optionThree.setText(optionThreeText);
            optionThree.setFont(R.color.colorText);

            // Option four

            CustomRadioButton optionFour = (CustomRadioButton)findViewById(R.id.optionFourRadio);
            String optionFourText = questionToDisplay.getOptionFour();
            optionFour.setText(optionFourText);
            optionFour.setFont(R.color.colorText);
            // Displaying the explanation

        }

        /**
         * User entered the answer... now for the result
         * @param inputView
         */
        public void nextQuestion(View inputView)  {

            CustomRadioButton correctAnswerRadio = (CustomRadioButton) findViewById(answerOptionRadioButton[(currentCorrectAnswer - 1)]);
                // Get the radio button
            if (correctAnswerRadio.isChecked()) {
                totalCorrect++;
                updateTotalCorrect();
                updateQuestion(Boolean.TRUE, ActivityConstants.RIGHT_ANSWER);
                updateTotalQuestionAnswered();
            }
            else    {
                RadioGroup userEnteredAnswerGroup = (RadioGroup) findViewById(R.id.answerOption);
                CustomRadioButton userEnteredAnswer = (CustomRadioButton) findViewById(userEnteredAnswerGroup.getCheckedRadioButtonId());

                correctAnswerRadio.setFont(R.color.correctAnswer);
                userEnteredAnswer.setFont(R.color.wrongAnswer);
                updateQuestion(Boolean.TRUE, ActivityConstants.WRONG_ANSWER );
                updateTotalQuestionAnswered();
            }


        }

        public void changeCategory(View inputView) {

        }

    }
