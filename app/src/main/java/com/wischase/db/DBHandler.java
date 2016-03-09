package com.wischase.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.wischase.Category;
import com.wischase.Question;
import com.wischase.SubCategory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ranjitha on 12/12/2015.
 */

/**
 * DbHandler class provides the database functionality required to create
 * and query  SQLite database
 */
public class DBHandler extends SQLiteOpenHelper{

    /**
     * Context required to load the json file
     */
    private Context context;
    private SQLiteDatabase db;

    /**
     * Construtor
     * @param context
     */

    public DBHandler(Context context) {
        super(context, DBConstants.DB_NAME, null, 1);
        this.context = context;
    }

    /**
     * All the initial setup of tables and data loading done here
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SYSTEMUSER = "systemUser";
        // Create the category table
        db.execSQL(CreateTables.CREATE_CATEGORY_TB);
        // Initial DB setup
        insertCategoryFromJson(db);
        // Create user table
        db.execSQL(CreateTables.CREATE_USER_TB);
        // Insert system user
        addUserInfo(SYSTEMUSER, db);
        // Create question table
        db.execSQL(CreateTables.CREATE_QUESTION_TB);
        // Insert sample questions
        insertSampleQuestions(db);
    }
/* Categories DB operation - start */

    /**
     * Initial data setup. This is called the first time a readable/writable database is accessed
     * @param db
     */
    public void insertCategoryFromJson(SQLiteDatabase db)  {
        try {
            // Reads the category list from Json file
            Type listType = new TypeToken<List<Category>>() {}.getType();
            List<Category> categoryArray = (List<Category>) JsonUtil.categoryFromJson(context, DBConstants.JSON_FILE_NAME, listType);
            // After the list is read now it is time insert into DB
            for (Category categoryObject:categoryArray)              {
                List<SubCategory> subCategoryList = categoryObject.getSubCategory();
                // This is required because each subcategory needs its own id
                for (SubCategory subCategory : subCategoryList) {
                    // Create key value pair to insert into DB
                    insertCategoryInfo(categoryObject.getCategory(), subCategory.getSubCategory(), db);
                }
            }
        }
        catch (Exception io) {
            Log.d("Data setup error : ", io.getMessage());
        }
    }
    /**
     * The categories and subcategories to display
     * @return list of category objects. each object has a category name and a array of sub categories
     * with their category id
     * @throws IOException
     */
    public Map<String, Category> getAllCategories() throws IOException {
        SQLiteDatabase categoryDB = this.getReadableDatabase();
        // Column to be returned from DB.
        String[] columns = {CreateTables.KEY_ID,CreateTables.KEY_CATEGORYNAME,CreateTables.KEY_SUBCATEGORY};
        // The below query returns rows which are grouped by category name. The rows with same category and ordered by category id
        Cursor categoryCursor= categoryDB.query(CreateTables.TABLE_CATEGORY, columns, null, null, null, null, null, null);
        categoryCursor.moveToFirst();
        // This will combine rows with same category name and add the subcategories
        Map<String, Category> categoryMap = cursorToCategoryList(categoryCursor);
        categoryDB.close();
        return categoryMap;
    }

    public long insertUserAddedCatInfo(String category, String subCategory) {
       long insertSuccess = insertCategoryInfo(category, subCategory, this.getWritableDatabase());
        return insertSuccess;
    }
    private long insertCategoryInfo(String category, String subCategory, SQLiteDatabase db)        {

        // Create key value pair to insert into DB
        ContentValues categoryMap = categoryObjectToMap(category, subCategory);
        long categoryRowId = db.insert(CreateTables.TABLE_CATEGORY, null, categoryMap);
        return categoryRowId;
    }
/* Categories DB operation - end */
    /* User DB operation - start */
    /**
     * Insert user info
     * @param userName User name as entered by user
     * @return The rowId. This will be the unique identifier value stored in user Id column.
     */
    public long insertUserInfo(String userName)  {
        SQLiteDatabase userDB = this.getWritableDatabase();
        long userId = addUserInfo(userName, userDB);
        userDB.close();
        return userId;
    }

    private long addUserInfo(String userName, SQLiteDatabase userDB) {
        // User name as entered by the user
        ContentValues userInfo = new ContentValues(1);
        userInfo.put(CreateTables.KEY_USERNAME,userName);
        // Get the rowid. This will be the user id.
        long userId = userDB.insert(CreateTables.TABLE_USER, null, userInfo);
        return userId;
    }

    public boolean getUserInfo(String userName, SQLiteDatabase userDB) {

       //This is incomplete
        return true;
    }


    /* User DB operation - start */
    /* Question table DB operation -start */
public void insertSampleQuestions(SQLiteDatabase db)  {
    try {
        // Reads the category list from Json file
        Type listType = new TypeToken<List<Question>>() {}.getType();
        List<Question> questionArray = (List<Question>) JsonUtil.categoryFromJson(context, DBConstants.JSON_QUESTIONFILE_NAME, listType);
        // After the list is read now it is time insert into DB
        for (Question question:questionArray)              {
            // This is required because each subcategory needs its own id
                // Create key value pair to insert into DB
               long questionId = insertQuestionInfo(question, db);
        }
    }
    catch (Exception io) {
        Log.d("Data setup error : ", io.getMessage());
    }
}

    private long insertQuestionInfo(Question question, SQLiteDatabase db) {
        ContentValues questionInfo = questionToContentValues(question);
        long questionId = db.insert(CreateTables.TABLE_QUESTION, null, questionInfo);
        return questionId;
    }

    /**
     * Insert user added question
     * @param questionObject The user added question represented by a question object
     * @return the question id
     */
    public long insertQuestion(Question questionObject) {
        SQLiteDatabase questionDb = this.getWritableDatabase();
        // Insert the question and return the question id/ rowid
        ContentValues questionInfo = questionToContentValues(questionObject);
        long questionId = questionDb.insert(CreateTables.TABLE_QUESTION, null, questionInfo);
        questionDb.close();
        return questionId;
    }
    private ContentValues questionToContentValues(Question questionObject) {
        ContentValues questionInfo = new ContentValues();
        // Add all the params to the map.
        questionInfo.put(CreateTables.KEY_QUESTION, questionObject.getQuestionText());
        questionInfo.put(CreateTables.KEY_OPTIONONE, questionObject.getOptionOne());
        questionInfo.put(CreateTables.KEY_OPTIONTWO, questionObject.getOptionTwo());
        questionInfo.put(CreateTables.KEY_OPTIONTHREE, questionObject.getOptionThree());
        questionInfo.put(CreateTables.KEY_OPTIONFOUR, questionObject.getOptionFour());
        questionInfo.put(CreateTables.KEY_CORRECTANSWER, questionObject.getCorrectAnswer());
        questionInfo.put(CreateTables.KEY_EXPLANATION, questionObject.getExplanation());
        questionInfo.put(CreateTables.KEY_USER_ID, questionObject.getUserid());
        questionInfo.put(CreateTables.KEY_ID, questionObject.getCategoryId());
        questionInfo.put(CreateTables.KEY_GRADE, questionObject.getGrade());
        return questionInfo;
    }

    /**
     * Query the database for questions
     * @param categoryId
     * @param grade
     * @return
     */
    public List<Question> getAllQuestions ( int categoryId, int grade)  {
        SQLiteDatabase qDb = this.getReadableDatabase();
        String[] columnQuestion = {CreateTables.KEY_QUESTIONID,CreateTables.KEY_QUESTION,CreateTables.KEY_OPTIONONE,CreateTables.KEY_OPTIONTWO, CreateTables.KEY_OPTIONTHREE, CreateTables.KEY_OPTIONFOUR, CreateTables.KEY_CORRECTANSWER, CreateTables.KEY_EXPLANATION, CreateTables.KEY_USER_ID};
        Cursor questionCursor = qDb.query(CreateTables.TABLE_QUESTION, columnQuestion, CreateTables.KEY_ID + " = " + categoryId + " and " + CreateTables.KEY_GRADE + "=" + grade, null, null, null, null);
        List<Question> questionList = cursorToQuestion(questionCursor, categoryId, grade);

                qDb.close();
        return questionList;
    }

    private List<Question> cursorToQuestion(Cursor questionCursor, int categoryId, int grade) {
        int questionTextIndex = questionCursor.getColumnIndex(CreateTables.KEY_QUESTION);
        int questionIdIndex = questionCursor.getColumnIndex(CreateTables.KEY_QUESTIONID);
        int optionOneIndex = questionCursor.getColumnIndex(CreateTables.KEY_OPTIONONE);
        int optionTwoIndex = questionCursor.getColumnIndex(CreateTables.KEY_OPTIONTWO);
        int optionThreeIndex = questionCursor.getColumnIndex(CreateTables.KEY_OPTIONTHREE);
        int optionFourIndex = questionCursor.getColumnIndex(CreateTables.KEY_OPTIONFOUR);
        int correctAnswerIndex = questionCursor.getColumnIndex(CreateTables.KEY_CORRECTANSWER);
        int explanationIndex = questionCursor.getColumnIndex(CreateTables.KEY_EXPLANATION);
        int userIdIndex = questionCursor.getColumnIndex(CreateTables.KEY_USER_ID);


        questionCursor.moveToFirst();
        List<Question> questionList = new ArrayList<Question>();
        while(!questionCursor.isAfterLast())    {
                questionList.add(new Question(questionCursor.getInt(questionIdIndex), questionCursor.getString(questionTextIndex), questionCursor.getString(optionOneIndex), questionCursor.getString(optionTwoIndex), questionCursor.getString(optionThreeIndex),questionCursor.getString(optionFourIndex), questionCursor.getInt(correctAnswerIndex), questionCursor.getString(explanationIndex), grade, questionCursor.getInt(userIdIndex), categoryId));

                questionCursor.moveToNext();
            }

        return questionList;
        }

    /**
     * This converts the cursor object from the database to category list.
     * @param categoryCursor Database result with all the categories
     * @return List that was created from the list
     */
    private Map<String, Category> cursorToCategoryList(Cursor categoryCursor) {
        String categoryName = "";
        int columnIndexOfCategory = categoryCursor.getColumnIndex(JsonUtil.JSON_FIELD_CATEGORY);
        int columnIndexOfSubCategory = categoryCursor.getColumnIndex(JsonUtil.JSON_FIELD_SUBCATEGORY);
        int columnIndexOfCategoryId = categoryCursor.getColumnIndex(JsonUtil.JSON_FIELD_CATEGORYID);
        Map<String,Category> categoryMap = new HashMap<String,Category>();

        try {
            while (!categoryCursor.isAfterLast()) {
                // If the category name of this row is same as the previous row then
                // just add the subcategory to the recently added category object
                if (categoryName != null && categoryName.equals(categoryCursor.getString(columnIndexOfCategory))) {
                     categoryMap.get(categoryName).add(categoryCursor.getString(columnIndexOfSubCategory), categoryCursor.getInt(columnIndexOfCategoryId));
                } else {

                    // This is a new category so create a new object with the row data
                    categoryName = categoryCursor.getString(columnIndexOfCategory);
                    String subCategoryName = categoryCursor.getString(columnIndexOfSubCategory);
                    int categoryId = categoryCursor.getInt(columnIndexOfCategoryId);
                    // add the new object at the index so it is easy to pull out and add the sub categories
                    categoryMap.put(categoryName, new Category(categoryName, subCategoryName, categoryId));
                }
                categoryCursor.moveToNext();
            }

        }
        catch(Exception e)  {
            Log.d("Data error : ",e.getMessage());
        }
        return (categoryMap);
    }

    /**
     * Creates a key value map of category object to be inserted into DB
     * @param category
     * @param subCategory
     * @return
     * @throws IOException
     */
    public static ContentValues categoryObjectToMap(String category, String subCategory)    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateTables.KEY_CATEGORYNAME,category);
        //Category id is not added because there is a auto increament on category id column
        //    contentValues.put(JSON_FIELD_CATEGORYID,categoryJSON.getInt(JSON_FIELD_CATEGORYID) );
        contentValues.put(CreateTables.KEY_SUBCATEGORY,subCategory );
        return contentValues;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
