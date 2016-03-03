package com.wischase;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ranji on 12/28/2015.
 */
public class Question  implements Parcelable {

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
    /**
     * Grade for which the question is applicable
     */
    private int grade;

    /**
     * Category under which the question has to be listed
     */
    private int categoryId;

    /**
     * Who uploaded the question.
     */
    private long userid;

    /**
     * Some explanation on the correct answer.
     */
    private String explanation;

    public Question()   {

    }
    /**
     * Constructor for a question object
     * @param questionId
     * @param questionText
     * @param optionOne
     * @param optionTwo
     * @param optionThree
     * @param optionFour
     * @param explanation
     * @param correctAnswer
     * @param grade
     * @param userid
     * @param categoryId
     */
    public Question(int questionId, String questionText, String optionOne, String optionTwo, String optionThree, String optionFour,int correctAnswer,String explanation, int grade, long userid, int categoryId ) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.optionThree = optionThree;
        this.optionFour = optionFour;
        this.correctAnswer = correctAnswer;
        this.explanation = explanation;
        this.grade = grade;
        this.categoryId = categoryId;
        this.userid = userid;
    }

    public  int getGrade() { return grade;     }

    public void setGrade(int grade) { this.grade = grade;   }

    public int getCategoryId() { return categoryId;    }

    public String getExplanation() {    return explanation;    }

    public void setExplanation(String explanation) {    this.explanation = explanation;     }

    public void setCategoryId(int categoryId) {   this.categoryId = categoryId;     }

    public long getUserid() {   return userid;     }

    public void setUserid(long userid) {
        this.userid = userid;
    }

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

    /* Parcelable interface implementation*/
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel questionParcel, int flags) {
        questionParcel.writeInt(questionId);
        questionParcel.writeString((questionText == null) ? "" : questionText);
        questionParcel.writeString((optionOne == null) ? "" : optionOne);
        questionParcel.writeString((optionTwo == null) ? "" : optionTwo);
        questionParcel.writeString((optionThree == null) ? "" : optionThree);
        questionParcel.writeString((optionFour == null) ? "" : optionFour);
        questionParcel.writeInt(correctAnswer);
        questionParcel.writeString((explanation == null) ? "" : explanation);
        questionParcel.writeInt(grade);
        questionParcel.writeInt(categoryId);
        questionParcel.writeLong(userid);
    }

    public Question(Parcel questionParcel)  {
        questionId = questionParcel.readInt();
        questionText = questionParcel.readString();
        optionOne = questionParcel.readString();
        optionTwo = questionParcel.readString();
        optionThree = questionParcel.readString();
        optionFour = questionParcel.readString();
        correctAnswer = questionParcel.readInt();
        explanation = questionParcel.readString();
        grade = questionParcel.readInt();
        categoryId = questionParcel.readInt();
        userid = questionParcel.readLong();
    }
    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
    /* Parcelable interface implementation*/

}
