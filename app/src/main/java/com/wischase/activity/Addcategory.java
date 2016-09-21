package com.wischase.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.wischase.Category;
import com.wischase.R;
import com.wischase.db.DBHandler;
import com.wischase.view.menu.ScrollingActivity;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class Addcategory extends Activity {
Spinner categoryspinner;
    Map<String,Category>categorylist;
    EditText category,subcategory;
    int gradeinput,categoryinput;
    Category userinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcategory);
        Intent inputintent = getIntent();
        gradeinput = inputintent.getIntExtra(ActivityConstants.GRADE_INPUT, 0);
        userinput =(Category)(inputintent.getParcelableExtra(ActivityConstants.USER_INPUT));
      //  categoryinput =userinput.getSubCategory().get(0).getCategoryId();
        categoryspinner = (Spinner) findViewById(R.id.spinner);
        DBHandler db = new DBHandler(getApplicationContext());
        try {
            categorylist = db.getAllCategories();
            Collection<String> list = categorylist.keySet();
            ArrayAdapter<String> dataadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
            dataadapter.addAll(list);
            dataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categoryspinner.setAdapter(dataadapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        categoryspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category=(EditText)findViewById(R.id.editText);
                category.setText(categoryspinner.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
        public void nextscreen (View v){
            Intent nextintent = new Intent(getBaseContext(),Selectionscreen.class);
            startActivity(nextintent);
    }
        public void submit(View v) {
            category=(EditText)findViewById(R.id.editText);
            String categoryname =category.getText().toString();
            subcategory=(EditText)findViewById(R.id.editText2);
            String subcategoryname =subcategory.getText().toString();
            if((categoryname.equals(" "))&&(!(subcategoryname.equals(" "))))
                categoryname=categoryspinner.getSelectedItem().toString();

            DBHandler dbhandle =new DBHandler(this);
           long newlyadded =dbhandle.insertUserAddedCatInfo(categoryname,subcategoryname);
            Toast.makeText(getBaseContext(),categoryname+" added",Toast.LENGTH_SHORT).show();
            //setContentView(R.layout.activity_addcategory);
        }
   }
