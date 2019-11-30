package com.example.clinic_seg2105;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class patientSearchService extends AppCompatActivity implements View.OnClickListener{

    private Spinner searchServiceSpinner;
    private ListView listViewService;
    private Button searchServiceButton;

    public static final String EXTRA_TEXT = "com.example.hospital_clinic.EXTRA_TEXT";
    public static final String CLINIC_ID = "com.example.hospital_clinic.CLINIC_ID";

    ArrayList<String> clinicList = new ArrayList<String>();

    private String id_of_clinic;

    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_search_service);

        ref = FirebaseDatabase.getInstance().getReference();

        searchServiceSpinner = (Spinner) findViewById(R.id.searchServiceSpinner);
        listViewService = (ListView) findViewById(R.id.listViewService);
        searchServiceButton = (Button) findViewById(R.id.searchServiceButton);

        populateSpinner();

        searchServiceButton.setOnClickListener(this);

        listViewService.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = clinicList.get(position);
                goToBooking(name);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchServiceButton:
                scavange();
                break;
        }
    }


    private void populateSpinner(){

        ref.child("Employees").addValueEventListener(new ValueEventListener() {
            ArrayList<String> servicesList = new ArrayList<String>();
            ArrayAdapter adapter = new ArrayAdapter<String>(patientSearchService.this, android.R.layout.simple_spinner_dropdown_item, servicesList);
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    for (DataSnapshot ds2 : ds.getChildren()){
                        if (ds2.getKey().equals("EmployeeServices")){
                            for (DataSnapshot ds3 : ds2.getChildren()){
                                String s = ds3.getValue(String.class);
                                servicesList.add(s);
                            }
                        }

                    }
                }
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                searchServiceSpinner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void scavange(){
        final String service = searchServiceSpinner.getSelectedItem().toString().trim();

        ref.child("Employees").addValueEventListener(new ValueEventListener() {

            ListAdapter adapter = new ArrayAdapter<String>(patientSearchService.this, android.R.layout.simple_list_item_1, clinicList);
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    for (DataSnapshot ds2 : ds.getChildren()){
                        if (ds2.getKey().equals("EmployeeServices")){
                            for (DataSnapshot ds3 : ds2.getChildren()){

                                if (service.equals(ds3.getValue(String.class))){
                                    String clinic_name = ds.child("name").getValue(String.class);
                                    clinicList.add(clinic_name);
                                }
                            }
                        }

                    }
                }

                ArrayList<String> no_dupe = new ArrayList<String>();

                for (String element : clinicList){

                    if (!no_dupe.contains(element)){
                        no_dupe.add(element);
                    }
                }

                listViewService.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void goToBooking(final String name){

        ref.child("Employees").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    if ( (ds.child("name").getValue(String.class)).equals(name) ){
                        id_of_clinic = ds.getKey();
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