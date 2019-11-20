package com.example.clinic_seg2105;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import static com.example.clinic_seg2105.GlobalVariables.*;

public class addClinicScreen extends AppCompatActivity {

    private EditText phoneNumber;
    private EditText nameClinic;
    private EditText locationAddress;
    private Spinner insurance;
    private Spinner payment;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clinic_screen);

        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        nameClinic = (EditText) findViewById(R.id.nameOfClinic);
        locationAddress = (EditText) findViewById(R.id.locationAddress);
        insurance = (Spinner) findViewById(R.id.insurance);
        payment = (Spinner) findViewById(R.id.payment);
        saveButton = (Button) findViewById(R.id.saveButton);

        String[] insuranceSpinner = new String[]{"Through Employer", "Personal Insurance", "Other"};
        String[] paymentSpinner = new String[]{"Credit Card", "Cash", "Cheque"};

        ArrayAdapter<String> insuranceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, insuranceSpinner);
        ArrayAdapter<String> paymentAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, paymentSpinner);

        insuranceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        insurance.setAdapter(insuranceAdapter);
        payment.setAdapter(paymentAdapter);

    }

    public void saveButtonFunction(View v){
        String locAdd = locationAddress.getText().toString().trim();
        String phoNu = phoneNumber.getText().toString().trim();
        String nameClin = nameClinic.getText().toString().trim();
        String ins = insurance.getSelectedItem().toString().trim();
        String pay = payment.getSelectedItem().toString().trim();
        Clinic clinic = new Clinic(locAdd,phoNu,nameClin,ins, pay);

        clinic_vector.add(clinic);

    }



    //@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }


//    public void onNothingSelected(AdapterView<?> arg0) {
//        String item = parent.getItemAtPosition(0).toString();
//        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
//    }
}
