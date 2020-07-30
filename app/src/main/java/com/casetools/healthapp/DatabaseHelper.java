package com.casetools.healthapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "HealthApp.db";
    public static final String TABLE_NAME = "Users";
    public static final String COL1 = "name";
    public static final String COL2 = "username";
    public static final String COL3 = "password";
    public static final String COL4 = "email";
    public static final String COL5 = "contact";
    public static final String COL6 = "place";
    public static final String COL7 = "age";
    public static final String COL8 = "height";
    public static final String COL9 = "weight";
    public static final String COL10 = "gender";
    public static final String TABLE_2 = "Dailycons";
    // public static final String COL_1="username";
    //public static final String COL_2="bmi";
    //public static final String COL_3="dailyrequirement";
    Log i;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase ds = getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("create table " + TABLE_NAME + " (NAME TEXT,USERNAME TEXT PRIMARY KEY,PASSWORD TEXT,EMAIL TEXT,CONTACT TEXT,PLACE TEXT,AGE TEXT,HEIGHT TEXT,WEIGHT TEXT,GENDER TEXT)");
            //db.execSQL("create table "+TABLE_2+"(username primary key,bmi text,dailyrequirement text)");
            db.execSQL("create TABLE DAILYCONS ( USERNAME TEXT PRIMARY KEY,CONS TEXT)");
        }
        catch(SQLiteException e)
        {
            Log.e("Got and error:",e.getMessage());
        }
    }

    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_2);
        onCreate(db);
    }

    public boolean insertData(String name, String username, String password, String email, String contact, String place, String age, String height, String weight, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL1, name);
        cv.put(COL2, username);
        cv.put(COL3, password);
        cv.put(COL4, email);
        cv.put(COL5, contact);
        cv.put(COL6, place);
        cv.put(COL7, age);
        cv.put(COL8, height);
        cv.put(COL9, weight);
        cv.put(COL10, gender);
        long l = db.insert(TABLE_NAME, null, cv);
        ContentValues cv1=new ContentValues();
        cv1.put("username",username);
        cv1.put("cons","0");
        long l1=db.insert(TABLE_2,null,cv1);

        if (l1== -1)
            return false;
        else
            return true;

        //db.execSQL("insert into dailycon values ("+username+",0)");
        //return true;

    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from person", null);
        return c;

    }
    public boolean updatecons(String user,int value)
    {
        String s="250";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.query("Dailycons",new String[]{"cons"},"username=?",new String[]{user},null,null,null);
        if(c!=null && c.getCount()>0)
        {
            if(c.moveToFirst())
                s=c.getString(0);
            c.close();
        }
        int a=Integer.parseInt(s);
        a=a+value;
        String con=Integer.toString(a);
        ContentValues vc=new ContentValues();
        vc.put("cons",a);


        int f=db.update("dailycons",vc,"username=?",new String[]{user});
        if(f==1)
            return true;
        return false;


    }
    public void updatedetail(String user,String a,String b)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues v=new ContentValues();
        v.put("height",a);
        v.put("weight",b);

        int fin=db.update("users",v,"username=?",new String[]{user});

    }
    public void erasecalorie(String user)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String c=Integer.toString(0);
        ContentValues v=new ContentValues();
        v.put("cons",c);
        int a=db.update("dailycons",v,"username=?",new String[]{user});

    }

    public boolean Checkuser(String use, String pass) {
        //  SQLiteDatabase sqldb = EGLifeStyleApplication.sqLiteDatabase;
        SQLiteDatabase db = this.getReadableDatabase();
        //String[] col={"username"};
        // db=openDatabase();
        //String Query = "Select * from " + TABLE_NAME + " where " + "username" + " = " + use + " and "+"password"+"="+pass;
        //   String
        Cursor c = db.query(TABLE_NAME, null, "username=? and password=?", new String[]{use, pass}, null, null, null);
        //  Cursor cursor = db.rawQuery(Query, null);
        if (c.getCount() <= 0) {
            c.close();
            return false;
        }
        c.close();
        return true;
    }
    public String getcons(String user)
    {
        SQLiteDatabase db=getReadableDatabase();
        String s="90";
        Cursor cursor = db.query(TABLE_2,new String[]{"cons"},"username=?",new String[]{user},null,null,null);
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                s = cursor.getString(0);
                // cursor.moveToNext();
                cursor.close();
            }}
        return s;

    }

    public float heightweight(String use) {
        String s, t;
        float a, b, bmi = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c1 = db.query(TABLE_NAME, new String[]{"height", "weight"}, "username=?", new String[]{use}, null, null, null);
        //Cursor c1=db.rawQuery("select height,weight from users where username=?",new String[]{use});
        if (c1 != null && c1.getCount() > 0) {
            if (c1.moveToFirst()) {
                // s = c1.getString(0);
                //   t=c.getString(c.getColumnIndex("weight"));
                //  int s1=Integer.parseInt(s);
                // int s2=Integer.parseInt(t);
                //bmi=s1*(s2*s2);}
                //  Toast.makeText(getWritableDatabase(),"ok",Toast.LENGTH_LONG).show();
                //s="100000";
                //i.i("Ok", "inside c1");
                s = c1.getString(0);
                t = c1.getString(1);

                a = Float.parseFloat(s);
                float h=a/100;
                b = Float.parseFloat(t);
                //bmi=b;
                bmi = b/(h*h);

                c1.close();
            }
        }

        return bmi;

    }
    public String getgender(String use)
    {
        String s="21";
        int a;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c1 = db.query(TABLE_NAME, new String[]{"gender"}, "username=?", new String[]{use}, null, null, null);
        //Cursor c1=db.rawQuery("select height,weight from users where username=?",new String[]{use});
        if (c1 != null && c1.getCount() > 0) {
            if (c1.moveToFirst()) {
                s = c1.getString(0);


            }
        }
        return s;


    }

    public String getage(String use) {
        String s="21";
        int a;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c1 = db.query(TABLE_NAME, new String[]{"age"}, "username=?", new String[]{use}, null, null, null);
        //Cursor c1=db.rawQuery("select height,weight from users where username=?",new String[]{use});
        if (c1 != null && c1.getCount() > 0) {
            if (c1.moveToFirst()) {
                s = c1.getString(0);


            }
        }
        return s;


    }




}
