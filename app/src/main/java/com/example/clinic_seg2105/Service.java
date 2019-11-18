package com.example.clinic_seg2105;

public class Service {

    // Labels table name
    public static final String TABLE = "Service";

    // Labels Table Columns names
    public static final String KEY_service = "service";
    public static final String KEY_role = "role";
    public static final String KEY_id = "id";

    private String service;
    private String role;
    private Integer id;

    public void setService(String service){
        this.service=service;
    }

    public String getService(){
        return this.service;
    }


    public void setRole(String role){
        this.role=role;
    }

    public String getRole(){
        return this.role;
    }

    public void setID(Integer id) {
        this.id=id;
    }

    public Integer getID() {
        return this.id;
    }

}
