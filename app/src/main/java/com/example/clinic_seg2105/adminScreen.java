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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_screen);

        addService = (Button) findViewById(R.id.addServiceButton);
        editService = (Button) findViewById(R.id.editServiceButton);
        deleteService = (Button) findViewById(R.id.deleteServiceButton);

        EditData();

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

    public void EditData() {
        editService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditDialog();
            }
        });
    }

    public void openAddDialog(){
        addDialog ad = new addDialog();
        ad.show(getSupportFragmentManager(), "Add");
    }

    public void openEditDialog() {
        Intent intent = new Intent(this, editServiceScreen.class);

        startActivity(intent);
    }
}
