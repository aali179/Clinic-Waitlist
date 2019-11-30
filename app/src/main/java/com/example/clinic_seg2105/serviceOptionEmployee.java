package com.example.clinic_seg2105;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class serviceOptionEmployee extends AppCompatActivity implements View.OnClickListener {

    private Spinner serviceOptionForEmployee;
    private Button addServiceEmployee;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_option_employee);

        serviceOptionForEmployee = (Spinner) findViewById(R.id.serviceOptionForEmployee);
        addServiceEmployee = (Button) findViewById(R.id.addServiceEmployee);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        addServiceEmployee.setOnClickListener(this);

        populateSpinner();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.addServiceEmployee:
                addServiceToEmployeeFunction();
                break;
        }

    }

    private void populateSpinner(){
        mDatabase.child("Services").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> servicesList = new ArrayList<String>();

                for (DataSnapshot servSnapshot: dataSnapshot.getChildren()){
                    String servName = servSnapshot.child("name").getValue(String.class);
                    servicesList.add(servName);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(serviceOptionEmployee.this, android.R.layout.simple_spinner_dropdown_item, servicesList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                serviceOptionForEmployee.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void addServiceToEmployeeFunction(){

        String selectedService = serviceOptionForEmployee.getSelectedItem().toString().trim();

        mDatabase.child("Employees").child(mUser.getUid()).child("EmployeeServices").push().setValue(selectedService);

    }


}