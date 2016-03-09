package com.wischase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wischase.R;
import com.wischase.db.DBHandler;
import com.wischase.view.menu.ScrollingActivity;

public class LogInScreen extends ScrollingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);
    }

            public void signUp(View v) {
                EditText ed1 = (EditText)findViewById(R.id.userNameEntry);
                String UsrName = ed1.getText().toString();
                DBHandler DB1 = new DBHandler(getApplicationContext());
                long UserId = DB1.insertUserInfo(UsrName);
                long x = -1;
                if (UserId != x) {
                    //Toast.makeText(getApplicationContext(), "UserId:" +UserId, Toast.LENGTH_SHORT).show();
                    setContentView(R.layout.activity_take_aquiz);
                    Intent i = new Intent(getBaseContext(),TakeAQuiz.class);
                    startActivity(i);
                }
                    else{
                   //Logic is yet to be decided

                }

            }
    public void signIn(View v) {
        //THis is incomplete
        EditText ed1 = (EditText)findViewById(R.id.userNameEntry);
        String UsrName = ed1.getText().toString();
        DBHandler DB1 = new DBHandler(getApplicationContext());
       // long UserId = DB1.getUserInfo(UsrName);
        }
    }


