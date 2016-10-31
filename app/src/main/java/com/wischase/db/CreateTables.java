package com.wischase.db;

/**
 * Created by ranjitha on 12/12/2015.
 */
public class CreateTables {
    /**
     * Category table name
     */
    public static String TABLE_CATEGORY = "category";
    /**
     * Category id column. Primary key with auto increament
     */
    public static String KEY_ID = "categoryId";
    /**
     * Category name column representation
     */
    public static String KEY_CATEGORYNAME = "categoryName";
    /**
     * Sub category column
     */
    public static String KEY_SUBCATEGORY = "subCategory";
    /*constraint for subCategory and categoryName*/
    public static String KEY_CATEGORIES="categories";
    /**
     * Keep track of inserted the category.
     */
    public static String KEY_USER = "USERID";
    /**
     * Create statement. Initial database setup
     */
    public final static String CREATE_CATEGORY_TB = "CREATE TABLE IF NOT EXISTS " + TABLE_CATEGORY + " (" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CATEGORYNAME + " TEXT not null," + KEY_SUBCATEGORY + " TEXT not null," + KEY_USER + " TEXT,"+"constraint "+ KEY_CATEGORIES + " unique("+ KEY_CATEGORYNAME+","+KEY_SUBCATEGORY+"))";

    /**
     * User table name
     */
    public static final String TABLE_USER = "userinfo";

    /**
     * User id. The rowid will be the user id.
     */
    public static final String KEY_USER_ID = "userid";

    /**
     * User name s entered by the user
     */
    public static final String KEY_USERNAME = "username";
    /**
     * Create statement for User table. Intial data setup
     */
    public final static String CREATE_USER_TB = "CREATE TABLE IF NOT EXISTS " + TABLE_USER + " (" + KEY_USER_ID + " INTEGER PRIMARY KEY," + KEY_USERNAME + " TEXT not null unique )";

    public static final String TABLE_QUESTION = "question";
    public static final String KEY_GRADE = "grade";
    public static final String KEY_QUESTION = "questiontext";
    public static final String KEY_QUESTIONID = "questionid";
    public static final String KEY_OPTIONONE = "optionone";
    public static final String KEY_OPTIONTWO = "optiontwo";
public static final String KEY_RANDOM="RANDOM()";
    public static final String KEY_OPTIONTHREE = "optionthree";
    public static final String KEY_OPTIONFOUR = "optionfour" ;
    public static final String KEY_CORRECTANSWER = "correctanswer";
    public static final String KEY_EXPLANATION = "explanation";

    /**
     * Create statement for Question table. Intial data setup
     */
    public final static String CREATE_QUESTION_TB = "CREATE TABLE IF NOT EXISTS " + TABLE_QUESTION + " (" + KEY_USER_ID + " INTEGER , " + KEY_ID + " INTEGER , " + KEY_GRADE + " INTEGER , "+KEY_QUESTIONID+" INTEGER PRIMARY KEY , "+ KEY_QUESTION +" TEXT , "+ KEY_OPTIONONE +" TEXT , " + KEY_OPTIONTWO +" TEXT , " + KEY_OPTIONTHREE +" TEXT , " + KEY_OPTIONFOUR +" TEXT , " + KEY_CORRECTANSWER + " TEXT , " +KEY_EXPLANATION + " TEXT , "+ "FOREIGN KEY( " + KEY_ID +" ) REFERENCES " + TABLE_CATEGORY + "( " +KEY_ID + "), FOREIGN KEY( " + KEY_USER_ID +" ) REFERENCES " + TABLE_USER + "( " +KEY_USER_ID + ") )" ;

public static final String DELETE_QUESTION="DELETE FROM"+TABLE_QUESTION;




}
