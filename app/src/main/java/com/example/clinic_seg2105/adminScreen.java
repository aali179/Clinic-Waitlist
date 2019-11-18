package com.example.clinic_seg2105;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class adminScreen extends AppCompatActivity {
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
        addClinic = (Button)findViewById(R.id.addClinicButton);

        AddData();
        EditData();
        DeleteData();
        AddClinic();

        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddDialog();
            }
        });

        editService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditDialog();
            }
        });
//
//        deleteService.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteServiceDialog();
//            }
//        });

    }

    public void AddData() {
        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddDialog();
            }
        });
    }

    public void EditData() {
        editService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditDialog();
            }
        });
    }
    public void DeleteData() {
        deleteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeleteDialog();
            }
        });
    }

    public void AddClinic(){
        addClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddClinicDialog();
            }
        });    }

    public void openAddDialog(){
        Intent intent = new Intent(this, editServiceScreen.class);
        screenChoice = 1;
        startActivity(intent);

    public void openAddDialog(){
        addDialog ad = new addDialog();
        ad.show(getSupportFragmentManager(), "Add");

    }

    public void openEditDialog() {
        Intent intent = new Intent(this, editServiceScreen.class);
        screenChoice = 2;
        startActivity(intent);
    }

    public void openDeleteDialog() {
        Intent intent = new Intent(this, editServiceScreen.class);
        screenChoice = 3;
        startActivity(intent);
    }

    public void openAddClinicDialog(){
        Intent intent = new Intent(this, addClinicScreen.class);
        startActivity(intent);
    }
}
