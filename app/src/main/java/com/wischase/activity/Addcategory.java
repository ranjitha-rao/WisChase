package com.wischase.activity;

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

public class Addcategory extends ScrollingActivity implements AdapterView.OnItemSelectedListener{
Spinner categoryspinner;
    Map<String,Category>categorylist;
Button nextbutton,submitbutton,backbutton;
EditText category,subcategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcategory);
        categoryspinner=(Spinner)findViewById(R.id.spinner);
        DBHandler db = new DBHandler(getApplicationContext());
        try {
            categorylist = db.getAllCategories();
            Collection<String> list = categorylist.keySet();
            ArrayAdapter<String> dataadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
            dataadapter.addAll(list);
            dataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categoryspinner.setAdapter(dataadapter);
        }catch (IOException e) {
            e.printStackTrace();
        }
        categoryspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " Selected ", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        nextbutton=(Button)findViewById(R.id.nextbutton);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next= new Intent(getBaseContext(),Optionscreen.class);
                startActivity(next);
            }
        });
        backbutton=(Button)findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back= new Intent(getBaseContext(),Selectionscreen.class);
                startActivity(back);
            }
        });
        submitbutton=(Button)findViewById(R.id.submitbutton);
        category=(EditText)findViewById(R.id.editText);
        String catname =getBaseContext().toString();
        subcategory=(EditText)findViewById(R.id.editText2);
        String subcname =getBaseContext().toString();
        DBHandler dbhandle =new DBHandler(getApplicationContext());
        long submit =dbhandle.insertUserAddedCatInfo(catname,subcname);
}
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
