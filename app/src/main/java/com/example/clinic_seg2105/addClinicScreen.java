package com.example.clinic_seg2105;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addClinicScreen extends AppCompatActivity {

    private EditText phoneNumber;
    private EditText nameClinic;
    private EditText emailAddress;
    private Spinner insurance;
    private Spinner payment;
    private Button saveButton;
    private DatabaseReference clinicDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clinic_screen);

        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        nameClinic = (EditText) findViewById(R.id.nameOfClinic);
        emailAddress = (EditText) findViewById(R.id.emailAddress);
        insurance = (Spinner) findViewById(R.id.insurance);
        payment = (Spinner) findViewById(R.id.payment);
        saveButton = (Button) findViewById(R.id.saveButton);

        String[] insuranceSpinner = new String[]{"Through Employer", "Personal Insurance", "Other"};
        String[] paymentSpinner = new String[]{"Credit Card", "Cash", "Cheque"};

        ArrayAdapter<String> insuranceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, insuranceSpinner);
        ArrayAdapter<String> paymentAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, paymentSpinner);

        insuranceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        insurance.setAdapter(insuranceAdapter);
        payment.setAdapter(paymentAdapter);


    }

    public void saveButtonFunction(){
        clinicDB = FirebaseDatabase.getInstance().getReference("Clinic");


    }



    //@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }


//    public void onNothingSelected(AdapterView<?> arg0) {
//        String item = parent.getItemAtPosition(0).toString();
//        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
//    }
}
