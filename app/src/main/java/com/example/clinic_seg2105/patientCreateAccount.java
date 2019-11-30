package com.example.clinic_seg2105;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class patientCreateAccount extends AppCompatActivity implements View.OnClickListener{

    private EditText fullName, email, password, passwordRe;
    private TextView signInLink;
    private Button createPatientAccount;

    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_create_account);

        initializer();


    }

    private void initializer() {
        fullName = (EditText) findViewById(R.id.fullName);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        passwordRe = (EditText) findViewById(R.id.passwordRe);
        createPatientAccount = (Button) findViewById(R.id.createPatientAccount);
        signInLink = (TextView) findViewById(R.id.signInLink);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        createPatientAccount.setOnClickListener(this);
        signInLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createPatientAccount:
                registerPatient();
                break;
            case R.id.signInLink:
                Intent intent = new Intent(getApplicationContext(), loginScreen.class);
                finish();
                break;
        }
    }

    private void registerPatient(){
        String temp_name = fullName.getText().toString().trim();
        String temp_email = email.getText().toString().trim();
        String temp_pass = password.getText().toString().trim();

        final Patient patient = new Patient(temp_name, temp_email);

        mAuth.createUserWithEmailAndPassword(temp_email, temp_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    mDatabase.getReference("Patients")
                            .child(mAuth.getCurrentUser().getUid()).setValue(patient)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        finish();
                                        startActivity(new Intent(getApplicationContext(), loginScreen.class));
                                    } else {
                                        Toast.makeText(patientCreateAccount.this, "Firebase Data Error", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(patientCreateAccount.this, "Password is too short!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
