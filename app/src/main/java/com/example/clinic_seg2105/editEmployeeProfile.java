package com.example.clinic_seg2105;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class editEmployeeProfile extends AppCompatActivity {

    private EditText phoneNumber;
    private Spinner nameClinic;
    private EditText locationAddress;
    private Spinner insurance;
    private Spinner payment;
    private Button saveButton;

    private String nameOfClinic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee_profile);

        phoneNumber = (EditText) findViewById(R.id.phoneNumberEntry);
        nameClinic = (Spinner) findViewById(R.id.clinicSpinner);
        locationAddress = (EditText) findViewById(R.id.locationEntry);
        insurance = (Spinner) findViewById(R.id.insuranceSpinner);
        payment = (Spinner) findViewById(R.id.paymentSpinner);
        saveButton = (Button) findViewById(R.id.saveInfoButton);

        String[] insuranceSpinner = new String[]{"Through Employer", "Personal Insurance", "Other"};
        String[] paymentSpinner = new String[]{"Credit Card", "Cash", "Cheque"};

        ArrayAdapter<String> insuranceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, insuranceSpinner);
        ArrayAdapter<String> paymentAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, paymentSpinner);

        insuranceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        insurance.setAdapter(insuranceAdapter);
        payment.setAdapter(paymentAdapter);

        loadClinics();

        //ClinicSpinner Initialization
        nameClinic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Service selected = (Service) parent.getItemAtPosition(position);
                //mTVservice.setText(selected.getService());

                String text = parent.getItemAtPosition(position).toString();
                nameOfClinic = text;
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
                autoFillFields(nameOfClinic);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile();
            }
        });
    }

    private void loadClinics(){
        ArrayAdapter<String> spinnerAdapter;
        ClinicRepo db = new ClinicRepo(getApplicationContext());
        List<String> clinics = db.getAll();
        spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, clinics);
        nameClinic.setAdapter(spinnerAdapter);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    private void autoFillFields(String clnc) {
        ClinicRepo db = new ClinicRepo(getApplicationContext());
        List<String> clinicInfo = db.getAutofill(clnc);
        locationAddress.setText(clinicInfo.get(0));
        phoneNumber.setText(clinicInfo.get(1));
    }

    private void editProfile() {

    }


}
