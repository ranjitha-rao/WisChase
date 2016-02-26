package com.wischase.db;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by ranjitha on 12/12/2015.
 */

/**
 * This class has util methods to work with JSON
 */
final class JsonUtil {
    /**
     * Category name column representation
     */
        public static final String JSON_FIELD_CATEGORY = "categoryName";
    /**
     * Sub category column representatiom
     */
        public static final String JSON_FIELD_SUBCATEGORY = "subCategory";
    /**
     * Category id column representaion
     */
        public static final String JSON_FIELD_CATEGORYID = "categoryId";

        private JsonUtil() {
        }

    /**
     * Pulls out the category list from json file
     * @param context
     * @param jsonFileName
     * @return
     * @throws IOException
     */
        public static List<?> categoryFromJson(Context context, String jsonFileName, Type listType)   {

            Gson categoryGson = new GsonBuilder().create();
            List<?> listFromJson = null;

            try {
                // OPen the json file located in the Assets folder
                Reader jsonReader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(jsonFileName)));

                // Call GSON to read and convert from JSON to List<Category>
                listFromJson = categoryGson.fromJson(jsonReader, listType);
                return listFromJson;
            }

            catch (IOException e) {
                System.out.println("JSON file read : "+e.getMessage());
            }
            catch   (Exception e)   {
                Log.d("JSON read error", e.getMessage());
            }
            return listFromJson;
        }



}

