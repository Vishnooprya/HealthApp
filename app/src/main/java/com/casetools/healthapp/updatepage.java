package com.casetools.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updatepage extends AppCompatActivity {

    EditText t1,t2;
    Button b1,b2;
    String text;
    DatabaseHelper db;
    String age,g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatepage);
        db=new DatabaseHelper(this);
        Bundle bundle = getIntent().getExtras();
        text= bundle.getString("username");
        t1 = findViewById(R.id.height);
        age=db.getage(text);
        g=db.getgender(text);
        t2 = findViewById(R.id.weight);
        b1 = findViewById(R.id.ok1);
        b2 = findViewById(R.id.back1);
    }

    public void okpress(View v) {
        String s1 = t1.getText().toString();
        String s2 = t2.getText().toString();
        db.updatedetail(text, s1, s2);
        Toast.makeText(getApplicationContext(), "Details updated", Toast.LENGTH_LONG).show();
    }
    public void back1(View v)
    {
        Intent i=new Intent(getApplicationContext(),ViewDetails.class);
        Bundle b=new Bundle();
        b.putString("username",text);
        b.putString("age",age);
        b.putString("gender",g);
        i.putExtras(b);
        startActivity(i);



    }
}
