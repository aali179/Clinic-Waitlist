package com.example.clinic_seg2105;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class adminLogin extends AppCompatActivity implements View.OnClickListener {

    private EditText passwordAdmin, emailAdmin;
    private Button loginButtonAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        emailAdmin = (EditText) findViewById(R.id.emailAdmin);
        passwordAdmin = (EditText) findViewById(R.id.passwordAdmin);
        loginButtonAdmin = (Button) findViewById(R.id.loginButtonAdmin);

        loginButtonAdmin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginButtonAdmin:
                loginAdminFunc();
                break;
        }
    }

    private void loginAdminFunc(){

        String temp_email = emailAdmin.getText().toString().trim();
        String temp_pass = passwordAdmin.getText().toString().trim();

        if (temp_email.equals("admin@gmail.com") && temp_pass.equals("admin1234")) {
            finish();
            Intent intent = new Intent(this, adminScreen.class);
            startActivity(intent);
        } else{
            Toast.makeText(adminLogin.this, "Sorry, incorrect Admin credentials", Toast.LENGTH_LONG).show();

        }

    }
}
