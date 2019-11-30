package com.example.clinic_seg2105;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class Hours extends AppCompatActivity {

    public Date startHour;
    public Date endHour;
    public long totalHours;
    public String date;

    public Hours(Date startTime, Date endTime, String stringDate) {
        this.startHour = startTime;
        this.endHour = endTime;
        this.totalHours = Math.abs(endHour.getTime() - startHour.getTime());
        this.date = stringDate;
    }
}
