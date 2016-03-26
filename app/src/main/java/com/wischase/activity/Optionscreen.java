package com.wischase.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.wischase.R;
import com.wischase.SubCategory;
import com.wischase.view.menu.ScrollingActivity;

public class Optionscreen extends ScrollingActivity {
    ImageButton imgbutupfile;//imagebutton for uploading a file
    ImageButton imgbutupq;//imagebutton for uploading a single question
    ImageButton imgbuttest;//imagebutton for taking a test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optionscreen);
        /* function for onclick event of the imagebutton (uploading a question); onclick of the button leads to
        *  the next selection screen and also displays the message of what is clicked*/
        imgbutupfile = (ImageButton) findViewById(R.id.imgbutupq);
        imgbutupfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Upload a Question", Toast.LENGTH_LONG).show();
                Intent uploadfile = new Intent(getBaseContext(), QuestionUpload.class);
                SubCategory subcategory = new SubCategory();
                subcategory.getCategoryId();
                uploadfile.putExtra(ActivityConstants.GRADE_INPUT,subcategory);
                startActivity(uploadfile);
            }
        });
        /* function for onclick event of the imagebutton (uploading a file); onclick of the button leads to
        *  the next selection screen and also displays the message of what is clicked*/
        imgbutupq = (ImageButton) findViewById(R.id.imgbutupfile);
        imgbutupq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Upload a File/ A set of Questions", Toast.LENGTH_LONG).show();
                Intent upqus =new Intent(getBaseContext(),QuestionUpload.class);
                SubCategory subcategory = new SubCategory();
                subcategory.getCategoryId();
                upqus.putExtra(ActivityConstants.GRADE_INPUT,subcategory);
                startActivity(upqus);
            }
        });
        /* function for onclick event of the imagebutton (taking the test); onclick of the button leads to
        *  the next selection screen and also displays the message of what is clicked*/
        imgbuttest = (ImageButton) findViewById(R.id.imgbuttest);
        imgbuttest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Take A Test", Toast.LENGTH_LONG).show();
                Intent  taketest = new Intent(getBaseContext(),TakeAQuiz.class);
                SubCategory subcategory = new SubCategory();
                subcategory.getCategoryId();
                taketest.putExtra(ActivityConstants.GRADE_INPUT,subcategory);
                startActivity(taketest);
            }
        });
    }
}


    /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
*/