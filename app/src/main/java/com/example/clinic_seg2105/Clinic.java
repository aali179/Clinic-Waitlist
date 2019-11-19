package com.example.clinic_seg2105;

public class Clinic {

    // Labels table name
    public static final String TABLE = "Clinic";

    // Labels Table Columns names
    public static final String KEY_name = "name";
    public static final String KEY_address = "address";
    public static final String KEY_phone = "phone";
    public static final String KEY_payment = "payment";
    public static final String KEY_insurance = "insurance";

    private String address;
    private String phone;
    private String name;
    private String insurance;
    private String payment;

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
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

    public void setInsurance(String insurance){
        this.insurance=insurance;
    }

    public String getInsurance(){
        return this.insurance;
    }

    public void setPayment(String payment){
        this.payment=payment;
    }

    public String getPayment(){
        return this.payment;
    }

   /* public Service[] getServices() {
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

*/

}

