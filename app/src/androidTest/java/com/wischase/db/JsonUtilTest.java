package com.wischase.db;

import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.MediumTest;

import junit.framework.TestCase;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.wischase.Category;

import junit.framework.TestCase;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by ranji on 12/27/2015.
 */
public class JsonUtilTest extends InstrumentationTestCase  {
    private Context instrumantationCtx;
    private String testCategoryInputFileName = "CategoryJunit.json";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        instrumantationCtx = getInstrumentation().getTargetContext();
    }

    public void testCategoryFromJson() throws Exception {
        Type listType = new TypeToken<List<Category>>() {}.getType();
        List<Category> categoryList = (List<Category>) JsonUtil.categoryFromJson(instrumantationCtx, testCategoryInputFileName, listType);
        assertEquals( 5, categoryList.size());
    }

    public void testCategoryObjectToMap() throws Exception {

    }
}