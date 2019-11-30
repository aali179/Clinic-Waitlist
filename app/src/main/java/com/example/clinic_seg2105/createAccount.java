package com.example.clinic_seg2105;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;


import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class createAccount extends AppCompatActivity implements View.OnClickListener {


    private Button createNewEmployee, createNewPatient, signInAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);


        createNewEmployee = (Button) findViewById(R.id.createNewEmployee);
        createNewPatient = (Button) findViewById(R.id.createNewPatient);
        signInAdmin = (Button) findViewById(R.id.signInAdmin);

        createNewEmployee.setOnClickListener(this);
        createNewPatient.setOnClickListener(this);
        signInAdmin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.createNewEmployee:
                createNewEmployeeFunction();
                break;
            case R.id.createNewPatient:
                createNewPatientFunction();
                break;
            case R.id.signInAdmin:
                signInAdminFunction();
                break;
        }
    }


    private void createNewEmployeeFunction(){
        finish();
        Intent intent = new Intent(getApplicationContext(), employeeCreateAccount.class);
        startActivity(intent);
    }

    private void createNewPatientFunction(){
        finish();
        Intent intent = new Intent(getApplicationContext(), patientCreateAccount.class);
        startActivity(intent);
    }

    private void signInAdminFunction(){

    }
}
