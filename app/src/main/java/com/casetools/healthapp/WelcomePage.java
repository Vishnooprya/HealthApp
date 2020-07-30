package com.casetools.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

    }
    public void Login(View view)
    {
        Intent i=new Intent(getApplicationContext(),Loginpage.class);
        startActivity(i);
    }
    public void signup(View view)
    {
        Intent i=new Intent(getApplicationContext(),Registerpage.class);
        startActivity(i);
    }
}
