package com.example.clinic_seg2105;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class employeeCreateAccount extends AppCompatActivity implements View.OnClickListener {

    private EditText clinicName, clinicEmail, clinicNumber, clinicAddress, clinicPassword;
    private Spinner insuranceSpinner, paymentSpinner;
    private Button createEmployeeAccount;

    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_create_account);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        initializer();

        spinnerSetUp();

        createEmployeeAccount.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.createEmployeeAccount:
                registerEmployee();
                break;
        }


    }

    private void initializer(){
        clinicName = (EditText) findViewById(R.id.clinicName);
        clinicEmail = (EditText) findViewById(R.id.clinicEmail);
        clinicNumber = (EditText) findViewById(R.id.clinicNumber);
        clinicAddress = (EditText) findViewById(R.id.clinicAddress);
        clinicPassword = (EditText) findViewById(R.id.clinicPassword);
        createEmployeeAccount = (Button) findViewById(R.id.createEmployeeAccount);
    }

    private void spinnerSetUp(){
        insuranceSpinner = (Spinner) findViewById(R.id.insuranceSpinner);
        paymentSpinner = (Spinner) findViewById(R.id.paymentSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.insurance_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        insuranceSpinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.payment_methods, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentSpinner.setAdapter(adapter2);
    }

    private void registerEmployee(){
        String temp_name = clinicName.getText().toString().trim();
        String temp_email = clinicEmail.getText().toString().trim();
        String temp_number = clinicNumber.getText().toString().trim();
        String temp_address = clinicAddress.getText().toString().trim();
        String temp_insurance = insuranceSpinner.getSelectedItem().toString().trim();
        String temp_payment = paymentSpinner.getSelectedItem().toString().trim();
        String temp_password = clinicPassword.getText().toString().trim();
        final Employee employee = new Employee(temp_name, temp_email, temp_number, temp_address, temp_insurance, temp_payment, temp_password);

        mAuth.createUserWithEmailAndPassword(temp_email, temp_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    mDatabase.getReference("Employees")
                            .child(mAuth.getCurrentUser().getUid()).setValue(employee)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        finish();
                                        startActivity(new Intent(getApplicationContext(), loginScreen.class));
                                    } else {
                                        Toast.makeText(employeeCreateAccount.this, "Firebase Data Error", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(employeeCreateAccount.this, "Password is too short!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
