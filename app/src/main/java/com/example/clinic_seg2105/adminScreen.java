package com.example.clinic_seg2105;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;

public class adminScreen extends AppCompatActivity implements View.OnClickListener {
    private Button addService;
    private Button editService;
    private Button deleteService;
    private Button addClinic;

    FirebaseDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_screen);

        addService = (Button) findViewById(R.id.addServiceButton);
        editService = (Button) findViewById(R.id.editServiceButton);
        deleteService = (Button) findViewById(R.id.deleteServiceButton);
        addClinic = (Button) findViewById(R.id.addClinicButton);

        mDatabase = FirebaseDatabase.getInstance();


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
        addServiceDialog aSD = new addServiceDialog();
        aSD.show(getSupportFragmentManager(), "Add Service");
    }

    private void editServiceActivity(){
        Intent intent = new Intent (getApplicationContext(), editServiceScreen.class);
        startActivity(intent);

    }

    private void deleteServiceActivity(){
        Intent intent = new Intent(getApplicationContext(), deleteService.class);
        startActivity(intent);
    }

    private void addClinicActivity(){
        Intent intent = new Intent(getApplicationContext(), addClinic.class);
        startActivity(intent);
    }
}
