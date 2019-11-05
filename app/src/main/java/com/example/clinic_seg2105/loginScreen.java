package com.example.clinic_seg2105;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class loginScreen extends AppCompatActivity {
    private EditText username_login;
    private EditText password_login;

    private String username_login_string;
    private String password_login_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        username_login = (EditText) findViewById(R.id.userName);
        password_login = (EditText) findViewById(R.id.userPassword);

        username_login_string = username_login.getText().toString();
        password_login_string = password_login.getText().toString();
    }


}
