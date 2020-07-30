package com.casetools.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Loginpage extends AppCompatActivity {
    Button b;
    EditText uname,pw;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db=new DatabaseHelper(this);
        setContentView(R.layout.activity_loginpage);
        uname=findViewById(R.id.uname);
        pw=findViewById(R.id.pw);



    }
    public void verify(View view){
        String a=uname.getText().toString();
        String b=pw.getText().toString();
        //Toast.makeText(Loginpage.this,"Ok",Toast.LENGTH_LONG).show();

        if(db.Checkuser(a,b)==true)
        {
            Intent i=new Intent(getApplicationContext(),BMIpage.class);
            Bundle b1=new Bundle();
            b1.putString("username",a);
            String s=db.getage(a);
            String t=db.getgender(a);
           //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            b1.putString("age",s);
            b1.putString("gender",t);
            i.putExtras(b1);
            startActivity(i);
           // Toast.makeText(Loginpage.this,"Account exists",Toast.LENGTH_LONG).show();

        }
        else
        {
            Toast.makeText(Loginpage.this,"Username or password incorrect",Toast.LENGTH_LONG).show();

        }
    }


}


