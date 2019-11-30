package com.example.clinic_seg2105;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClinicRepo {

    private ClinicDBHelper dbHelper;

    public ClinicRepo(Context context) {
        dbHelper = new ClinicDBHelper(context);
    }

    //Delete all data in the table
    public void Delete() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Clinic.TABLE, null, null);
        db.close(); // Closing database connection
    }

    //Insert student records
    public int insert(Clinic clinic) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Clinic.KEY_name, clinic.getName());
        values.put(Clinic.KEY_address, clinic.getAddress());
        values.put(Clinic.KEY_phone, clinic.getPhone());
        values.put(Clinic.KEY_payment, clinic.getPayment());
        values.put(Clinic.KEY_insurance, clinic.getInsurance());
        //values.put(Employee.KEY_services, employee.getServices());
        // Inserting Row
        long clinic_Id = db.insert(Clinic.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) clinic_Id;
    }

    public void delete(String name) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Clinic.TABLE, Employee.KEY_name + "=?", new String[]{name} );
        db.close();
    }

    //Retrieve all records and populate into List<String>
    public List<String> getAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Clinic.KEY_name + "," +
                Clinic.KEY_address + "," +
                Clinic.KEY_phone + "," +
                Clinic.KEY_payment + "," +
                Clinic.KEY_insurance +
                //Employee.KEY_services +
                " FROM " + Clinic.TABLE;

        List<String> clinicList = new ArrayList<String>() ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        Integer i=0;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                clinicList.add(i,cursor.getString(cursor.getColumnIndex(Clinic.KEY_name)));
                i+=1;
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return clinicList;

    }

    public List<String> getAutofill(String clnc) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Clinic.KEY_name + "," +
                Clinic.KEY_address + "," +
                Clinic.KEY_phone + "," +
                Clinic.KEY_payment + "," +
                Clinic.KEY_insurance +
                " FROM " + Clinic.TABLE + " WHERE " + Clinic.KEY_name + " =?";

        List<String> clinicList = new ArrayList<String>() ;
        Cursor cursor = db.rawQuery(selectQuery, new String[]{clnc});
        Integer i=0;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                clinicList.add(i,cursor.getString(cursor.getColumnIndex(Clinic.KEY_address)));
                i+=1;
                clinicList.add(i,cursor.getString(cursor.getColumnIndex(Clinic.KEY_phone)));
                i+=1;
                clinicList.add(i,cursor.getString(cursor.getColumnIndex(Clinic.KEY_payment)));
                i+=1;
                clinicList.add(i,cursor.getString(cursor.getColumnIndex(Clinic.KEY_insurance)));
                i+=1;
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return clinicList;

    }
}
