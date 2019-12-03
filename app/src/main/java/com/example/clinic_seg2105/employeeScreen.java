
package com.example.clinic_seg2105;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class employeeScreen extends AppCompatActivity implements View.OnClickListener {

    private Button serviceOptionButton;
    private Button profileOptionButton;
    private Button logoutButton;
    private Button employeeViewProfile;
    private Button setHoursEmployee;
    private TextView welcomeEmployee;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_screen);

        serviceOptionButton = (Button) findViewById(R.id.serviceOptionButton);
        profileOptionButton = (Button) findViewById(R.id.profileOptionButton);
        logoutButton = (Button) findViewById(R.id.logoutButton);
        employeeViewProfile = (Button) findViewById(R.id.employeeViewProfile);
        setHoursEmployee = (Button) findViewById(R.id.setHoursEmployee);
        welcomeEmployee = (TextView) findViewById(R.id.welcomeEmployee);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference();

        welcomeEmployee.append( (mRef.child("Employees").child(mUser.getUid()).child("name")).toString().trim());
        welcomeEmployee.append(" Employee");

        serviceOptionButton.setOnClickListener(this);
        profileOptionButton.setOnClickListener(this);
        logoutButton.setOnClickListener(this);
        employeeViewProfile.setOnClickListener(this);
        setHoursEmployee.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.serviceOptionButton:
                serviceOptionFunc();
                break;
            case R.id.profileOptionButton:
                profileOptionEmpFunc();
                break;
            case R.id.logoutButton:
                logoutFunc();
                break;
            case R.id.employeeViewProfile:
                viewEmployeeProfile();
                break;
            case R.id.setHoursEmployee:
                setHoursEmployeeFunc();
                break;
        }

    }

    // still need to do delete...
    private void serviceOptionFunc(){
        Intent intent = new Intent(this, serviceOptionEmployee.class);
        startActivity(intent);
    }

    //done
    private void profileOptionEmpFunc(){
        Intent intent = new Intent(this, editEmployeeProfile.class);
        startActivity(intent);
    }

    //done
    private void viewEmployeeProfile(){
        Intent intent = new Intent(this, viewEmployeeProfile.class);
        startActivity(intent);
    }

    private void setHoursEmployeeFunc(){
        Intent intent = new Intent(this, employeeSetHours.class);
        startActivity(intent);
    }

    //done
    private void logoutFunc(){
        mAuth.signOut();
        startActivity(new Intent(this, loginScreen.class));
    }


}
