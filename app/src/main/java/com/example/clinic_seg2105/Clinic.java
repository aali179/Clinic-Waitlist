package com.example.clinic_seg2105;

import java.util.Vector;

public class Clinic {

    private String locationAddress;
    private String phoneNumber;
    private String nameClinic;
    private String insuranceType;
    private String paymentMethod;

    public Clinic(){
        this.locationAddress = "";
        this.phoneNumber = "";
        this.nameClinic = "";
        this.insuranceType = "";
        this.paymentMethod = "";
    }

    public Clinic(String locationAddress, String phoneNumber, String nameClinic, String insuranceType, String paymentMethod){
        this.locationAddress = locationAddress;
        this.phoneNumber = phoneNumber;
        this.nameClinic = nameClinic;
        this.insuranceType = insuranceType;
        this.paymentMethod = paymentMethod;
    }

    public String getLocationAddress(){ return locationAddress; }
    public void setLocationAddress(String locationAddress){ this.locationAddress = locationAddress;}

    public String getPhoneNumber(){ return phoneNumber; }
    public void setPhoneNumber(String phoneNumber){ this.phoneNumber= phoneNumber;}

    public String getNameClinic(){ return nameClinic; }
    public void setNameClinic(String nameClinic){ this.nameClinic= nameClinic;}

    public String getInsuranceType(){ return insuranceType; }
    public void setInsuranceType(String insuranceType){ this.insuranceType= insuranceType;}

    public String getPaymentMethod(){ return paymentMethod; }
    public void setPaymentMethod(String paymentMethod){ this.paymentMethod = paymentMethod;}

}

