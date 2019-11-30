package com.example.hospital_clinic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class employeeSetHours extends AppCompatActivity implements
        View.OnClickListener, TimePickerDialog.OnTimeSetListener {

    private Button mondayStartTime, mondayEndTime,
                tuesdayStartTime, tuesdayEndTime,
                wednesdayStartTime, wednesdayEndTime,
                thursdayStartTime, thursdayEndTime,
                fridayStartTime, fridayEndTime;


    private String monday, tuesday, wednesday, thursday, friday;

    private TextView mondayText, tuesdayText, wednesdayText,
                        thursdayText, fridayText;

    Calendar currentTime;
    int hour, minute, activePickerID;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_set_hours);

        initializer();

        buttonOnSetListeners();

        currentTime = Calendar.getInstance();

        hour = currentTime.get(Calendar.HOUR_OF_DAY);
        minute = currentTime.get(Calendar.MINUTE);

    }

    @Override
    public void onClick(View v) {
        activePickerID = v.getId();

        switch(v.getId()){
            case R.id.mondayStartTime:
                mondayStartTimeFunc();
                break;
            case R.id.mondayEndTime:
                mondayEndTimeFunc();
                break;
            case R.id.tuesdayStartTime:
                tuesdayStartTimeFunc();
                break;
            case R.id.tuesdayEndTime:
                tuesdayEndTimeFunc();
                break;
            case R.id.wednesdayStartTime:
                wednesdayStartTimeFunc();
                break;
            case R.id.wednesdayEndTime:
                wednesdayEndTimeFunc();
                break;
            case R.id.thursdayStartTime:
                thursdayStartTimeFunc();
                break;
            case R.id.thursdayEndTime:
                thursdayEndTimeFunc();
                break;
            case R.id.fridayStartTime:
                fridayStartTimeFunc();
                break;
            case R.id.fridayEndTime:
                fridayEndTimeFunc();
                break;
        }
    }

    private void initializer(){

        mondayStartTime = (Button) findViewById(R.id.mondayStartTime);
        mondayEndTime = (Button) findViewById(R.id.mondayEndTime);
        tuesdayStartTime = (Button) findViewById(R.id.tuesdayStartTime);
        tuesdayEndTime = (Button) findViewById(R.id.tuesdayEndTime);
        wednesdayStartTime = (Button) findViewById(R.id.wednesdayStartTime);
        wednesdayEndTime = (Button) findViewById(R.id.wednesdayEndTime);
        thursdayStartTime = (Button) findViewById(R.id.thursdayStartTime);
        thursdayEndTime = (Button) findViewById(R.id.thursdayEndTime);
        fridayStartTime = (Button) findViewById(R.id.fridayStartTime);
        fridayEndTime = (Button) findViewById(R.id.fridayEndTime);

        mondayText = (TextView) findViewById(R.id.mondayText);
        tuesdayText = (TextView) findViewById(R.id.tuesdayText);
        wednesdayText = (TextView) findViewById(R.id.wednesdayText);
        thursdayText = (TextView) findViewById(R.id.thursdayText);
        fridayText = (TextView) findViewById(R.id.fridayText);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    private void buttonOnSetListeners(){

        mondayStartTime.setOnClickListener(this);
        mondayEndTime.setOnClickListener(this);
        tuesdayStartTime.setOnClickListener(this);
        tuesdayEndTime.setOnClickListener(this);
        wednesdayStartTime.setOnClickListener(this);
        wednesdayEndTime.setOnClickListener(this);
        thursdayStartTime.setOnClickListener(this);
        thursdayEndTime.setOnClickListener(this);
        fridayStartTime.setOnClickListener(this);
        fridayEndTime.setOnClickListener(this);
    }

    private void mondayStartTimeFunc(){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }

    private void mondayEndTimeFunc(){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }

    private void tuesdayStartTimeFunc(){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }

    private void tuesdayEndTimeFunc(){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }

    private void wednesdayStartTimeFunc(){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }

    private void wednesdayEndTimeFunc(){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }

    private void thursdayStartTimeFunc(){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }

    private void thursdayEndTimeFunc(){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }

    private void fridayStartTimeFunc(){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }

    private void fridayEndTimeFunc(){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        if (activePickerID == R.id.mondayStartTime){
            mondayText.append(hourOfDay + " " + minute);
            Hour hr = new Hour(hourOfDay, minute);
            mDatabase.child("Employees").child(mUser.getUid()).child("WorkingHours").child("Monday").child("Open").setValue(hr);
        }

        if (activePickerID == R.id.mondayEndTime){
            mondayText.append(hourOfDay + " " + minute);
            Hour hr = new Hour(hourOfDay, minute);
            mDatabase.child("Employees").child(mUser.getUid()).child("WorkingHours").child("Monday").child("Close").setValue(hr);
        }






        if (activePickerID == R.id.tuesdayStartTime){
            Hour hr = new Hour(hourOfDay, minute);
            mDatabase.child("Employees").child(mUser.getUid()).child("WorkingHours").child("Tuesday").child("Open").setValue(hr);
        }

        if (activePickerID == R.id.tuesdayEndTime){
            Hour hr = new Hour(hourOfDay, minute);
            mDatabase.child("Employees").child(mUser.getUid()).child("WorkingHours").child("Tuesday").child("Close").setValue(hr);
        }








        if (activePickerID == R.id.wednesdayStartTime){
            Hour hr = new Hour(hourOfDay, minute);
            mDatabase.child("Employees").child(mUser.getUid()).child("WorkingHours").child("Wednesday").child("Open").setValue(hr);
        }

        if (activePickerID == R.id.wednesdayEndTime){
            Hour hr = new Hour(hourOfDay, minute);
            mDatabase.child("Employees").child(mUser.getUid()).child("WorkingHours").child("Wednesday").child("Close").setValue(hr);
        }





        if (activePickerID == R.id.thursdayStartTime){
            Hour hr = new Hour(hourOfDay, minute);
            mDatabase.child("Employees").child(mUser.getUid()).child("WorkingHours").child("Thursday").child("Open").setValue(hr);
        }

        if (activePickerID == R.id.thursdayEndTime){
            Hour hr = new Hour(hourOfDay, minute);
            mDatabase.child("Employees").child(mUser.getUid()).child("WorkingHours").child("Thursday").child("Close").setValue(hr);
        }






        if (activePickerID == R.id.fridayStartTime){
            Hour hr = new Hour(hourOfDay, minute);
            mDatabase.child("Employees").child(mUser.getUid()).child("WorkingHours").child("Friday").child("Open").setValue(hr);
        }

        if (activePickerID == R.id.fridayEndTime){
            Hour hr = new Hour(hourOfDay, minute);
            mDatabase.child("Employees").child(mUser.getUid()).child("WorkingHours").child("Friday").child("Close").setValue(hr);
        }

    }
}
