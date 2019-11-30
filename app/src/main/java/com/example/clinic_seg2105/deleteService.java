package com.example.clinic_seg2105;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class deleteService extends AppCompatActivity implements View.OnClickListener{

    private Spinner deleteServiceSpinner;
    private Button deleteDeleteServiceButton;
    private Button cancelDeleteServiceButton;

    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_service);

        ref = FirebaseDatabase.getInstance().getReference();

        spinnerSetUp();
        deleteDeleteServiceButton = (Button) findViewById(R.id.deleteDeleteServiceButton);
        cancelDeleteServiceButton = (Button) findViewById(R.id.cancelDeleteServiceButton);

        deleteDeleteServiceButton.setOnClickListener(this);
        cancelDeleteServiceButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.deleteDeleteServiceButton:
                deleteServiceFromDatabase();
                break;
            case R.id.cancelDeleteServiceButton:
                finish();
                Intent intent = new Intent(getApplicationContext(), adminScreen.class);
                startActivity(intent);
        }

    }

    private void spinnerSetUp() {
        deleteServiceSpinner = (Spinner) findViewById(R.id.deleteServiceSpinner);

        ref.child("Services").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> servicesList = new ArrayList<String>();

                for (DataSnapshot servSnapshot: dataSnapshot.getChildren()){
                    String servName = servSnapshot.child("name").getValue(String.class);
                    servicesList.add(servName);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(deleteService.this, android.R.layout.simple_spinner_dropdown_item, servicesList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                deleteServiceSpinner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void deleteServiceFromDatabase(){
        String serviceToBeDeleted = deleteServiceSpinner.getSelectedItem().toString().trim();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Services");
        ref.child(serviceToBeDeleted).removeValue();

    }
}
