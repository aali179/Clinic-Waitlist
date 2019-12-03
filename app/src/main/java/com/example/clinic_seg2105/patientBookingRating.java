package com.example.clinic_seg2105;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class patientBookingRating extends AppCompatActivity implements View.OnClickListener {

    public static final String CLINIC_NAME = "com.example.hospital_clinic.CLINIC_NAME";
    public static final String ID_OF_CLINIC = "com.example.hospital_clinic.ID_OF_CLINIC";

    private Button commentButton, bookAppointmentButton;
    private TextView clinicNameBooking;
    private String getNamePrevActivity;
    private String clinic_ID;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_booking_rating);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        Intent intent = getIntent();
        getNamePrevActivity = intent.getStringExtra(patientSearchClinic.EXTRA_TEXT);
        clinic_ID = intent.getStringExtra(patientSearchClinic.CLINIC_ID);

        commentButton = (Button) findViewById(R.id.commentButton);
        bookAppointmentButton = (Button) findViewById(R.id.bookAppointmentButton);
        clinicNameBooking = (TextView) findViewById(R.id.clinicNameBooking);

        commentButton.setOnClickListener(this);
        bookAppointmentButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.commentButton:
                startCommentPostActivity();
                break;
            case R.id.bookAppointmentButton:
                startBookAppointmentActivity();
        }
    }


    private void startCommentPostActivity(){

        Intent intent = new Intent(getApplicationContext(), commentSection.class);
        intent.putExtra(CLINIC_NAME, getNamePrevActivity);
        startActivity(intent);
    }


    private void startBookAppointmentActivity(){

        Intent intent = new Intent(getApplicationContext(), bookingSection.class);
        intent.putExtra(CLINIC_NAME, getNamePrevActivity);
        intent.putExtra(ID_OF_CLINIC, clinic_ID);
        clinicNameBooking.setText(clinic_ID);
        startActivity(intent);
    }
}
