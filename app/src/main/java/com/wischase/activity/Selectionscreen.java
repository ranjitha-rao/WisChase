package com.wischase.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.wischase.Category;
import com.wischase.R;
import com.wischase.SubCategory;
import com.wischase.db.DBHandler;
import com.wischase.view.menu.ScrollingActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Selectionscreen extends ScrollingActivity implements AdapterView.OnItemSelectedListener {
    Spinner gradearray, categorydd, subcategorydd; //variable to define the Grade drop down, Category and Sub Category dropdown.
    Map<String, Category> categorylist;
    List<SubCategory> subcategorylist;
    Button nextbutton, addcategorybutton;
    List<Category> list;
    List<SubCategory> sublist;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectionscreen);
        gradearray = (Spinner) findViewById(R.id.Grade_Array);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.grade_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gradearray.setAdapter(adapter);
        gradearray.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    System.out.println("Select a Grade Level");
                } else
                    Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        updateCategory();
        addcategorybutton = (Button) findViewById(R.id.AddCategory);
        addcategorybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addcatIntent = new Intent(Selectionscreen.this, Addcategory.class);
                startActivity(addcatIntent);
            }
        });
        nextbutton = (Button) findViewById(R.id.Next);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if else to be written.
                Intent nextIntent = new Intent(Selectionscreen.this, Optionscreen.class);
                startActivity(nextIntent);
            }
        });
    }

    void updateCategory() {
        categorydd = (Spinner) findViewById(R.id.category_spinner);
        DBHandler db = new DBHandler(getApplicationContext());
        try {
            categorylist = db.getAllCategories();
            Collection<Category> list = categorylist.values();
            ArrayAdapter<Category> dataadapter = new ArrayAdapter<Category>(this, android.R.layout.simple_spinner_item);
            dataadapter.addAll(list);
            dataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categorydd.setAdapter(dataadapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
