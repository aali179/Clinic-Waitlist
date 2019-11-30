package com.example.clinic_seg2105;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class viewEmployeeProfile extends AppCompatActivity {

    private TextView viewEmployeeName,viewEmployeeEmail, viewEmployeeNumber, viewEmployeeAddress,viewEmployeeInsurance, viewEmployeePayment;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee_profile);

        viewEmployeeName = (TextView) findViewById(R.id.viewEmployeeName);
        viewEmployeeEmail = (TextView) findViewById(R.id.viewEmployeeEmail);
        viewEmployeeNumber = (TextView) findViewById(R.id.viewEmployeeNumber);
        viewEmployeeAddress = (TextView) findViewById(R.id.viewEmployeeAddress);
        viewEmployeeInsurance = (TextView) findViewById(R.id.viewEmployeeInsurance);
        viewEmployeePayment = (TextView) findViewById(R.id.viewEmployeePayment);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        if (mUser == null){
            finish();
            startActivity(new Intent(this, createAccount.class));
        } else {
            populateEmployeeInformation();
        }
    }

    private void populateEmployeeInformation(){

        mDatabase.child("Employees")
                .child(mUser.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Employee emp_display = dataSnapshot.getValue(Employee.class);

                        String name = emp_display.getName();
                        String email = emp_display.getEmail();
                        String number = emp_display.getNumber();
                        String addresss = emp_display.getAddress();
                        String insurance = emp_display.getInsurance();
                        String payment = emp_display.getPayment();

                        viewEmployeeName.setText(name);
                        viewEmployeeEmail.setText(email);
                        viewEmployeeNumber.setText(number);
                        viewEmployeeAddress.setText(addresss);
                        viewEmployeeInsurance.setText(insurance);
                        viewEmployeePayment.setText(payment);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }
}
