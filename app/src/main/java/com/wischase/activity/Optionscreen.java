package com.wischase.activity;

import android.app.Activity;
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

import com.wischase.Category;
import com.wischase.R;
import com.wischase.SubCategory;
import com.wischase.db.DBHandler;
import com.wischase.view.menu.ScrollingActivity;

public class Optionscreen extends Activity {
    int gradeinput,categoryinput;
    Category userinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optionscreen);
       //
       // Intent inputintent = getIntent();
        //gradeinput = inputintent.getLongExtra(ActivityConstants.GRADE_INPUT, 0);
        //userinput =(Category)(inputintent.getParcelableExtra(ActivityConstants.USER_INPUT));
        //categoryinput =userinput.getSubCategory().get(0).getCategoryId();
         }

    public void uploadfile(View v) {
        Intent uploadfile = new Intent(this, UploadAFile.class);
        uploadfile.putExtras(getIntent().getExtras());
       // uploadfile.putExtra(ActivityConstants.USER_INPUT,categoryinput);
        startActivity(uploadfile);
    }

    public void uploadques(View v) {
        Intent upquestion = new Intent(this, QuestionUpload.class);
        //upquestion.putExtra(ActivityConstants.GRADE_INPUT,gradeinput);
        //upquestion.putExtra(ActivityConstants.USER_INPUT,categoryinput);
        upquestion.putExtras(getIntent().getExtras());
        startActivity(upquestion);
    }

    public void taketest(View v) {
        Intent taketest = new Intent(this, TakeAQuiz.class);
        taketest.putExtras(getIntent().getExtras());
        //taketest.putExtra(ActivityConstants.USER_INPUT,categoryinput);
        startActivity(taketest);
    }
}

