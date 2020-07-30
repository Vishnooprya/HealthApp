package com.casetools.healthapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database,db;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context)
        {
            this.openHelper = new DatabaseOpenHelper(context);
        }



    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public String getMCalorie(int age) {
        String s = "2500";
        //age="23";
        // int a=Integer.parseInt(age);
        int ag=age-(age%5)+1;
        System.out.println(ag);
        String ans=Integer.toString(ag);
        Cursor cursor = database.rawQuery("SELECT sedentary FROM malereq where age=?", new String[]{ans});
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                s = cursor.getString(0);
                // cursor.moveToNext();
                cursor.close();
            }}

          return s;

        }
    public String getFCalorie(int age) {
        String s = "2500";
        //age="23";
        // int a=Integer.parseInt(age);
        int ag=0;
        if(age<=20)
            ag=age;
        if(age>=21){
         ag=age-(age%5)+1;
        System.out.println(ag);}
        String ans=Integer.toString(ag);
        Cursor cursor = database.rawQuery("SELECT sedentary FROM femalereq where age=?", new String[]{ans});
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                s = cursor.getString(0);
                // cursor.moveToNext();
                cursor.close();
            }}

        return s;

    }


    public List<String> getQuotes() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT name FROM breakfast", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<String> getvalues()
    {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT calorie FROM breakfast order by name", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;

    }
    public List<String> getnamesnacks()
    {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT name FROM snacks", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<String> getnamehotel()
    {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT name FROM hotel", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<String> getplacehotel()
    {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT place FROM hotel", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<String> getvaluesnacks()
    {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT calorie FROM snacks order by name", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<String> getnamefood()
    {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT name FROM healthyfood order by name", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<String> getvaluefood()
    {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT calories FROM healthyfood order by name", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<String> getnamemeals()
    {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT name FROM meals", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<String> getvaluemeals()
    {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT calorie FROM meals order by name", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<String> getnamegravies()
    {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT name FROM gravies", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<String> getvaluegravies()
    {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT calorie FROM gravies order by name", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public String getvaluesnacks(String s)
    {
        String ans=null;
        Cursor c=database.rawQuery("select calorie from snacks where name=?",new String[]{s});
        if(c!=null && c.getCount()>0)
        {
            if(c.moveToFirst())
            {
                ans=c.getString(0);
            }
        }
        return ans;


    }
    public List<String> getnamemilk()
    {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT name FROM milk", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<String> getvaluemilk()
    {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT calories FROM milk order by name", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<String> getnamefruits()
    {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT name FROM fruit", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<String> getvaluefruits()
    {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT calorie FROM fruit order by name", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<String> getnamejuice()
    {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT name FROM juice", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<String> getvaluejuice()
    {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT calorie FROM juice order by name", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<String> getcalorieincrease()
    {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT name FROM calorieincrease order by name", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<String> getcalorieincreasequantity()
    {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT quantity FROM calorieincrease order by name", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<String> getcaloriedecrease()
    {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT name FROM caloriedecrease order by name", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<String> getcaloriedecreasequantity()
    {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT duration FROM caloriedecrease order by name", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    }
