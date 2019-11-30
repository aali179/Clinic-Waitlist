package com.example.clinic_seg2105;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class patientScreen extends AppCompatActivity implements View.OnClickListener{

    private Button searchByClinicName, searchByServices, isOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_screen);

        searchByClinicName = (Button) findViewById(R.id.searchByClinicName);
        searchByServices = (Button) findViewById(R.id.searchByServices);
        isOpen = (Button) findViewById(R.id.isOpen);

        searchByClinicName.setOnClickListener(this);
        searchByServices.setOnClickListener(this);
        isOpen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchByClinicName:
                searchByClinicNameFunc();
                break;
            case R.id.searchByServices:
                searchByServicesFunc();
                break;
            case R.id.isOpen:
                isOpenFunc();
                break;
        }
    }


    private void searchByClinicNameFunc(){
        Intent intent = new Intent(patientScreen.this, patientSearchClinic.class);
        startActivity(intent);
    }

    private void searchByServicesFunc(){
        Intent intent = new Intent(patientScreen.this, patientSearchService.class);
        startActivity(intent);
    }

    private void isOpenFunc(){
        Intent intent = new Intent(patientScreen.this, patientIsOpen.class);
        startActivity(intent);

    }
}