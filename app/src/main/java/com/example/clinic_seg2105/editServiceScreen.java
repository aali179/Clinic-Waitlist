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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class editServiceScreen extends AppCompatActivity implements View.OnClickListener {

    private Spinner editServiceSpinner;
    private EditText editServiceNewName;
    private Spinner newRoleEditServiceSpinner;
    private Button editChangeConfirm;
    private Button cancelEdit;

    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service_screen);

        ref = FirebaseDatabase.getInstance().getReference();

        spinnerSetUp();

        editServiceNewName = (EditText) findViewById(R.id.editServiceNewName);
        editChangeConfirm = (Button) findViewById(R.id.editChangeConfirm);
        cancelEdit = (Button) findViewById(R.id.cancelEdit);

        editChangeConfirm.setOnClickListener(this);
        cancelEdit.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.editChangeConfirm:
                editService();
                break;
            case R.id.cancelEdit:
                finish();
                Intent intent = new Intent(getApplicationContext(), adminScreen.class);
                startActivity(intent);
        }

    }

    private void spinnerSetUp(){
        newRoleEditServiceSpinner = (Spinner) findViewById(R.id.newRoleEditServiceSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.service_role, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        newRoleEditServiceSpinner.setAdapter(adapter);


        editServiceSpinner = (Spinner) findViewById(R.id.editServiceSpinner);
        ref.child("Services").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> servicesList = new ArrayList<String>();

                for (DataSnapshot servSnapshot: dataSnapshot.getChildren()){
                    String servName = servSnapshot.child("name").getValue(String.class);
                    servicesList.add(servName);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(editServiceScreen.this, android.R.layout.simple_spinner_dropdown_item, servicesList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                editServiceSpinner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void editService(){

        String serviceToBeUpdated = editServiceSpinner.getSelectedItem().toString().trim();

        String newServiceName = editServiceNewName.getText().toString().trim();
        String newServiceRole = newRoleEditServiceSpinner.getSelectedItem().toString().trim();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Services").child(serviceToBeUpdated);

        ref.child("name").setValue(newServiceName);
        ref.child("role").setValue(newServiceRole);
        ref.setValue(newServiceName);
    }

}
