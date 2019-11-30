package com.example.clinic_seg2105;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class patientIsOpen extends AppCompatActivity implements View.OnClickListener,
        TimePickerDialog.OnTimeSetListener {

    public static final String EXTRA_TEXT = "com.example.hospital_clinic.EXTRA_TEXT";
    public static final String CLINIC_ID = "com.example.hospital_clinic.CLINIC_ID";

    CalendarView calendarView;
    Button timePicker;
    ListView clinicListTime;
    String day_of_week;
    private DatabaseReference mRef;
    private TextView test;
    private Button search;
    private String id_of_clinic;

    ToggleButton monday;
    ToggleButton tuesday;
    ToggleButton wednesday;
    ToggleButton thursday;
    ToggleButton friday;

    Calendar currentTime;
    int hour, min;

    ArrayList<String> nameClinic = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_is_open);

        timePicker = (Button) findViewById(R.id.timePicker);
        clinicListTime = (ListView) findViewById(R.id.clinicListTime);

        mRef = FirebaseDatabase.getInstance().getReference();

        test = (TextView) findViewById(R.id.test);

        monday = (ToggleButton) findViewById(R.id.monday);
        tuesday = (ToggleButton) findViewById(R.id.tuesday);
        wednesday = (ToggleButton) findViewById(R.id.wednesday);
        thursday = (ToggleButton) findViewById(R.id.thursday);
        friday = (ToggleButton) findViewById(R.id.friday);

        changeListener();

        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(this);
        timePicker.setOnClickListener(this);

        clinicListTime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = nameClinic.get(position);
                goToBooking(name);
            }
        });

    }

    private void changeListener() {
        monday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    day_of_week = "Monday";
                    test.setText("mo");

                    tuesday.setChecked(false);
                    wednesday.setChecked(false);
                    thursday.setChecked(false);
                    friday.setChecked(false);

                }
            }
        });

        tuesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    day_of_week = "Tuesday";
                    test.setText("tu");

                    monday.setChecked(false);
                    wednesday.setChecked(false);
                    thursday.setChecked(false);
                    friday.setChecked(false);

                }
            }
        });

        wednesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    day_of_week = "Wednesday";
                    test.setText("we");

                    monday.setChecked(false);
                    tuesday.setChecked(false);
                    thursday.setChecked(false);
                    friday.setChecked(false);

                }
            }
        });

        thursday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    day_of_week = "Thursday";
                    test.setText("thu");
                    monday.setChecked(false);
                    tuesday.setChecked(false);
                    wednesday.setChecked(false);
                    friday.setChecked(false);
                }
            }
        });

        friday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    day_of_week = "Friday";
                    test.setText("fri");
                    monday.setChecked(false);
                    tuesday.setChecked(false);
                    wednesday.setChecked(false);
                    thursday.setChecked(false);

                }
            }
        });

    }


    private void populateList() {

        mRef.child("Employees").addValueEventListener(new ValueEventListener() {
            ArrayList<Integer> hourOpen = new ArrayList<Integer>();
            ArrayList<Integer> minOpen = new ArrayList<Integer>();
            ArrayList<Integer> hourClose = new ArrayList<Integer>();
            ArrayList<Integer> minClose = new ArrayList<Integer>();

            ListAdapter adapter = new ArrayAdapter<String>(patientIsOpen.this, android.R.layout.simple_list_item_1, nameClinic);

            boolean isOpen;

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    for (DataSnapshot ds2 : ds.getChildren()) {
                        if (ds2.getKey().equals("WorkingHours")) {
                            for (DataSnapshot ds3 : ds2.getChildren()) {
                                Integer open_hour;
                                Integer open_min;
                                Integer close_hour;
                                Integer close_min;
                                if (ds3.getKey().equals(day_of_week)){
                                    open_hour = ds3.child("Open").child("hour").getValue(Integer.class);
                                    open_min = ds3.child("Open").child("minute").getValue(Integer.class);
                                    close_hour = ds3.child("Close").child("hour").getValue(Integer.class);
                                    close_min = ds3.child("Close").child("minute").getValue(Integer.class);

                                    if (hour > open_hour && hour < close_hour){
                                        isOpen = true;
                                        nameClinic.add(ds.child("name").getValue(String.class));
                                    }

                                    if (hour == open_hour){
                                        if (min > open_min){
                                            nameClinic.add(ds.child("name").getValue(String.class));
                                        }
                                    }

                                    if (hour == close_hour){
                                        if (min < close_min){
                                            nameClinic.add(ds.child("name").getValue(String.class));
                                        }
                                    }
                                    hourOpen.add(open_hour);
                                    minOpen.add(open_min);
                                    hourClose.add(close_hour);
                                    minClose.add(close_min);
                                }
                            }
                        }

                    }
                }
                clinicListTime.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.timePicker:
                pickTime();
                break;

            case R.id.search:
                populateList();
                break;
        }

    }



    private void pickTime(){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }


    @Override
    public void onTimeSet (TimePicker view,int hourOfDay, int minute){
        hour = hourOfDay;
        min =  minute;


    }


    private void goToBooking(final String name){

        mRef.child("Employees").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    if ( (ds.child("name").getValue(String.class)).equals(name) ){
                        id_of_clinic = ds.getKey();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        startIntent(name);
    }

    private void startIntent(String name){

        Intent intent = new Intent(getApplicationContext(), patientBookingRating.class);
        intent.putExtra(EXTRA_TEXT, name);
        intent.putExtra(CLINIC_ID, id_of_clinic);
        startActivity(intent);

    }


}
