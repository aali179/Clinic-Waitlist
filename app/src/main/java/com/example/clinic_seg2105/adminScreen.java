package com.example.clinic_seg2105;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class adminScreen extends AppCompatActivity implements View.OnClickListener {
    private Button addService;
    private Button editService;
    private Button deleteService;
    private Button addClinic;

    public static Integer screenChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_screen);

        addService = (Button) findViewById(R.id.addServiceButton);
        editService = (Button) findViewById(R.id.editServiceButton);
        deleteService = (Button) findViewById(R.id.deleteServiceButton);
        addClinic = (Button) findViewById(R.id.addClinicButton);

        addService.setOnClickListener(this);
        editService.setOnClickListener(this);
        deleteService.setOnClickListener(this);
        addClinic.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.addServiceButton:
                addServiceActivity();
                break;
            case R.id.editServiceButton:
                editServiceActivity();
                break;
            case R.id.deleteServiceButton:
                deleteServiceActivity();
                break;
            case R.id.addClinicButton:
                addClinicActivity();
                break;
        }

    }

    private void addServiceActivity(){
        Intent intent = new Intent(this, editServiceScreen.class);
        screenChoice = 1;
        startActivity(intent);
    }

    private void editServiceActivity(){
        Intent intent = new Intent(this, editServiceScreen.class);
        screenChoice = 2;
        startActivity(intent);

    }

    private void deleteServiceActivity(){
        Intent intent = new Intent(this, editServiceScreen.class);
        screenChoice = 3;
        startActivity(intent);
    }

    private void addClinicActivity(){
        Intent intent = new Intent(this, addClinicScreen.class);
        startActivity(intent);
    }


    public void openAddDialog() {
        Intent intent = new Intent(this, editServiceScreen.class);
        screenChoice = 1;
        startActivity(intent);
    }

}
