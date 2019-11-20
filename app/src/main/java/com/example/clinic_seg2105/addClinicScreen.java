package com.example.clinic_seg2105;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class addClinicScreen extends AppCompatActivity {


    ClinicDBHelper myDb;

    ClinicRepo repo = new ClinicRepo(this);

    private EditText phoneNumber;
    private EditText nameClinic;
    private EditText emailAddress;
    private Spinner insurance;
    private Spinner payment;
    private Button saveButton;

    public static String insurance_type;
    public static String payment_type;
   // private DatabaseReference clinicDB;

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

        insurance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String text = parent.getItemAtPosition(position).toString();
                insurance_type = text;
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        payment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String text = parent.getItemAtPosition(position).toString();
                payment_type = text;
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        saveButton = (Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createClinic();
            }
        });



    }

    public void createClinic(){
        //clinicDB = FirebaseDatabase.getInstance().getReference("Clinic");

        Intent intent = new Intent(this, adminScreen.class);

        //deletes old service
        //repo.delete(service_type);

        //insert new service
        Clinic clinic = new Clinic();
        clinic.setName(nameClinic.getText().toString());
        clinic.setAddress(emailAddress.getText().toString());
        clinic.setPhone(phoneNumber.getText().toString());
        clinic.setPayment(payment_type);
        clinic.setInsurance(insurance_type);
        repo.insert(clinic);

        startActivity(intent);
    }

}
