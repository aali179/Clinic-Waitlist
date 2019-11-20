package com.example.clinic_seg2105;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class employeeScreen extends AppCompatActivity {

    public static int screenChoice;

    private TextView welcomePrompt;

    private Button editClinicProfileButton;
    private Button addServiceButton;
    private Button deleteServiceButton;
    private Button setHoursButton;
    private Button checkHoursButton;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_screen);

        welcomePrompt = findViewById(R.id.employeeWelcome);

        editClinicProfileButton = (Button) findViewById(R.id.editClinicProfileButton);
        addServiceButton = (Button) findViewById(R.id.addServButton);
        deleteServiceButton = (Button) findViewById(R.id.deleteServButton);
        setHoursButton = (Button) findViewById(R.id.setHoursButton);
        checkHoursButton = (Button) findViewById(R.id.checkoutHoursButton);
        logoutButton = (Button) findViewById(R.id.logoutButton);

        addServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addService();
            }
        });
        deleteServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteService();
            }
        });
    }

    private void addService() {
        Intent intent = new Intent(this, addDeleteServiceScreen.class);
        screenChoice = 2;
        startActivity(intent);
    }

    private void deleteService() {
        Intent intent = new Intent(this, addDeleteServiceScreen.class);
        screenChoice = 3;
        startActivity(intent);
    }

    private void logoutButton(){
        Intent intent = new Intent(this, createAccount.class);
        startActivity(intent);
        finish();
    }
}
