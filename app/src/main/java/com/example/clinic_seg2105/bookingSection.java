package com.example.clinic_seg2105;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class bookingSection extends AppCompatActivity implements View.OnClickListener {

    private TextView waitingTime;
    private Button bookAppointment;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mRef;

    private String clinicName;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_section);

        waitingTime = (TextView) findViewById(R.id.waitingTime);
        bookAppointment = (Button) findViewById(R.id.bookAppointment);

        Intent intent = getIntent();
        clinicName = intent.getStringExtra(patientBookingRating.CLINIC_NAME);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference("Employees");

        bookAppointment.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bookAppointment:
                bookAppointmentFunc();
                break;
        }
    }

    private void bookAppointmentFunc(){

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    if ( (ds.child("name").getValue(String.class)).equals(clinicName) ){
                        id = ds.getKey();
                        book(id);
                        setWaitingTime(id);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void book(String id){
        mRef.child(id).child("WaitingList").push().setValue(mUser.getUid());
    }

    private void setWaitingTime(String id){
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long count = (dataSnapshot.getChildrenCount()) * 15;
                String count_string = Long.toString(count);
                waitingTime.append(count_string);
                waitingTime.append(" minutes");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
