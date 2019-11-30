package com.example.clinic_seg2105;

public class Comments {

    public String comment;
    public String date;
    public String time;
    public String id;

    public Comments(){

    }

    public Comments(String comment, String date, String time, String id) {
        this.comment = comment;
        this.date = date;
        this.time = time;
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getId() {
        return id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setId(String id) {
        this.id = id;
    }
}
