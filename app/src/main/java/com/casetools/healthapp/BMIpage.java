
package com.casetools.healthapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class BMIpage extends AppCompatActivity {
    String text,x,t;
    int i;
    //int req;
    //DatabaseHelper db;
    DatabaseHelper d;
    DatabaseAccess databaseAccess;

    //DatabaseAccess db;

    String age,g,c,req;
    TextView tv,per,end;int ages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmipage);
        databaseAccess = DatabaseAccess.getInstance(this);
        d=new DatabaseHelper(this);
        databaseAccess.open();
       Bundle bundle = getIntent().getExtras();
      text= bundle.getString("username");
        age=d.getage(text);
        g=d.getgender(text);
        c=d.getcons(text);
        Toast.makeText(getApplicationContext(),c,Toast.LENGTH_LONG).show();
        tv=findViewById(R.id.consume);
        end=findViewById(R.id.end);
        tv.setText(c);
        per=findViewById(R.id.percent);
        try{
             i=Integer.parseInt(c);

        }
        catch(Exception e)
        {

        }
        try{
             ages=Integer.parseInt(age);
        }
        catch(Exception e)
        {}
        if(g.equalsIgnoreCase("male"))
        {
            req=databaseAccess.getMCalorie(ages);
        }
        if(g.equalsIgnoreCase("female"))
        {
            req=databaseAccess.getFCalorie(ages);
        }
        float req1=Float.parseFloat(req);
        float percent=(i/req1)*100;
        per.setText(Float.toString(percent));




       // t=bundle.getString("age");
        //x=bundle.getString("gender");


    }
    public void onResume()
    {

        super.onResume();
        c=d.getcons(text);
        tv.setText(c);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
       // d=new DatabaseHelper(this);
        databaseAccess.open();
        if(g.equalsIgnoreCase("male"))
        {
            req=databaseAccess.getMCalorie(ages);
        }
        if(g.equalsIgnoreCase("female"))
        {
            req=databaseAccess.getFCalorie(ages);
        }

        float req1=Float.parseFloat(req);
        float percent=(i/req1)*100;
       // int ih=Integer.parseInt()
        //Math.round(percent);
        per.setText(Integer.toString(Math.round(percent)));


    }

    public void onRestart()
    {
        super.onRestart();
        c=d.getcons(text);
        //super.onResume();
        tv.setText(c);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        // d=new DatabaseHelper(this);
        databaseAccess.open();
        if(g.equalsIgnoreCase("male"))
        {
            req=databaseAccess.getMCalorie(ages);
        }
        if(g.equalsIgnoreCase("female"))
        {
            req=databaseAccess.getFCalorie(ages);
        }

        float req1=Float.parseFloat(req);
        float percent=(i/req1)*100;
        per.setText(Float.toString(percent));


    }

   /* public void onStart()
    {
        //super.onRestart();
        c=d.getcons(text);
        super.onStart();
        tv.setText(c);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        // d=new DatabaseHelper(this);
        databaseAccess.open();
        if(g.equalsIgnoreCase("male"))
        {
            req=databaseAccess.getMCalorie(ages);
        }
        if(g.equalsIgnoreCase("female"))
        {
            req=databaseAccess.getFCalorie(ages);
        }

        float req1=Float.parseFloat(req);
        float percent=(i/req1)*100;
        per.setText(Float.toString(percent));


    }*/
    public void showdetails(View v)
    {
        Intent i=new Intent(getApplicationContext(),ViewDetails.class);
        Bundle b=new Bundle();
        b.putString("username",text);
        b.putString("age",age);
        b.putString("gender",g);
        i.putExtras(b);
        startActivity(i);
    }
    public void entering(View v)
    {
        Intent i=new Intent(getApplicationContext(),Entercalorie.class);
        Bundle b=new Bundle();
        b.putString("username",text);
        i.putExtras(b);
        startActivity(i);

    }
    public void ordering(View v) {
        Intent i = new Intent(getApplicationContext(), HotelPage.class);
        Bundle b = new Bundle();
        b.putString("username", text);
        i.putExtras(b);
        startActivity(i);
    }
    public void end(View v)
    {
        int diff=Integer.parseInt(req)-Integer.parseInt(c);
        //Toast.makeText(getApplicationContext(),"Diff is"+diff,Toast.LENGTH_LONG).show();
        if(diff>0)
        {
            int h=diff/400;
            Toast.makeText(getApplicationContext(),"Diff is"+h,Toast.LENGTH_LONG).show();
            final List<String> quotes = databaseAccess.getcalorieincrease();
            final String[] names=toStringArray(quotes);
            final List<String> vals = databaseAccess.getcalorieincreasequantity();
            final String[] vals2=toStringArray(vals);
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            //builder.setTitle("Enter quantity consumed in ml:");
            //final EditText input=new EditText(this);
            //input.setInputType(InputType.TYPE_CLASS_TEXT);;
            //builder.setView(input);

            if(h>quotes.size()) {
                h = quotes.size();

            }
            String[] fin = new String[h];
            for(int i=0;i<h;i++)
            {
                fin[i]=new StringBuilder().append(names[i]).append(" - ").append(vals2[i]).toString();

            }
            //fin= Arrays.copyOf(names,h);
          //  fin.add()

            builder.setTitle("You have taken too low calories\n Try to eat the following: ");
            builder.setItems(fin, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0: // horse
                                case 1: // cow
                                case 2: // camel
                                case 3: // sheep
                                case 4: // goat
                            }
                        }
                    });
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   // Toast.makeText(getApplicationContext(),s1,Toast.LENGTH_LONG).show();

                }
            });
            builder.show();




        }
        if(diff<0)
        {
            diff=diff*-1;
            int h=diff/400;
            Toast.makeText(getApplicationContext(),"Diff is"+h,Toast.LENGTH_LONG).show();
            final List<String> quotes = databaseAccess.getcaloriedecrease();
            final String[] names=toStringArray(quotes);
            final List<String> vals = databaseAccess.getcaloriedecreasequantity();
            final String[] vals2=toStringArray(vals);
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            //builder.setTitle("Enter quantity consumed in ml:");
            //final EditText input=new EditText(this);
            //input.setInputType(InputType.TYPE_CLASS_TEXT);;
            //builder.setView(input);

            if(h>quotes.size())
                h=quotes.size();
            String[] fin=new String[h];
            for(int i=0;i<h;i++)
            {
                fin[i]=new StringBuilder().append(names[i]).append(" - ").append(vals2[i]).append("minutes").toString();

            }
            //fin= Arrays.copyOf(names,h);
            //  fin.add()

            builder.setTitle("You have taken too much calories\n Try to do these exercises: ");
            builder.setItems(fin, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0: // horse
                        case 1: // cow
                        case 2: // camel
                        case 3: // sheep
                        case 4: // goat
                    }
                }
            });
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Toast.makeText(getApplicationContext(),s1,Toast.LENGTH_LONG).show();

                }
            });
            builder.show();





        }

        d.erasecalorie(text);
        c=d.getcons(text);
        tv.setText(c);
        per.setText("0");


    }
    public String[] toStringArray(List<String> l)
    {
        String[] s=new String[l.size()];
        Object[] o=l.toArray();
        for(int i=0;i<l.size();i++)
        {
            s[i]=(String)o[i];

        }
        return s;
    }
}
