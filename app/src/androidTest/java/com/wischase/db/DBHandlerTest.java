package com.wischase.db;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.wischase.Category;

import java.util.List;

/**
 * Created by ranji on 12/27/2015.
 */
public class DBHandlerTest extends AndroidTestCase
{

    DBHandler db;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        db = new DBHandler(context);
    }
    @Override
    public void tearDown() throws Exception {
        db.close();
        super.tearDown();
    }


    public void testOnCreate() throws Exception {
        SQLiteDatabase dbRead = db.getWritableDatabase();
        long numRowsCategory = DatabaseUtils.queryNumEntries(dbRead, CreateTables.TABLE_CATEGORY);
        assertTrue(numRowsCategory > 0);
        long numRowsUser = DatabaseUtils.queryNumEntries(dbRead, CreateTables.TABLE_USER);
        assertTrue(numRowsUser == 0);
        long numRowsQuestion = DatabaseUtils.queryNumEntries(dbRead, CreateTables.TABLE_QUESTION);
        assertTrue(numRowsQuestion == 0);
        dbRead.close();
    }

    public void testGetAllCategories() throws Exception {
        List<Category> categoryList = db.getAllCategories();
        assertTrue(categoryList.size() > 0);
    }

    public void testInsertUserAddedCatInfo() throws Exception {
        long categoryId = db.insertUserAddedCatInfo("testCategory", "testSubCategory");
        assertTrue(categoryId != -1);
        SQLiteDatabase dbRead = db.getWritableDatabase();
        dbRead.delete(CreateTables.TABLE_CATEGORY, CreateTables.KEY_ID + " = " +categoryId, null );
        dbRead.close();
    }

    public void testInsertUserInfo() throws Exception {
        long categoryId = db.insertUserInfo("testUserName");
        assertTrue(categoryId != -1);
        SQLiteDatabase dbRead = db.getWritableDatabase();
        dbRead.delete(CreateTables.TABLE_CATEGORY, CreateTables.KEY_ID + " = " + categoryId, null);
        dbRead.close();
    }
}