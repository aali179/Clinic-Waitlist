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

import org.w3c.dom.Text;

public class createAccount extends AppCompatActivity implements View.OnClickListener {

    // Calling an instance of the database class that stores all the information
    EmployeeDBHelper myDb;

    EmployeeRepo repo = new EmployeeRepo(this);

    private EditText name, email, password, passwordRe;
    private TextView signInLink;
    private Spinner spinner;
    private Button createAccountButton;


    //private FirebaseAuth firebaseAuth;
    //private DatabaseReference databaseReference;
    //private DatabaseReference databaseUserReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        spinnerSetUp();

        initializer();

        //loadDatabase();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createAccountButton:
                registerUser();
                break;
            case R.id.signInLink:
                Intent intent = new Intent(getApplicationContext(), loginScreen.class);
                finish();
                break;
        }
    }

    private void spinnerSetUp() {
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.login_dropdown, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void initializer() {
        name = (EditText) findViewById(R.id.fullName);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        passwordRe = (EditText) findViewById(R.id.passwordRe);
        createAccountButton = (Button) findViewById(R.id.createAccountButton);
        signInLink = (TextView) findViewById(R.id.signInLink);
       // mAuth = FirebaseAuth.getInstance();
        //mDatabase = FirebaseDatabase.getInstance();

        createAccountButton.setOnClickListener(this);
        signInLink.setOnClickListener(this);
    }

    private void registerUser() {
        Intent intent = new Intent(this, loginScreen.class);


        String memberRole = spinner.getSelectedItem().toString().trim();
        String temp_name = name.getText().toString().trim();
        String temp_email = email.getText().toString().trim();
        String temp_pass = password.getText().toString().trim();

       if (TextUtils.isEmpty(temp_name) || TextUtils.isEmpty(temp_email) || TextUtils.isEmpty(temp_pass)) {
            Toast.makeText(this, "Please Enter All Fields", Toast.LENGTH_LONG).show();
            return;

        }

        //if (memberRole == "Employee") {
            //insert new employee
            Employee employee = new Employee();
            employee.setName(temp_name);
            employee.setUsername(temp_email);
            employee.setPassword(temp_pass);
            employee.setClinic(null);
            employee.setAddress(null);
            employee.setPhone(null);
            employee.setPayment(null);
            employee.setInsurance(null);
            repo.insert(employee);
        //}

        startActivity(intent);

    }

}
