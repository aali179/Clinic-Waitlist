package com.example.clinic_seg2105;

import androidx.appcompat.app.AppCompatActivity;

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

        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddDialog();
            }
        });

//        editService.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openEditDialog();
//            }
//        });
//
//        deleteService.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteServiceDialog();
//            }
//        });

    }

    public void openAddDialog(){

    }
}
