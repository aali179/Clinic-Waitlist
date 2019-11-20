package com.example.clinic_seg2105;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class serviceOptionEmployee extends AppCompatActivity {

    private Button addServiceToClinic;
    ServiceDBHelper myDb;
    ServiceRepo repo = new ServiceRepo(this);

    //repo.getAll();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_option_employee);

        addServiceToClinic = (Button) findViewById(R.id.addServiceButton);

        loadServices();

        addServiceToClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(serviceOptionEmployee.this);
                mBuilder.setTitle("Select service to add to your clinic");
            }
        });
    }

    private void loadServices(){

    }
}
