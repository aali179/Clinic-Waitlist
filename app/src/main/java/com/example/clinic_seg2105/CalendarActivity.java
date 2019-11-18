package com.example.clinic_seg2105;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView date;
    EditText startTime;
    EditText endTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        date = (TextView)findViewById(R.id.date);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int day, int month) {
                //incrementing date
                String newDate = (day + 1) + "/" + month + "/" + year;
                //replace current date with new selected date
                date.setText(newDate);
            }
        });

        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);

    }


    public void AddHours(View view) {
        //insert these hours into a data base?
    }



}
