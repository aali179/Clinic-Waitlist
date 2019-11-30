package com.example.clinic_seg2105;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView date;
    String stringDate;
    Date startTime;
    Date endTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        date = (TextView) (findViewById(R.id.date));

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int day, int month) {
                //incrementing date
                String newDate = (day + 1) + "/" + month + "/" + year;
                //replace current date with new selected date
                date.setText(newDate);
                stringDate = date.getText().toString();
            }
        });

        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        try {
            startTime = dateFormat.parse(findViewById(R.id.startTime).toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            endTime = dateFormat.parse(findViewById(R.id.endTime).toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void AddHours(View view) {
        Hours newHours = new Hours(startTime, endTime, stringDate);
        Toast.makeText(this, "Hours set for " + stringDate, Toast.LENGTH_LONG).show();

    }

}