package com.example.clinic_seg2105;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class createAccount extends AppCompatActivity {


    public String user_name = MainActivity.user_name;
    public String person_type = MainActivity.person_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        openWelcomeScreen();
    }

    public void openWelcomeScreen(){
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.append(" " + user_name + " you are logged in as " + person_type);
    }
}
