package com.example.clinic_seg2105;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import static com.example.clinic_seg2105.GlobalVariables.*;


public class employeeScreen extends AppCompatActivity implements View.OnClickListener {

    private Button clinicOptionButton;
    private Button addServiceBTN;
    private Button deleteServiceBTN;
    private Button profileOptionButton;
    private Button logoutButton;
    //Clinic test = new Clinic("dsds", "asd", "asd","asd", "asd");

    public static int screenChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_screen);

        clinicOptionButton = (Button) findViewById(R.id.clinicOptionButon);
        addServiceBTN = (Button) findViewById(R.id.addServiceBTN);
        deleteServiceBTN = (Button) findViewById(R.id.deleteServiceBTN);
        profileOptionButton = (Button) findViewById(R.id.profileOptionButton);
        logoutButton = (Button) findViewById(R.id.logoutButton);

        clinicOptionButton.setOnClickListener(this);
        addServiceBTN.setOnClickListener(this);
        deleteServiceBTN.setOnClickListener(this);
        profileOptionButton.setOnClickListener(this);
        logoutButton.setOnClickListener(this);
        //clinic_vector.add(test);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clinicOptionButon:
                clinicOptionButtonFunc();
                break;
            case R.id.addServiceBTN:
                addServiceFunc();
                break;
            case R.id.deleteServiceBTN:
                deleteServiceFunc();
                break;
            case R.id.profileOptionButton:
                profileOptionEmpFunc();
                break;
            case R.id.logoutButton:
                logoutFunc();
                break;
        }

    }

    private void clinicOptionButtonFunc(){
        Intent intent = new Intent(this, clinicOptionEmployee.class);
        startActivity(intent);
    }

    private void addServiceFunc(){
        Intent intent = new Intent(this, addDeleteServiceScreen.class);
        screenChoice = 2;
        startActivity(intent);
    }

    private void deleteServiceFunc(){
        Intent intent = new Intent(this, addDeleteServiceScreen.class);
        screenChoice = 3;
        startActivity(intent);
    }

    private void profileOptionEmpFunc(){
        Intent intent = new Intent(this, editEmployeeProfile.class);
        startActivity(intent);
    }

    private void logoutFunc(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

