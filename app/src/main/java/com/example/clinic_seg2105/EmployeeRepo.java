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
        values.put(Employee.KEY_clinic, employee.getClinic());
        values.put(Employee.KEY_address, employee.getAddress());
        values.put(Employee.KEY_phone, employee.getPhone());
        values.put(Employee.KEY_payment, employee.getPayment());
        values.put(Employee.KEY_insurance, employee.getInsurance());
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

    public String getPassword(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Employee.KEY_name + "," +
                Employee.KEY_username + "," +
                Employee.KEY_password + "," +
                Employee.KEY_clinic + "," +
                Employee.KEY_address + "," +
                Employee.KEY_phone + "," +
                Employee.KEY_payment + "," +
                Employee.KEY_insurance +
                " FROM " + Employee.TABLE + " WHERE " + Employee.KEY_username + " =?";
        List<String> passwordList = new ArrayList<String>() ;
        Cursor cursor = db.rawQuery(selectQuery, new String[] {email});
        Integer i =0;
        if (cursor.moveToFirst()) {
            do {
                passwordList.add(i,cursor.getString(cursor.getColumnIndex(Employee.KEY_password)));
                i+=1;
            } while (cursor.moveToNext());
        }

        return passwordList.get(0);
    }

    public String getName(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Employee.KEY_name + "," +
                Employee.KEY_username + "," +
                Employee.KEY_password + "," +
                Employee.KEY_clinic + "," +
                Employee.KEY_address + "," +
                Employee.KEY_phone + "," +
                Employee.KEY_payment + "," +
                Employee.KEY_insurance +
                " FROM " + Employee.TABLE + " WHERE " + Employee.KEY_username + " =?";
        List<String> nameList = new ArrayList<String>() ;
        Cursor cursor = db.rawQuery(selectQuery, new String[] {email});
        Integer i =0;
        if (cursor.moveToFirst()) {
            do {
                nameList.add(i,cursor.getString(cursor.getColumnIndex(Employee.KEY_password)));
                i+=1;
            } while (cursor.moveToNext());
        }

        return nameList.get(0);
    }

    public String getAddress(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Employee.KEY_name + "," +
                Employee.KEY_username + "," +
                Employee.KEY_password + "," +
                Employee.KEY_clinic + "," +
                Employee.KEY_address + "," +
                Employee.KEY_phone + "," +
                Employee.KEY_payment + "," +
                Employee.KEY_insurance +
                " FROM " + Employee.TABLE + " WHERE " + Employee.KEY_username + " =?";
        List<String> addressList = new ArrayList<String>() ;
        Cursor cursor = db.rawQuery(selectQuery, new String[] {email});
        Integer i =0;
        if (cursor.moveToFirst()) {
            do {
                addressList.add(i,cursor.getString(cursor.getColumnIndex(Employee.KEY_password)));
                i+=1;
            } while (cursor.moveToNext());
        }

        return addressList.get(0);
    }

    public String getPhone(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Employee.KEY_name + "," +
                Employee.KEY_username + "," +
                Employee.KEY_password + "," +
                Employee.KEY_clinic + "," +
                Employee.KEY_address + "," +
                Employee.KEY_phone + "," +
                Employee.KEY_payment + "," +
                Employee.KEY_insurance +
                " FROM " + Employee.TABLE + " WHERE " + Employee.KEY_username + " =?";
        List<String> phoneList = new ArrayList<String>() ;
        Cursor cursor = db.rawQuery(selectQuery, new String[] {email});
        Integer i =0;
        if (cursor.moveToFirst()) {
            do {
                phoneList.add(i,cursor.getString(cursor.getColumnIndex(Employee.KEY_password)));
                i+=1;
            } while (cursor.moveToNext());
        }

        return phoneList.get(0);
    }

    public String getPayment(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Employee.KEY_name + "," +
                Employee.KEY_username + "," +
                Employee.KEY_password + "," +
                Employee.KEY_clinic + "," +
                Employee.KEY_address + "," +
                Employee.KEY_phone + "," +
                Employee.KEY_payment + "," +
                Employee.KEY_insurance +
                " FROM " + Employee.TABLE + " WHERE " + Employee.KEY_username + " =?";
        List<String> paymentList = new ArrayList<String>() ;
        Cursor cursor = db.rawQuery(selectQuery, new String[] {email});
        Integer i =0;
        if (cursor.moveToFirst()) {
            do {
                paymentList.add(i,cursor.getString(cursor.getColumnIndex(Employee.KEY_password)));
                i+=1;
            } while (cursor.moveToNext());
        }

        return paymentList.get(0);
    }

    public String getClinic(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Employee.KEY_name + "," +
                Employee.KEY_username + "," +
                Employee.KEY_password + "," +
                Employee.KEY_clinic + "," +
                Employee.KEY_address + "," +
                Employee.KEY_phone + "," +
                Employee.KEY_payment + "," +
                Employee.KEY_insurance +
                " FROM " + Employee.TABLE + " WHERE " + Employee.KEY_username + " =?";
        List<String> clinicList = new ArrayList<String>() ;
        Cursor cursor = db.rawQuery(selectQuery, new String[] {email});
        Integer i =0;
        if (cursor.moveToFirst()) {
            do {
                clinicList.add(i,cursor.getString(cursor.getColumnIndex(Employee.KEY_password)));
                i+=1;
            } while (cursor.moveToNext());
        }

        return clinicList.get(0);
    }

    public String getInsurance(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Employee.KEY_name + "," +
                Employee.KEY_username + "," +
                Employee.KEY_password + "," +
                Employee.KEY_clinic + "," +
                Employee.KEY_address + "," +
                Employee.KEY_phone + "," +
                Employee.KEY_payment + "," +
                Employee.KEY_insurance +
                " FROM " + Employee.TABLE + " WHERE " + Employee.KEY_username + " =?";
        List<String> insuranceList = new ArrayList<String>() ;
        Cursor cursor = db.rawQuery(selectQuery, new String[] {email});
        Integer i =0;
        if (cursor.moveToFirst()) {
            do {
                insuranceList.add(i,cursor.getString(cursor.getColumnIndex(Employee.KEY_password)));
                i+=1;
            } while (cursor.moveToNext());
        }

        return insuranceList.get(0);
    }

    public boolean login(String email, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Employee.KEY_name + "," +
                Employee.KEY_username + "," +
                Employee.KEY_password + "," +
                Employee.KEY_clinic + "," +
                Employee.KEY_address + "," +
                Employee.KEY_phone + "," +
                Employee.KEY_payment + "," +
                Employee.KEY_insurance +
                " FROM " + Employee.TABLE + " WHERE " + Employee.KEY_username + " =?";
        List<String> passwordList = new ArrayList<String>() ;
        Cursor cursor = db.rawQuery(selectQuery, new String[] {email});
        Integer i =0;
        if (cursor.moveToFirst()) {
            do {
                passwordList.add(i,cursor.getString(cursor.getColumnIndex(Employee.KEY_password)));
                i+=1;
            } while (cursor.moveToNext());
        }

        if (passwordList.contains(password)) {
            return true;
        }

        return false;

    }

    //Retrieve all records and populate into List<String>
    public List<String> getAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Employee.KEY_name + "," +
                Employee.KEY_username + "," +
                Employee.KEY_password + "," +
                Employee.KEY_clinic + "," +
                Employee.KEY_address + "," +
                Employee.KEY_phone + "," +
                Employee.KEY_payment + "," +
                Employee.KEY_insurance +
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
