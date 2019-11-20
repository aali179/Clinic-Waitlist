package com.example.clinic_seg2105;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EmployeeServiceDBHelper extends SQLiteOpenHelper {

    //version number to upgrade database version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "employeeServices.db";

    public static final String TABLE = "table";

    public EmployeeServiceDBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_EMPLOYEESERVICE = "CREATE TABLE " + EmployeeService.TABLE  + "("
                + EmployeeService.KEY_username + " TEXT, "
                + EmployeeService.KEY_service + " TEXT)";

        db.execSQL(CREATE_TABLE_EMPLOYEESERVICE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone
        db.execSQL("DROP TABLE IF EXISTS " + EmployeeService.TABLE);

        // Create tables again
        onCreate(db);

    }
}

