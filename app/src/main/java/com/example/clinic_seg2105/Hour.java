package com.example.clinic_seg2105;

public class Hour {

    private int hr;
    private int minute;

    public Hour(){

    }

    public Hour(int hr, int minute){
        this.hr = hr;
        this.minute = minute;
    }

    public int getHour(){return hr;}
    public void setHour(int hr){this.hr = hr;}

    public int getMinute(){return minute;}
    public void setMinute(int minute){this.minute = minute;}

}