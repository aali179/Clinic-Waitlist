package com.example.clinic_seg2105;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClinicDBHelper extends SQLiteOpenHelper {

    //version number to upgrade database version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "clinic.db";

    public static final String TABLE = "table";

    public ClinicDBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_CLINIC = "CREATE TABLE " + Clinic.TABLE  + "("
                + Clinic.KEY_name + " TEXT, "
                + Clinic.KEY_address + " TEXT, "
                + Clinic.KEY_phone + " TEXT, "
                + Clinic.KEY_payment + " TEXT, "
                + Clinic.KEY_insurance + " TEXT)";
        //+ Employee.KEY_services + " SERVICE)";

        db.execSQL(CREATE_TABLE_CLINIC);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone
        db.execSQL("DROP TABLE IF EXISTS " + Clinic.TABLE);

        // Create tables again
        onCreate(db);

    }
}

