package com.example.clinic_seg2105;

public class Employee {

    // Labels table name
    public static final String TABLE = "Employee";

    // Labels Table Columns names
    public static final String KEY_username = "username";
    public static final String KEY_password = "password";
    public static final String KEY_name = "name";
    public static final String KEY_services = "services";

    private String username;
    private String password;
    private String name;
    private Service[] services;

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

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }

    public Service[] getServices() {
        return this.services;
    }


    public void addService(Service service) {
        int numService = services.length;
        this.services[numService] = service;
    }

    public void deleteService(Service service) {
        int numService = services.length;

        // deletes service from list
        for (int i = 0; i < numService; i++) {
            if (this.services[numService] == service) {
                this.services[numService] = null;
            }
        }

        // shifts services up to remove the null
    }



}

