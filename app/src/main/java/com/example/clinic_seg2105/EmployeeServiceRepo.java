package com.example.clinic_seg2105;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmployeeServiceRepo {

    private EmployeeServiceDBHelper dbHelper;

    public EmployeeServiceRepo(Context context) {
        dbHelper = new EmployeeServiceDBHelper(context);
    }

    //Delete all data in the table
    public void Delete() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(EmployeeService.TABLE, null, null);
        db.close(); // Closing database connection
    }

    //Insert student records
    public int insert(EmployeeService employeeService) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EmployeeService.KEY_username, employeeService.getUsername());
        values.put(EmployeeService.KEY_service, employeeService.getService());
        // Inserting Row
        long employeeService_Id = db.insert(EmployeeService.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) employeeService_Id;
    }

    public void delete(String name, String service) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(EmployeeService.TABLE, EmployeeService.KEY_username + "=? AND " +
                EmployeeService.KEY_service + "=?", new String[]{name, service} );
        db.close();
    }

    //Retrieve all records and populate into List<String>
    public List<String> getAll(String activeUser) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                EmployeeService.KEY_username + "," +
                EmployeeService.KEY_service +
                " FROM " + EmployeeService.TABLE  + " WHERE " + EmployeeService.KEY_username + " =?";

        List<String> employeeServiceList = new ArrayList<String>() ;
        Cursor cursor = db.rawQuery(selectQuery, new String[]{activeUser});
        Integer i=0;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                employeeServiceList.add(i,cursor.getString(cursor.getColumnIndex(EmployeeService.KEY_service)));
                i+=1;
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return employeeServiceList;

    }
}

