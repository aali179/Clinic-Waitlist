
package com.example.clinic_seg2105;

public class Employee {

    private String name;
    private String email;
    private String number;
    private String address;
    private String insurance;
    private String payment;
    private String password;

    public Employee(){

    }

    public Employee(String name, String email, String number, String address, String insurance, String payment, String password){
        this.name = name;
        this.email = email;
        this.number = number;
        this.address = address;
        this.insurance = insurance;
        this.payment = payment;
    }

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}

    public String getNumber(){return number;}
    public void setNumber(String email){this.number = number;}

    public String getAddress(){return address;}
    public void setAddress(String address){this.address = address;}

    public String getInsurance(){return insurance;}
    public void setInsurance(String insurance){this.insurance = insurance;}

    public String getPayment(){return payment;}
    public void setPayment(String payment){this.payment = payment;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}

}
