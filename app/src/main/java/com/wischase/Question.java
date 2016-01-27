package com.wischase;

import android.database.Cursor;

/**
 * Created by ranji on 12/28/2015.
 */
public class Question   {

    /**
     *  The questionId is the rowid for the question
     */
    private int questionId;
    /**
     *   Question text.
     */
    private String questionText;
    /**
     *  Option one for the question
     */
    private String optionOne;
    /**
     * Option two for the question
     */
    private String optionTwo;
    /**
     *  Option three for the question
     */
    private String optionThree;
    /**
     *  Option four for the question
     */
    private String optionFour;
    /**
     * The option number of the correct answer
     */
    private int correctAnswer;

    private int grade;

    private int categryId;

    private int userid;

     /**
     *
     * @param questionId

     * @param questionText
     * @param optionOne
     * @param optionTwo
     * @param optionThree
     * @param optionFour
     * @param correctAnswer
     */

    public Question(int questionId, String questionText, String optionOne, String optionTwo, String optionThree, String optionFour, int correctAnswer, int grade, int userid, int categoryId ) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.optionThree = optionThree;
        this.optionFour = optionFour;
        this.correctAnswer = correctAnswer;
        this.grade = grade;
        this.categryId = categoryId;
        this.userid = userid;
    }
    public Question(int questionId, String questionText, String optionOne, String optionTwo, String optionThree, String optionFour, int correctAnswer ) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.optionThree = optionThree;
        this.optionFour = optionFour;
        this.correctAnswer = correctAnswer;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
    public int getCategryId() {
        return categryId;
    }

    public void setCategryId(int categryId) {
        this.categryId = categryId;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    /**
     * The grade for which this question is applicable
     */

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getOptionOne() {
        return optionOne;
    }

    public void setOptionOne(String optionOne) {
        this.optionOne = optionOne;
    }

    public String getOptionTwo() {
        return optionTwo;
    }

    public void setOptionTwo(String optionTwo) {
        this.optionTwo = optionTwo;
    }

    public String getOptionThree() {
        return optionThree;
    }

    public void setOptionThree(String optionThree) {
        this.optionThree = optionThree;
    }

    public String getOptionFour() {
        return optionFour;
    }

    public void setOptionFour(String optionFour) {
        this.optionFour = optionFour;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }


}
