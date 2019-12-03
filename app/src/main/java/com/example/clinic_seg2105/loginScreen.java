package com.example.clinic_seg2105;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginScreen extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemSelectedListener {

    private EditText email_login;
    private EditText password_login;
    private TextView goToCreateAccount;
    private Button loginButton;
    private Spinner signInSelectionSpinner;
    private TextView adminSignIn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        //Initialization
        email_login = (EditText) findViewById(R.id.emailLn);
        password_login = (EditText) findViewById(R.id.passwordLn);
        goToCreateAccount = (TextView) findViewById(R.id.goToCreateAccount);
        loginButton = (Button) findViewById(R.id.loginButton);
        adminSignIn = (TextView) findViewById(R.id.adminSignIn);

        signInSelectionSpinner = (Spinner) findViewById(R.id.signInSelectionSpinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.login_dropdown, R.layout.color_spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        signInSelectionSpinner.setAdapter(adapter);
        signInSelectionSpinner.setOnItemSelectedListener(this);

        goToCreateAccount.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        adminSignIn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                userLogin();
                break;
            case R.id.goToCreateAccount:
                Intent intent = new Intent(this, createAccount.class);
                startActivity(intent);
                break;
            case R.id.adminSignIn:
                Intent intentAdmin = new Intent(this, adminLogin.class);
                startActivity(intentAdmin);
        }

    }

    private void userLogin() {
        String temp_email = email_login.getText().toString().trim();
        String temp_pass = password_login.getText().toString().trim();

        if (TextUtils.isEmpty(temp_pass) || TextUtils.isEmpty(temp_email)) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_LONG).show();
        } else {

            if (signInSelectionSpinner.getSelectedItem().toString().trim().equals("Employee")) {
                loginAsEmployee();
            }

            if (signInSelectionSpinner.getSelectedItem().toString().trim().equals("Patient")) {
                loginAsPatient();
            }
        }


    }

    private void loginAsEmployee() {
        String temp_email = email_login.getText().toString().trim();
        String temp_pass = password_login.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(temp_email, temp_pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), employeeScreen.class));
                        } else {
                            Toast.makeText(loginScreen.this, "Incorrect email or password", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void loginAsPatient() {
        String temp_email = email_login.getText().toString().trim();
        String temp_pass = password_login.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(temp_email, temp_pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), patientScreen.class));
                        } else {
                            Toast.makeText(loginScreen.this, "Incorrect email or password", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}    