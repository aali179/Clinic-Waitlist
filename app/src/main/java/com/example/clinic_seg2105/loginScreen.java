package com.example.clinic_seg2105;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginScreen extends AppCompatActivity implements View.OnClickListener{
    private EditText email_login;
    private EditText password_login;
    private TextView goToCreateAccount;
    private Button loginButton;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mDatabase;

    public static String activeUser;

    // Calling an instance of the database class that stores all the information
    EmployeeDBHelper myDb;

    EmployeeRepo repo = new EmployeeRepo(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        email_login = (EditText) findViewById(R.id.emailLn);
        password_login = (EditText) findViewById(R.id.passwordLn);
        goToCreateAccount = (TextView) findViewById(R.id.goToCreateAccount);
        loginButton = (Button) findViewById(R.id.loginButton);

        goToCreateAccount.setOnClickListener(this);
        loginButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginButton:
                userLogin();
                break;
            case R.id.goToCreateAccount:
                Intent intent = new Intent(this, createAccount.class);
                startActivity(intent);
                break;
        }
    }

    public void userLogin(){

        String temp_email = email_login.getText().toString().trim();
        String temp_pass = password_login.getText().toString().trim();

        if (TextUtils.isEmpty(temp_pass)){
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(temp_email)){
            Toast.makeText(this, "Please enter a email", Toast.LENGTH_LONG).show();
            return;
        }

        if (temp_email.equals("admin@gmail.com") && temp_pass.equals("admin1234")){
            finish();
            Intent intent = new Intent(this, adminScreen.class);
            startActivity(intent);
        }

        if (repo.login(temp_email, temp_pass) == true) {
            Intent intent = new Intent(this, employeeScreen.class);
            activeUser = temp_email;
            loginButton.setText("Signing In");
            startActivity(intent);
        }

    }

    private void openEmployeeScreen(){
        finish();
        Intent intent = new Intent(this, employeeScreen.class);
        startActivity(intent);
    }

    private void openPatientScreen(){

    }

}
