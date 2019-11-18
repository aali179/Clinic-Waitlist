package com.example.clinic_seg2105;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class patientScreen extends AppCompatActivity {

    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_screen);

        logoutButton = (Button) findViewById(R.id.logoutButton);
    }

    private void logoutButton(View v){
        Intent intent = new Intent(this, createAccount.class);
        startActivity(intent);
        finish();
    }
}
