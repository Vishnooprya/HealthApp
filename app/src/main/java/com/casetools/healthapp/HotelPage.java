package com.casetools.healthapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HotelPage extends AppCompatActivity {
    int[] images=new int[]{R.drawable.hotel1,R.drawable.hotel2,R.drawable.hotel3,R.drawable.hotel4,R.drawable.hotel5,R.drawable.hotel6};
    ListView l;String s1,text;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_page);
        Bundle bundle = getIntent().getExtras();
        text= bundle.getString("username");
        l=findViewById(R.id.listview);
        db=new DatabaseHelper(this);
        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        final List<String> quotes = databaseAccess.getnamehotel();
        final String[] names=toStringArray(quotes);
        //databaseAccess.close();

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quotes);
        // this.listView.setAdapter(adapter);
        final List<String> vals=databaseAccess.getplacehotel();
        String[] values=toStringArray(vals);
        List<HashMap<String,String>> mylist = new ArrayList<HashMap<String,String>>();
        for(int i=0;i<vals.size();i++)
        {
            HashMap<String,String> h=new HashMap<>();
            h.put("txt","Name:"+names[i]);
            h.put("cur","Place:"+values[i]);
            h.put("flag",Integer.toString(images[i]));
            mylist.add(h);
        }
        String[] from=new String[]{"txt","cur","flag"};
        int to[]=new int[]{R.id.txt,R.id.cur,R.id.flag};
        SimpleAdapter s= new SimpleAdapter(getBaseContext(),mylist,R.layout.hotel_list,from,to);
        //ListView l=findViewById(R.id.listview);
        l.setAdapter(s);
        //setContentView(l);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String s=vals.get(position)+"";
                Toast.makeText(HotelPage.this, "Choose food to order:" , Toast.LENGTH_SHORT).show();
                //showbox(s);
                Intent i=new Intent(getApplicationContext(),FoodActivity.class);
                Bundle b=new Bundle();
                b.putString("username",text);
                i.putExtras(b);
                startActivity(i);
                //   s=databaseAccess.getvaluesnacks(quotes.get(position)+"");
              //  Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();





            }
        });


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

