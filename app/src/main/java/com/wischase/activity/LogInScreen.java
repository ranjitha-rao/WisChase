package com.wischase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wischase.R;
import com.wischase.UserInfo;
import com.wischase.db.DBHandler;
import com.wischase.view.menu.ScrollingActivity;

public class LogInScreen extends ScrollingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);
    }

    // On clicking Sign In this method is called
    public void signIn(View v) {

        //Retrieve the user name from the User
        EditText ed1 = (EditText)findViewById(R.id.userNameEntry);
        String UsrName = ed1.getText().toString();

        //Insert or retrieve the user details from DB
        DBHandler DB1 = new DBHandler(getApplicationContext());
        long UserId = DB1.insertUserInfo(UsrName);

        Toast.makeText(getBaseContext(),"Value is " +UserId, Toast.LENGTH_LONG).show();
        Toast.makeText(getBaseContext(),UsrName, Toast.LENGTH_LONG).show();
        // Passing the User Info to the next Screen
        setContentView(R.layout.activity_selectionscreen);
        UserInfo Uinfo = new UserInfo();
        Uinfo.getuserInfo(UserId, UsrName);
        Intent i = new Intent(getBaseContext(),Selectionscreen.class);
        i.putExtra("UserId",Uinfo);
        i.putExtra("UsrName",Uinfo);


        //Display the Screen
        startActivity(i);
    }

}
