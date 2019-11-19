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
        // firebaseAuth = FirebaseAuth.getInstance();
        //databaseReference = FirebaseDatabase.getInstance().getReference();

        createAccountButton.setOnClickListener(this);
        signInLink.setOnClickListener(this);
    }

    private void registerUser() {
        String memberRole = spinner.getSelectedItem().toString().trim();
        String temp_name = name.getText().toString().trim();
        String temp_email = email.getText().toString().trim();
        String temp_pass = password.getText().toString().trim();

        if (TextUtils.isEmpty(temp_name) || TextUtils.isEmpty(temp_email) || TextUtils.isEmpty(temp_pass)) {
            Toast.makeText(this, "Please Enter All Fields", Toast.LENGTH_LONG).show();
            return;
        }

        //insert new employee
        Employee employee = new Employee();
        employee.setName(temp_name);
        employee.setUsername(temp_email);
        employee.setPassword(temp_pass);
        repo.insert(employee);
    }
}

        /*firebaseAuth.createUserWithEmailAndPassword(temp_email, temp_pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(createAccount.this, "Successfully Registered", Toast.LENGTH_LONG).show();

                            createUserFirebase();

                            Intent intent = new Intent(getApplicationContext(), loginScreen.class);
                            startActivity(intent);
                            finish();

                        } else{
                            Toast.makeText(createAccount.this, "Hello", Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }*/


   /* private void createUserFirebase(){
        String memberRole = spinner.getSelectedItem().toString().trim();
        String temp_name = name.getText().toString().trim();
        String temp_email = email.getText().toString().trim();
        String temp_pass = password.getText().toString().trim();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseUserReference = FirebaseDatabase.getInstance().getReference("Users");

        FirebaseUser fUser = firebaseAuth.getCurrentUser();
        String id = fUser.getUid();
        User user = new User(id, temp_name, temp_email, memberRole);
        databaseUserReference.child(id).setValue(user);
        Toast.makeText(this, "Finished", Toast.LENGTH_LONG).show();
        finish();
    }*/



