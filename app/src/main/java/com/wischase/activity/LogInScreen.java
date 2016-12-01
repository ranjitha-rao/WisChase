package com.wischase.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wischase.R;
import com.wischase.UserInfo;
import com.wischase.db.DBHandler;
import com.wischase.utils.SharedPref;
import com.wischase.view.menu.ScrollingActivity;

import java.lang.reflect.Type;

public class LogInScreen extends ScrollingActivity {

    //Shared Preference instance
    SharedPref sharedpreferences;
    String Type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);
    }

    // On clicking Sign In this method is called
    public void signIn(View v) {

        //If Forgot password is clicked
        if (v.getId() == R.id.link) {

            sendEmail(v);
            //If Sign In button is clicked
        } else if (v.getId() == R.id.signUp)

        {
            verify(v);
        }

    }


    public void verify(View v) {

        //Retrieve the type from the User
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.logInType);
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();

        // No Log In type selected
        if (checkedRadioButtonId == -1) {
            Toast.makeText(getBaseContext(), "Select your typeof LogIn", Toast.LENGTH_SHORT).show();
            return;
        } else {

            //Retrieve the user name from the User
            EditText ed1 = (EditText) findViewById(R.id.userNameEntry);
            String UsrName = ed1.getText().toString();

            //Check if User Name is Null
            if (TextUtils.isEmpty(UsrName)) {
                Toast.makeText(getBaseContext(), "Enter a UserName", Toast.LENGTH_SHORT).show();
                return;
            } else

            {
                //Retrieve the password from the User
                EditText ed2 = (EditText) findViewById(R.id.passwordEntry);
                String Password = ed2.getText().toString();

                //Check if password is Null
                if (TextUtils.isEmpty(Password)) {
                    Toast.makeText(getBaseContext(), "Password field cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                } else

                {
                    //Check if type is Teacher
                    if (checkedRadioButtonId == R.id.teacher) {
                        Type = ActivityConstants.TEACH_TYPE;
                    }
                    //Check if type is Student
                    else if (checkedRadioButtonId == R.id.student) {
                        Type = ActivityConstants.STUD_TYPE;
                    }

                    //Insert or retrieve the user details from DB
                    DBHandler DB1 = new DBHandler(getApplicationContext());
                    long UserId = DB1.insertUserInfo(UsrName, Password, Type);

                    //Toast for testing purpose. Should be removed after testing
                    Toast.makeText(getBaseContext(), "Value is " + UserId, Toast.LENGTH_LONG).show();
                    Toast.makeText(getBaseContext(), UsrName, Toast.LENGTH_LONG).show();

                    //Put the values in the shared preference
                    sharedpreferences = new SharedPref();
                    sharedpreferences.save(getApplicationContext(), UserId, UsrName);


                    // Passing the User Info to the next Screen
                    setContentView(R.layout.activity_selectionscreen);
                    Intent i = new Intent(getBaseContext(), Selectionscreen.class);

                    //Display the Screen
                    startActivity(i);
                }
            }
        }
    }




    //Called when forgot password is clicked
    public void sendEmail(View v) {

        //Retrieve the type from the User
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.logInType);
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();

        // No Log In type selected
        if (checkedRadioButtonId == -1) {
            Toast.makeText(getBaseContext(), "Select your typeof LogIn", Toast.LENGTH_SHORT).show();
            return;

            // Log In Type Student
        } else if (checkedRadioButtonId == R.id.student) {

            Toast.makeText(getBaseContext(), "Check with your teacher for password reset", Toast.LENGTH_SHORT).show();
            return;

            //Only teacher can reset password
        } else {

            //Retrieve the user name from the User
            EditText ed1 = (EditText) findViewById(R.id.userNameEntry);
            String UsrName = ed1.getText().toString();

            //Check if User Name is Null
            if (TextUtils.isEmpty(UsrName)) {
                Toast.makeText(getBaseContext(), "Enter a UserName", Toast.LENGTH_SHORT).show();
                return;
            } else {

                // Display forgot password Screen
                //setContentView(R.layout.activity_forgot_password); - donno why this needs to be commented as code works only when commented
                Intent i2 = new Intent(getBaseContext(), ForgotPassword.class);

                //Display the Screen
                startActivity(i2);
            }

        }
    }



}

