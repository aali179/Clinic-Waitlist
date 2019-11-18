package com.example.clinic_seg2105;

public class Employee {

    // Labels table name
    public static final String TABLE = "Employee";

    // Labels Table Columns names
    public static final String KEY_username = "username";
    public static final String KEY_password = "password";
    public static final String KEY_email = "email";
    public static final String KEY_telephone = "telephone";
    public static final String KEY_clinic = "clinic";

    private String username;
    private String password;
    private String email;
    private String telephone;
    private String clinic;

    public void setUsername(String username){
        this.username=username;
    }

    public String getUsername(){
        return this.username;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String getPassword(){
        return this.password;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setTelephone(String telephone){
        this.telephone=telephone;
    }

    public String getTelephone(){
        return this.telephone;
    }

    public void setClinic(String clinic){
        this.clinic=clinic;
    }

    public String getClinic(){
        return this.clinic;
    }


}

