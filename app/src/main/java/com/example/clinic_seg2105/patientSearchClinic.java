package com.example.clinic_seg2105;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class patientSearchClinic extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.hospital_clinic.EXTRA_TEXT";
    public static final String CLINIC_ID = "com.example.hospital_clinic.CLINIC_ID";

    private ListView listViewClinic;
    private DatabaseReference mRef;

    private String id_of_clinic;

    ArrayList<String> clinicName= new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_search_clinic);

        listViewClinic = (ListView) findViewById(R.id.listViewClinic);
        mRef = FirebaseDatabase.getInstance().getReference("Employees");

        populateListView();

        listViewClinic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = clinicName.get(position);
                goToBooking(name);
            }
        });
    }

    private void populateListView(){
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            ListAdapter adapter = new ArrayAdapter<String>(patientSearchClinic.this, android.R.layout.simple_list_item_1, clinicName);
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String n = ds.child("name").getValue(String.class);
                    clinicName.add(n);
                }

                listViewClinic.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void goToBooking(final String name){

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    if ( (ds.child("name").getValue(String.class)).equals(name) ){
                        String emp_id = ds.getKey();
                        id_of_clinic = emp_id;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        startIntent(name);

    }

    private void startIntent(String name){

        Intent intent = new Intent(getApplicationContext(), patientBookingRating.class);
        intent.putExtra(EXTRA_TEXT, name);
        intent.putExtra(CLINIC_ID, id_of_clinic);
        startActivity(intent);

    }
}