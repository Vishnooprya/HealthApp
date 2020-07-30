package com.casetools.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ViewDetails extends AppCompatActivity {

    DatabaseHelper db;
    String text,t,x;
    TextView e,f,g;
    //DatabaseAccess d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        db=new DatabaseHelper(this);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        Bundle bundle = getIntent().getExtras();
        text= bundle.getString("username");
        t=bundle.getString("age");
        x=bundle.getString("gender");
        int s=Integer.parseInt(t);
        String ans=null;


       // Toast.makeText(getApplicationContext(),t,Toast.LENGTH_LONG).show();
        e=findViewById(R.id.bmi);
        float bmi=db.heightweight(text);
        e.setText(Float.toString(bmi));
        f=findViewById(R.id.tv);
       // g=findViewById(R.id.calorie);
        if(bmi<19)
            f.setText("Underweight");
        if(bmi>19 && bmi<=25)
            f.setText("Normal");
        if(bmi>25)
            f.setText("Overweight");
        if(x.equalsIgnoreCase("male")){
        ans=databaseAccess.getMCalorie(s);}
        if(x.equalsIgnoreCase("female")) {
             ans = databaseAccess.getFCalorie(s);
        }


        g=findViewById(R.id.calorie);
        g.setText(ans);




    }
    public void showrecommendations(View v)
    {
        Bundle b=new Bundle();
        b.putString("status",f.getText().toString());
        Intent i=new Intent(getApplicationContext(),Recommendationpage.class);
        i.putExtras(b);
        startActivity(i);
    }
    public void bmipage(View v)
    {
        Bundle b=new Bundle();
        b.putString("username",text);
        Intent i=new Intent(getApplicationContext(),BMIpage.class);
        i.putExtras(b);
        startActivity(i);

    }
    public void updatedetail(View v)
    {
        Intent i=new Intent(getApplicationContext(),updatepage.class);
        Bundle b=new Bundle();
        b.putString("username",text);
        i.putExtras(b);
        startActivity(i);

    }

}
