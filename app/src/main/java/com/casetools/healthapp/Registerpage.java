package com.casetools.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registerpage extends AppCompatActivity {

    DatabaseHelper db;
    EditText name,uname,pw,email,cont,place,age,height,weight,gender;
    Button enter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpage);
        db=new DatabaseHelper(this);
        name=findViewById(R.id.namebox);
        uname=findViewById(R.id.unamebox);
        pw=findViewById(R.id.pwbox);
        email=findViewById(R.id.emailbox);
        cont=findViewById(R.id.contactbox);
        place=findViewById(R.id.placebox);
        age=findViewById(R.id.agebox);
        height=findViewById(R.id.heightbox);
        weight=findViewById(R.id.weightbox);
        gender=findViewById(R.id.genderbox);
        enter=findViewById(R.id.createbutton);


    }
    public void c1(View view)
    {
        int flag=0;int f=0;
        String pw1=pw.getText().toString();
        String c=cont.getText().toString();
        String g=gender.getText().toString();
        if(pw1.length()<6 && (c.length()<10 || c.length()>10))
        {
            pw.setText("");
            cont.setText("");
            Toast.makeText(getApplicationContext(),"Password must be 6 characters long and contact number must have 10 digits",Toast.LENGTH_LONG).show();
            flag=1;
            f=1;


        }
        if(pw1.length()<6 && f==0)
        {
            pw.setText("");
            Toast.makeText(getApplicationContext(),"Password must be 6 characters long",Toast.LENGTH_LONG).show();
            flag=1;
        }
        //String c=cont.getText().toString();
        if((c.length()<10 || c.length()>10) && f==0)
        {
            cont.setText("");
            Toast.makeText(getApplicationContext(),"Contact number must have 10 digits",Toast.LENGTH_LONG).show();
            flag =1;
        }
        if (!((g.equals("male")||g.equals("female"))))
        {
            gender.setText("");
            Toast.makeText(getApplicationContext(),"Enter valid gender male/female",Toast.LENGTH_LONG).show();
            flag =1;
        }

        if(flag==0){
        boolean s=db.insertData(name.getText().toString(),uname.getText().toString(),pw.getText().toString(),email.getText().toString(),cont.getText().toString(),place.getText().toString(),age.getText().toString(),height.getText().toString(),weight.getText().toString(),gender.getText().toString());
        if(s)
        {
            Toast.makeText(Registerpage.this,"Account created",Toast.LENGTH_LONG).show();
            Intent i=new Intent(getApplicationContext(),Loginpage.class);
           /* Bundle b=new Bundle();
            b.putString("username",name.getText().toString());
            i.putExtras(b);
                    */
            startActivity(i);
        }
        else
        {
            Toast.makeText(Registerpage.this,"Username already exists",Toast.LENGTH_LONG).show();
        }}
    }
}
