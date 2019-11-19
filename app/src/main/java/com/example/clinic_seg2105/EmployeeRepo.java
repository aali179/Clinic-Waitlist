package com.example.clinic_seg2105;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmployeeRepo {

    private EmployeeDBHelper dbHelper;

    public EmployeeRepo(Context context) {
        dbHelper = new EmployeeDBHelper(context);
    }

    //Delete all data in the table
    public void Delete() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Employee.TABLE, null, null);
        db.close(); // Closing database connection
    }

    //Insert student records
    public int insert(Employee employee) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Employee.KEY_name, employee.getName());
        values.put(Employee.KEY_username, employee.getUsername());
        values.put(Employee.KEY_password, employee.getPassword());
        //values.put(Employee.KEY_services, employee.getServices());
        // Inserting Row
        long employee_Id = db.insert(Employee.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) employee_Id;
    }

    public void delete(String name) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Employee.TABLE, Employee.KEY_username + "=?", new String[]{name} );
        db.close();
    }

    //Retrieve all records and populate into List<String>
    public List<String> getAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Employee.KEY_name + "," +
                Employee.KEY_username + "," +
                Employee.KEY_password +
                //Employee.KEY_services +
                " FROM " + Employee.TABLE;

        List<String> employeeList = new ArrayList<String>() ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        Integer i=0;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                employeeList.add(i,cursor.getString(cursor.getColumnIndex(Employee.KEY_username)));
                i+=1;
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return employeeList;

    }
}
