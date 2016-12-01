package com.wischase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wischase.R;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

    }

    //Called when forgot password is clicked
    public void sEmail(View v) {

        //Retrieve the e-mail from the User
        EditText ed1 = (EditText) findViewById(R.id.eMail_entry);
        String Email = ed1.getText().toString();

        //Check if field is empty
        if (TextUtils.isEmpty(Email)) {

            Toast.makeText(getBaseContext(), "Enter a valid e-mail address", Toast.LENGTH_SHORT).show();
            return;
        } else {

           //Need to use webservice
            // Go back to Log In Screen
            setContentView(R.layout.activity_log_in_screen);
            Intent i2 = new Intent(getBaseContext(), LogInScreen.class);

            //Display the Screen
            startActivity(i2);
        }


    }

}

