package com.example.clinic_seg2105;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ServiceDBHelper extends SQLiteOpenHelper {

    //version number to upgrade database version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "spinner2.db";

    public static final String TABLE = "table";

    public ServiceDBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_SERVICE = "CREATE TABLE " + Service.TABLE  + "("
                + Service.KEY_service + " TEXT, "
                + Service.KEY_role + " TEXT )";

        db.execSQL(CREATE_TABLE_SERVICE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone
        db.execSQL("DROP TABLE IF EXISTS " + Service.TABLE);

        // Create tables again
        onCreate(db);

    }
}
