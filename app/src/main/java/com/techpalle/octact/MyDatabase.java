package com.techpalle.octact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pc on 11-10-2017.
 */

public class MyDatabase {

    private MyHelper mh;
    private SQLiteDatabase sdb;
    //initialize myhelper object in the constructor
    public MyDatabase(Context c){
        //p1 = context, p2 = database name, p3 = cursorfactory null, p4 = database version num
        mh = new MyHelper(c, "octact.db", null, 1);
    }
    // open database connection
    public void open(){
        sdb = mh.getWritableDatabase();
    }
    // insert method for user table
    public void registerUser(String firstName, String lasttName, String email, String password, Long mobile, String place){
        ContentValues cv = new ContentValues(); //prepare content values
        cv.put("fname",firstName);
        cv.put("lname",lasttName);
        cv.put("email",email);
        cv.put("password",password);
        cv.put("mobile",mobile);
        cv.put("place",place);
        sdb.insert("user", null, cv); //pass data to student table
    }
    // authenticating user
    public String doLogin(String email){
        //in query first parameter is table name
        Cursor c = sdb.query("user", null, " email=?", new String[]{email}, null, null, null);
        // Check user exist or not
        if(c.getCount()<1)
        {
            c.close();
            return "NOT EXIST";
        }
        c.moveToFirst();
        String password= c.getString(c.getColumnIndex("password"));
        c.close();
        return password;
    }
    //Fetch the user details
    public Cursor fetchUserDetails(String email){
        //in query first parameter is table name
        Cursor c = sdb.query("user", null, " email=?", new String[]{email}, null, null, null);
        return c;
    }
    public int updateUserProfile(String fname, String lname,String email, Long mobile, String place){
        ContentValues values=new ContentValues();
        values.put("fname",fname);
        values.put("lname",lname);
        values.put("mobile",mobile);
        values.put("place",place);

        return sdb.update("user",values,"email='"+email+"'",null);
    }

    public boolean isUserExist(String email){
        //in query first parameter is table name
        Cursor c = sdb.query("user", null, " email=?", new String[]{email}, null, null, null);
        if(c.getCount()<1)
        {
            return false;
        }else {
            return true;
        }
    }

    public void close(){
        sdb.close(); //removes the database connection [else memory will be wasted]
    }

    public class MyHelper extends SQLiteOpenHelper {
        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table user(_id integer Primary key,fname text,lname text,email text,password text,mobile long,place text);" );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
