package com.example.clinic_seg2105;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static com.example.clinic_seg2105.GlobalVariables.*;

public class clinicOptionEmployee extends AppCompatActivity {

    private Spinner spinner;
    private Button test;
    private Button addClinic;
    private FirebaseAuth mAuth;
    private FirebaseUser mEmp;
    private DatabaseReference mDatabase;
    private String clinic_name_checker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_option_employee);

        mAuth = FirebaseAuth.getInstance();
        mEmp = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        spinner = (Spinner) findViewById(R.id.clinicOptionSpinner);
        test = (Button) findViewById(R.id.lel);
        addClinic = (Button) findViewById(R.id.addClinic);
        employeeClinicChecker();
        populateSpinnerWithClinics();

    }

    private void employeeClinicChecker(){

        mDatabase.child("Users")
                .child(mEmp.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);

                        clinic_name_checker = user.getClinic();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        if (clinic_name_checker.equals("")){
            populateSpinnerWithClinics();
        } else{
            Intent intent = new Intent(this, serviceOptionEmployee.class);
            startActivity(intent);
        }

    }

    private void populateSpinnerWithClinics(){

        if (clinic_vector.size() == 0){
            Toast.makeText(this, "No Clinic", Toast.LENGTH_LONG).show();
            test.setText("NO CLINICS");

        } else {
            List<String> spinnerArray = new ArrayList<String>();

            for (int i = 0; i < clinic_vector.size(); i++) {
              //  String name = (clinic_vector.elementAt(i)).getNameClinic().trim();
                //spinnerArray.add(name);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, spinnerArray);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(adapter);
        }
    }

    public void addClinicEmp(View v){
        final String clinicNameFromSpinner = spinner.getSelectedItem().toString().trim();

        mDatabase.child("Users").child(mEmp.getUid()).child("clinic").setValue(clinicNameFromSpinner);

    }
}
