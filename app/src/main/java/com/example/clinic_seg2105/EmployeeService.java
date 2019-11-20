package com.example.clinic_seg2105;

public class EmployeeService {

    // Labels table name
    public static final String TABLE = "EmployeeService";

    // Labels Table Columns names
    public static final String KEY_username = "username";
    public static final String KEY_service = "service";

    private String username;
    private String service;

    public void setUsername(String username){
        this.username=username;
    }

    public String getUsername(){
        return this.username;
    }

    public void setService(String service){
        this.service=service;
    }

    public String getService(){
        return this.service;
    }

}

