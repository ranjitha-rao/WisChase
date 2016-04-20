package com.wischase.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Selectionscreen extends Activity {
    Spinner gradearray, categorydd, subcategorydd;
    Map<String, Category> categorylist;
    List<SubCategory> subcategorylist;
    String subcategorySelected,categorySelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectionscreen);
        gradearray = (Spinner) findViewById(R.id.Grade_Array);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.grade_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gradearray.setPrompt("Select a Grade");
        gradearray.setAdapter(adapter);
        updateCategory();
    }
    private void updateCategory() {
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
                categorySelected = categorydd.getSelectedItem().toString();
                subcategorydd = (Spinner) findViewById(R.id.subcategory_spinner);
                subcategorylist = categorylist.get(categorySelected).getSubCategory();
                updateSubcategory();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void updateSubcategory() {
        ArrayAdapter<SubCategory> adapter =new ArrayAdapter<SubCategory>(this,android.R.layout.simple_spinner_item);
        adapter.addAll(subcategorylist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subcategorydd.setAdapter(adapter);
        subcategorydd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subcategorySelected = subcategorydd.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void addcategory(View view){
        Intent categoryIntent = new Intent(getBaseContext(), Addcategory.class);
        categoryIntent.putExtra(ActivityConstants.GRADE_INPUT, (String) gradearray.getSelectedItem());
        SubCategory subcategoryinput = new SubCategory();
        subcategoryinput.setSubCategory(subcategorySelected);
        int categoryId =subcategoryinput.getCategoryId();
        categoryIntent.putExtra(ActivityConstants.USER_INPUT, categoryId);
        startActivity(categoryIntent);
    }
        public void next(View view){
            Intent nextIntent = new Intent(getBaseContext(), Optionscreen.class);
            nextIntent.putExtra(ActivityConstants.GRADE_INPUT, (String) gradearray.getSelectedItem());
            SubCategory subcategoryinput = new SubCategory();
            subcategoryinput.setSubCategory(subcategorySelected);
            int categoryId = subcategoryinput.getCategoryId();
            nextIntent.putExtra(ActivityConstants.USER_INPUT,categoryId);
            Toast.makeText(getBaseContext(),categoryId+"",Toast.LENGTH_SHORT).show();
            startActivity(nextIntent);
        }
}




