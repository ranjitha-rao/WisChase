package com.wischase.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

import com.wischase.activity.ActivityConstants;

/**
 * Created by sri_suki on 11/2/2016.
 */
public class SharedPref {

    //Shared Preference instance

    SharedPreferences settings;
    Editor editor;

     public SharedPref() {
        super();
    }

   public void save(Context context, Long UserId, String UsrName) {
        //Put the values in the shared preference
        settings = context.getSharedPreferences(ActivityConstants.MyPREFERENCES, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putLong(ActivityConstants.USER_ID, UserId);
        editor.putString(ActivityConstants.USER_NAME, UsrName);
        editor.commit();
       //Toast to test if this method has been invoked. Need to remove after testing
       Toast.makeText(context, "Save Method is called", Toast.LENGTH_LONG).show();
   }

    // Get the User Id saved as Shared Preference
    public Long getId(Context context) {
        Long text1;
        settings = context.getSharedPreferences(ActivityConstants.MyPREFERENCES, Context.MODE_PRIVATE);
        text1 = settings.getLong(ActivityConstants.USER_ID,0);
        return text1;
        }

    // Get the User Name saved as Shared Preference
    public String getUsrname(Context context) {
        String text2;
        settings = context.getSharedPreferences(ActivityConstants.MyPREFERENCES, Context.MODE_PRIVATE);
        text2= settings.getString(ActivityConstants.USER_NAME,null);
        return text2;
    }

    //Clear Shared Preference values
    public void clearSharedPreference(Context context) {
        settings = context.getSharedPreferences(ActivityConstants.MyPREFERENCES, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.clear();
        editor.commit();

    }
}
