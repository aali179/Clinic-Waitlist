package com.example.clinic_seg2105;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database_helper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "People.db";
    public static final String TABLE_NAME = "people_table";
    public static final String COL1 = "NAME";
    public static final String COL2 = "TYPE";
    public static final String COL3 = "PASSWORD";


    public Database_helper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }
    @Override

    public void onCreate(SQLiteDatabase db){
        db.execSQL("create Table " + TABLE_NAME + " (NAME INTEGER PRIMARY KEY AUTOINCREMENT, TYPE TEXT, PASSWORD TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS" +TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String Password, String Type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,name);
        contentValues.put(COL2, Password);
        contentValues.put(COL3, Type);
        long res = db.insert(TABLE_NAME, null, contentValues);

        if (res == -1){ return false; }
        else {return true;}
    }

}
