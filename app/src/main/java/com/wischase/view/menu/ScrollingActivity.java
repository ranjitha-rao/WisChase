package com.wischase.view.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;

import com.wischase.R;

import java.lang.reflect.Field;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        // Find the toolbar
        Toolbar  toolbar = (Toolbar) findViewById(R.id.app_bar);
        // Need to figure out how to do this in XML
        toolbar.setTitle("");
        // Make the toolbar the action bar
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Initial code for menu item. Need to update.
        switch (item.getItemId()) {
            case R.id.action_takeQuiz:
                // The user wants to take a quiz
                return true;

            case R.id.action_upload_question:
                // User wants to upload a single question
                return true;

            case R.id.action_upload_bank:
                // User wants to upload a question bank
                return true;

            case R.id.action_exit:
                // User wants to exit the application
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
