package com.example.clinic_seg2105;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.SimpleTimeZone;

public class commentSection extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView commentList;
    private RecyclerView.Adapter mAdapter;


    private EditText commentInput;
    private ImageButton postCommentButton;
    private TextView text;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mRef;

    Intent intent = getIntent();
    private String getNamePrevActivity = intent.getStringExtra(patientBookingRating.CLINIC_NAME);
    private String clinicId = intent.getStringExtra(patientBookingRating.ID_OF_CLINIC);

    ArrayList<Comments> comments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_section);


        text = (TextView) findViewById(R.id.text);
        text.setText("Nothing");


        commentList = (RecyclerView) findViewById(R.id.commentList);
        commentList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);


        commentInput = (EditText) findViewById(R.id.commentInput);
        postCommentButton = (ImageButton) findViewById(R.id.postCommentButton);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference("Employees");

        //populateComments(comments);


        mAdapter = new commentAdapter(comments);

        commentList.setLayoutManager(linearLayoutManager);
        commentList.setAdapter(mAdapter);
        postCommentButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.postCommentButton:
                //pushValue("hello");
                postingComment();
                break;
        }
    }

    private void postingComment(){

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String clinic_name = getNamePrevActivity;

                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    if ( (ds.child("name").getValue(String.class)).equals(clinic_name) ){
                        String emp_id = ds.getKey();
                        clinicId = emp_id;
                        pushValue(emp_id);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void pushValue(String id){

        String commentText = commentInput.getText().toString().trim();

        Calendar calDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
        final String saveCurrentDate = currentDate.format(calDate.getTime());

        Calendar calTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
        final String saveCurrentTime = currentTime.format(calDate.getTime());

        HashMap commentMap = new HashMap();
        commentMap.put("uid", mUser.getDisplayName());
        commentMap.put("comment", commentText);
        commentMap.put("date", saveCurrentDate);
        commentMap.put("time", saveCurrentTime);

        mRef.child(id).child("Comments").push().setValue(commentMap);
    }

    private void populateComments(final ArrayList<Comments> comm_list){

        mRef.child(clinicId).child("Comments").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String c = ds.child("comment").getValue(String.class);
                    String d = ds.child("date").getValue(String.class);
                    String t = ds.child("time").getValue(String.class);
                    String id = ds.child("uid").getValue(String.class);

                    Comments comment = new Comments(c,d,t,id);

                    comm_list.add(comment);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
