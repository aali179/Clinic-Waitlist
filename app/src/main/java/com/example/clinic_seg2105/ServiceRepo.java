package com.example.clinic_seg2105;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServiceRepo {

    private ServiceDBHelper dbHelper;

    public ServiceRepo(Context context) {
        dbHelper = new ServiceDBHelper(context);
    }

    //Delete all data in the table
    public void Delete() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Service.TABLE, null, null);
        db.close(); // Closing database connection
    }

    //Insert student records
    public int insert(Service service) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Service.KEY_service, service.getService());
        values.put(Service.KEY_role, service.getRole());

        // Inserting Row
        long service_Id = db.insert(Service.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) service_Id;
    }


    //Retrieve all records and populate into List<String>
    public List<String> getAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Service.KEY_service + "," +
                Service.KEY_role +
                " FROM " + Service.TABLE;

        List<String> serviceList = new ArrayList<String>() ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        Integer i=0;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                serviceList.add(i,cursor.getString(cursor.getColumnIndex(Service.KEY_service)));
                i+=1;
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return serviceList;

    }
}
