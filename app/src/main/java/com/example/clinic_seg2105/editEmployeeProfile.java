
package com.example.clinic_seg2105;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class editEmployeeProfile extends AppCompatActivity implements View.OnClickListener {

    private EditText clinicNameUpdate, clinicEmailUpdate, clinicNumberUpdate, clinicAddressUpdate, clinicPasswordUpdate;
    private Spinner insuranceSpinnerUpdate, paymentSpinnerUpdate;
    private Button updateEmployee;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee_profile);

        initializer();
        spinnerSetUp();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.updateEmployee:
                updateEmployeeFunction();
                break;
        }

    }

    private void initializer(){
        clinicNameUpdate = (EditText) findViewById(R.id.clinicNameUpdate);
        clinicEmailUpdate = (EditText) findViewById(R.id.clinicEmailUpdate);
        clinicNumberUpdate = (EditText) findViewById(R.id.clinicNumberUpdate);
        clinicAddressUpdate = (EditText) findViewById(R.id.clinicAddressUpdate);
        clinicPasswordUpdate = (EditText) findViewById(R.id.clinicPasswordUpdate);
        updateEmployee = (Button) findViewById(R.id.updateEmployee);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        updateEmployee.setOnClickListener(this);
    }


    private void spinnerSetUp(){
        insuranceSpinnerUpdate = (Spinner) findViewById(R.id.insuranceSpinnerUpdate);
        paymentSpinnerUpdate = (Spinner) findViewById(R.id.paymentSpinnerUpdate);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.insurance_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        insuranceSpinnerUpdate.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.payment_methods, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentSpinnerUpdate.setAdapter(adapter2);
    }

    private void updateEmployeeFunction(){

        if (mUser == null){
            finish();
            startActivity(new Intent(this, createAccount.class));
        } else {

            String temp_name = clinicNameUpdate.getText().toString().trim();
            String temp_email = clinicEmailUpdate.getText().toString().trim();
            String temp_number = clinicNumberUpdate.getText().toString().trim();
            String temp_address = clinicAddressUpdate.getText().toString().trim();
            String temp_insurance = insuranceSpinnerUpdate.getSelectedItem().toString().trim();
            String temp_payment = paymentSpinnerUpdate.getSelectedItem().toString().trim();
            String temp_password = clinicPasswordUpdate.getText().toString().trim();

            mDatabase.child("Employees").child(mUser.getUid()).child("address").setValue(temp_address);
            mDatabase.child("Employees").child(mUser.getUid()).child("email").setValue(temp_email);
            mDatabase.child("Employees").child(mUser.getUid()).child("insurance").setValue(temp_insurance);
            mDatabase.child("Employees").child(mUser.getUid()).child("name").setValue(temp_name);
            mDatabase.child("Employees").child(mUser.getUid()).child("number").setValue(temp_number);
            mDatabase.child("Employees").child(mUser.getUid()).child("payment").setValue(temp_payment);

        }

    }


}
