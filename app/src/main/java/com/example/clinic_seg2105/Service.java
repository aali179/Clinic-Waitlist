package com.example.clinic_seg2105;

public class Service {
    private String name;
    private String role;

    public Service(){

    }

    public Service(String name, String role){
        this.name = name;
        this.role = role;
    }

    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }

    public String getRole(){ return role; }
    public void setRole(String role){ this.role = role; }
}
