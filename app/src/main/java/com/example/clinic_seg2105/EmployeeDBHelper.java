package com.example.clinic_seg2105;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EmployeeDBHelper extends SQLiteOpenHelper {

    //version number to upgrade database version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "employees.db";

    public static final String TABLE = "table";

    public EmployeeDBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_EMPLOYEE = "CREATE TABLE " + Employee.TABLE  + "("
                + Employee.KEY_name + " TEXT, "
                + Employee.KEY_username + " TEXT, "
                + Employee.KEY_password + " TEXT)";

        db.execSQL(CREATE_TABLE_EMPLOYEE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone
        db.execSQL("DROP TABLE IF EXISTS " + Employee.TABLE);

        // Create tables again
        onCreate(db);

    }
}

