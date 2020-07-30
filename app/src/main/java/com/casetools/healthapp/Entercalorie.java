package com.casetools.healthapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Entercalorie extends AppCompatActivity {
    Button b;
    DatabaseHelper db;
    String x,s,t,s1,text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entercalorie);
        b=findViewById(R.id.calorie);
        db=new DatabaseHelper(this);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        Bundle bundle = getIntent().getExtras();
        text= bundle.getString("username");
       // t=bundle.getString("age");
        //x=bundle.getString("gender");
    }
    public void addcalorie(View v)
    {
        /*boolean a=db.updatecons(text,200);
        if(a==true){

        Intent i=new Intent(getApplicationContext(),BMIpage.class);
        Bundle b=new Bundle();b.putString("username",text);

        i.putExtras(b);
        startActivity(i);}
        if(a==false)
            Toast.makeText(getApplicationContext(),"Error in updating",Toast.LENGTH_LONG).show();*/
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Enter number of calories consumed:");
        final EditText input=new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);;
        builder.setView(input);
        builder.setPositiveButton("OK:" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                s1=input.getText().toString();
                Toast.makeText(getApplicationContext(),s1,Toast.LENGTH_LONG).show();
        //        int f=(Integer.parseInt(s1)*Integer.parseInt(s));
           //     Toast.makeText(getApplicationContext(),Integer.toString(f),Toast.LENGTH_LONG).show();
                db.updatecons(text,Integer.parseInt(s1));


            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),s1,Toast.LENGTH_LONG).show();

            }
        });
        builder.show();


    }
    public void breakfastpage(View v) {
        Intent i = new Intent(getApplicationContext(), BreakfastActivity.class);
        Bundle b=new Bundle();
        b.putString("username",text);;
        i.putExtras(b);

        startActivity(i);
    }
    public void mealpage(View v)
    {
        Intent i=new Intent(getApplicationContext(),MealsActivity.class);
        Bundle b=new Bundle();
        b.putString("username",text);;
        i.putExtras(b);
        startActivity(i);
    }
    public void snackspage(View v)
    {
        Intent i=new Intent(getApplicationContext(),SnacksActivity.class);
        Bundle b=new Bundle();
        b.putString("username",text);;
        i.putExtras(b);
        startActivity(i);
    }
    public void gravypage(View v)
    {
        Intent i=new Intent(getApplicationContext(),GraviesActivity.class);
        Bundle b=new Bundle();
        b.putString("username",text);;
        i.putExtras(b);
        startActivity(i);

    }
    public void fruitpage(View v)
    {
        Intent i=new Intent(getApplicationContext(),FruitActivity.class);
        Bundle b=new Bundle();
        b.putString("username",text);;
        i.putExtras(b);
        startActivity(i);

    }
    public void juicepage(View v)
    {
        Intent i=new Intent(getApplicationContext(),JuiceActivity.class);
        Bundle b=new Bundle();
        b.putString("username",text);;
        i.putExtras(b);
        startActivity(i);

    }
    public void milkpage(View v)
    {
        Intent i=new Intent(getApplicationContext(),MilkActivity.class);
        Bundle b=new Bundle();
        b.putString("username",text);;
        i.putExtras(b);
        startActivity(i);

    }
    public void back(View v)
    {
        Intent i=new Intent(getApplicationContext(),BMIpage.class);
       // text= bundle.getString("username");
        // t=bundle.getString("age");
        //x=bundle.getString("gender");
        Bundle b=new Bundle();
        b.putString("username",text);
        i.putExtras(b);
        startActivity(i);
    }

}
