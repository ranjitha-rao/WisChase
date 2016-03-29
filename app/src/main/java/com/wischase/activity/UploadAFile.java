package com.wischase.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.wischase.Category;
import com.wischase.R;
import com.wischase.view.menu.ScrollingActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

public class UploadAFile extends UpdateTable {
    Category userInput;
    int grade;
    int categoryIdInput;
    private File dir;
    boolean mExternalStorageAvailable = false;
    boolean mExternalStorageWriteable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_afile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent=getIntent();
        userInput=(Category)(intent.getParcelableExtra(ActivityConstants.USER_INPUT));
        grade=intent.getIntExtra(ActivityConstants.GRADE_INPUT,0);
        super.updateCategoryTable(userInput, grade);
        categoryIdInput= userInput.getSubCategory().get(0).getCategoryId();

         /*  SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView=null;
        //= (SearchView) menu.findItem(R.id.menu_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        //return true;
        dir = getFilesDir();
        dir.listFiles();
        */
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void onSearchClick(View view) {
        String state = Environment.getExternalStorageState();
        EditText editText = (EditText) findViewById(R.id.up_file);
        String file_name = editText.getText().toString();

        if (Environment.DIRECTORY_DOWNLOADS.equals(state)) {
            mExternalStorageAvailable = true;
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(path, file_name);
            try {
                path.mkdirs();
                InputStream inputStream = new FileInputStream(file);
                Toast.makeText(getBaseContext(),file_name,Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
