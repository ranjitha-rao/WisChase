package com.wischase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.wischase.Category;
import com.wischase.R;
import com.wischase.SubCategory;
import com.wischase.db.DBHandler;
import com.wischase.view.menu.ScrollingActivity;

import java.io.IOException;
import java.text.CollationKey;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.lang.String.*;

public class Selectionscreen extends ScrollingActivity implements AdapterView.OnItemSelectedListener {
    Spinner gradearray, categorydd, subcategorydd; //variable to define the Grade drop down, Category and Sub Category dropdown.
    Map<String, Category> categorylist;
    List<SubCategory> subcategorylist;
    Button nextbutton, addcategorybutton;
    SubCategory sublist;
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
                    System.out.print("Select a Grade Level");
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
                Intent categoryIntent = new Intent(getBaseContext(), Addcategory.class);
                SubCategory subcategory = new SubCategory();
                subcategory.getCategoryId();
                categoryIntent.putExtra(ActivityConstants.GRADE_INPUT, subcategory);
                startActivity(categoryIntent);
            }
        });
        nextbutton = (Button) findViewById(R.id.Next);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(getBaseContext(), Optionscreen.class);
                SubCategory subcategory = new SubCategory();
                subcategory.getCategoryId();
                nextIntent.putExtra(ActivityConstants.GRADE_INPUT,subcategory);
                startActivity(nextIntent);
            }
        });
    }
    void updateCategory() {
        categorydd = (Spinner) findViewById(R.id.category_spinner);
        DBHandler db = new DBHandler(getApplicationContext());
        try {
            categorylist = db.getAllCategories();
            Collection<String> list = categorylist.keySet();
            ArrayAdapter<String> dataadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
            dataadapter.addAll(list);
            dataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categorydd.setAdapter(dataadapter);
           } catch (IOException e) {
            e.printStackTrace();
        }
    categorydd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View selectedItemview, int position, long id) {
            String categoryNameSelected = categorydd.getSelectedItem().toString();
            Toast.makeText(getBaseContext(), categoryNameSelected + " Selected ", Toast.LENGTH_SHORT).show();
            subcategorydd = (Spinner) findViewById(R.id.subcategory_spinner);
                subcategorylist = categorylist.get(categoryNameSelected).getSubCategory();
            updateSubcategory();
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    });
    }
   private void updateSubcategory() {
       ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        adapter.addAll(String.valueOf(subcategorylist));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subcategorydd.setAdapter(adapter);
                subcategorydd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),parent.getItemAtPosition(position) +"Selected ",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
