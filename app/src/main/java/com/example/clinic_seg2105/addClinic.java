package com.example.clinic_seg2105;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addClinic extends AppCompatActivity implements View.OnClickListener {

    EditText clinicName;
    EditText clinicAddress;
    EditText phoneClinic;
    Spinner insurance;
    Spinner payment;
    Button addClinicButton;

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clinic);

        clinicName = (EditText) findViewById(R.id.clinicName);
        clinicAddress = (EditText) findViewById(R.id.clinicAddress);
        phoneClinic = (EditText) findViewById(R.id.phoneClinic);
        addClinicButton = (Button) findViewById(R.id.addClinicButton);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        spinnerSetUp();

        addClinicButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.addClinicButton:
                addClinicToDB();
                break;
        }
    }

    private void spinnerSetUp(){
        insurance = (Spinner) findViewById(R.id.insurance);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.insurance_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        insurance.setAdapter(adapter);

        payment = (Spinner) findViewById(R.id.payment);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.payment_methods, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        payment.setAdapter(adapter2);
    }

    private void addClinicToDB(){
        String name = clinicName.getText().toString().trim();
        String address = clinicAddress.getText().toString().trim();
        String phone = phoneClinic.getText().toString().trim();
        String insurance_clin = insurance.getSelectedItem().toString().trim();
        String payment_clin = payment.getSelectedItem().toString().trim();

        Clinic clinic = new Clinic(address, phone, name, insurance_clin, payment_clin);

        mDatabase.child("Clinics").child(name).setValue(clinic);

    }
}

