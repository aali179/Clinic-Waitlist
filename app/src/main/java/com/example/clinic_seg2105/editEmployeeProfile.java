package com.example.clinic_seg2105;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    private String paymentType;
    private String insuranceType;

    ArrayAdapter<String> spinnerAdapter;

    private String activeUser = loginScreen.activeUser;

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

        final ArrayAdapter<String> insuranceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, insuranceSpinner);
        final ArrayAdapter<String> paymentAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, paymentSpinner);

        insuranceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        insurance.setAdapter(insuranceAdapter);
        payment.setAdapter(paymentAdapter);

        loadClinics();
        //loadDefaults(paymentAdapter, insuranceAdapter);

        //ClinicSpinner Initialization
        nameClinic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Service selected = (Service) parent.getItemAtPosition(position);
                //mTVservice.setText(selected.getService());

                String text = parent.getItemAtPosition(position).toString();
                nameOfClinic = text;
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
                autoFillFields(nameOfClinic, paymentAdapter, insuranceAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //PaymentSpinner Initialization
        payment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Service selected = (Service) parent.getItemAtPosition(position);
                //mTVservice.setText(selected.getService());

                String text = parent.getItemAtPosition(position).toString();
                paymentType = text;
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //PaymentSpinner Initialization
        insurance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Service selected = (Service) parent.getItemAtPosition(position);
                //mTVservice.setText(selected.getService());

                String text = parent.getItemAtPosition(position).toString();
                insuranceType = text;
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
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
        ClinicRepo db = new ClinicRepo(getApplicationContext());
        List<String> clinics = db.getAll();
        spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, clinics);
        nameClinic.setAdapter(spinnerAdapter);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    private void autoFillFields(String clnc, ArrayAdapter<String> pay, ArrayAdapter<String> ins) {
        ClinicRepo db = new ClinicRepo(getApplicationContext());
        List<String> clinicInfo = db.getAutofill(clnc);
        locationAddress.setText(clinicInfo.get(0));
        phoneNumber.setText(clinicInfo.get(1));
        int paymentSpinnerPosition = pay.getPosition(clinicInfo.get(2));
        payment.setSelection(paymentSpinnerPosition);
        int insuranceSpinnerPosition = ins.getPosition(clinicInfo.get(3));
        insurance.setSelection(insuranceSpinnerPosition);
    }

    private void loadDefaults(ArrayAdapter<String> pay, ArrayAdapter<String> ins) {
        // LOAD INITIAL DATA (IF ALREADY SET)
        EmployeeRepo repo = new EmployeeRepo(getApplicationContext());

        // load initial profile info
        String clinic = repo.getClinic(activeUser);
        String address = repo.getAddress(activeUser);
        String phone = repo.getPhone(activeUser);
        paymentType = repo.getPayment(activeUser);
        insuranceType = repo.getInsurance(activeUser);

        // set EditText/Spinner defaults
        int clinicSpinnerPosition = spinnerAdapter.getPosition(clinic);
        nameClinic.setSelection(clinicSpinnerPosition);
        locationAddress.setText(address);
        phoneNumber.setText(phone);
        int paymentSpinnerPosition = pay.getPosition(paymentType);
        payment.setSelection(paymentSpinnerPosition);
        int insuranceSpinnerPosition = ins.getPosition(insuranceType);
        insurance.setSelection(insuranceSpinnerPosition);
    }


    private void editProfile() {
        Intent intent = new Intent(this, employeeScreen.class);

        EmployeeRepo repo = new EmployeeRepo(getApplicationContext());

        // save old profile info
        String name = repo.getName(activeUser);
        String username = activeUser;
        String password = repo.getPassword(activeUser);

        // delete old profile
        repo.delete(activeUser);

        // insert new (updated) profile
        Employee employee = new Employee();
        employee.setName(name);
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setAddress(locationAddress.getText().toString());
        employee.setPhone(phoneNumber.getText().toString());
        employee.setClinic(nameOfClinic);
        employee.setPayment(paymentType);
        employee.setInsurance(insuranceType);
        repo.insert(employee);

        startActivity(intent);
    }


}
