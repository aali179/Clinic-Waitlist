package com.example.clinic_seg2105;

public class Employee {

    // Labels table name
    public static final String TABLE = "Employee";

    // Labels Table Columns names
    public static final String KEY_username = "username";
    public static final String KEY_password = "password";
    public static final String KEY_name = "name";
    public static final String KEY_clinic = "clinic";
    public static final String KEY_address = "address";
    public static final String KEY_phone = "phone";
    public static final String KEY_payment = "payment";
    public static final String KEY_insurance = "insurance";

    private String username;
    private String password;
    private String name;
    private String clinic;
    private String address;
    private String phone;
    private String payment;
    private String insurance;

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

    public void setClinic(String clinic){
        this.clinic=clinic;
    }

    public String getClinic(){
        return this.clinic;
    }

    public void setAddress(String address){
        this.address=address;
    }

    public String getAddress(){
        return this.address;
    }

    public void setPhone(String phone){
        this.phone=phone;
    }

    public String getPhone(){
        return this.phone;
    }

    public void setPayment(String payment){
        this.payment=payment;
    }

    public String getPayment(){
        return this.payment;
    }

    public void setInsurance(String insurance){
        this.insurance=insurance;
    }

    public String getInsurance(){
        return this.insurance;
    }

}





