package com.casetools.healthapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

public class MilkActivity extends AppCompatActivity {
    int[] images=new int[]{R.drawable.butter,R.drawable.buttermilk,R.drawable.cheese,R.drawable.curd,R.drawable.ghee,R.drawable.milk,R.drawable.milkshake,R.drawable.paneer};
    ListView l;String s1,text;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milk);
        db=new DatabaseHelper(this);
        Bundle bundle = getIntent().getExtras();
        text= bundle.getString("username");
        l=findViewById(R.id.listview);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        final List<String> quotes = databaseAccess.getnamemilk();
        final String[] names=toStringArray(quotes);
        //databaseAccess.close();

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quotes);
        // this.listView.setAdapter(adapter);
        final List<String> vals=databaseAccess.getvaluemilk();
        String[] values=toStringArray(vals);
        List<HashMap<String,String>> mylist = new ArrayList<HashMap<String,String>>();
        for(int i=0;i<vals.size();i++)
        {
            HashMap<String,String> h=new HashMap<>();
            h.put("txt","Name:"+names[i]);
            h.put("cur","Calories:"+values[i]);
            h.put("flag",Integer.toString(images[i]));
            mylist.add(h);
        }
        String[] from=new String[]{"txt","cur","flag"};
        int to[]=new int[]{R.id.txt,R.id.cur,R.id.flag};
        SimpleAdapter s= new SimpleAdapter(getBaseContext(),mylist,R.layout.milk_list,from,to);
        //ListView l=findViewById(R.id.listview);
        l.setAdapter(s);
        //setContentView(l);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s=vals.get(position)+"";
                Toast.makeText(MilkActivity.this, vals.get(position) + "", Toast.LENGTH_SHORT).show();
                showbox(s);

                // s=databaseAccess.getvaluesnacks(vals.get(position)+"");
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();





            }
        });


    }
    public void showbox(final String s)
    {
        // setContentView(R.layout.dialog);
        //  AlertDialog.Builder builder=new AlertDialog.Builder();
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Enter quantity consumed in ml:");
        final EditText input=new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                s1=input.getText().toString();
                Toast.makeText(getApplicationContext(),s1,Toast.LENGTH_LONG).show();
                int f=(Integer.parseInt(s1)*Integer.parseInt(s))/100;
                Toast.makeText(getApplicationContext(),Integer.toString(f),Toast.LENGTH_LONG).show();
                db.updatecons(text,f);
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
