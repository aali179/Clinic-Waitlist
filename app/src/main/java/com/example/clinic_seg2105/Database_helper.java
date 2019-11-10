package com.example.clinic_seg2105;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database_helper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "People.db";
    public static final String TABLE_NAME = "people_table";
    public static final String COL1 = "NAME";
    public static final String COL2 = "USERNAME";
    public static final String COL3 = "PASSWORD";
    public static final String COL4 = "TYPE";


    public Database_helper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }
    @Override

    public void onCreate(SQLiteDatabase db){
        db.execSQL(" create Table " + TABLE_NAME + " (NAME INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT, PASSWORD TEXT, TYPE TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(" DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String Username, String Password, String Type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, name);
        contentValues.put(COL2, Username);
        contentValues.put(COL3, Password);
        contentValues.put(COL4, Type);
        long res = db.insert(TABLE_NAME, null, contentValues);

        if (res == -1){ return false; }
        else {return true;}
    }

    public boolean checkData(String username, String password){
        String[] columns = { COL1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL2 + "=?" + " and " + COL3 + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count > 0) {
            return true;
        } else{ return false;}
    }

}
