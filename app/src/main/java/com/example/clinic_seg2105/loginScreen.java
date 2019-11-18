package com.example.clinic_seg2105;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class loginScreen extends AppCompatActivity {
    private EditText username_login;
    private EditText password_login;
    private Button login;
    Database_helper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        db = new Database_helper(this);

        username_login = (EditText) findViewById(R.id.userName);
        password_login = (EditText) findViewById(R.id.userPassword);
        login = findViewById(R.id.loginButton);

        username_login.addTextChangedListener(loginTextWatcher);
        password_login.addTextChangedListener(loginTextWatcher);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String username = username_login.getText().toString().trim();
                String password = password_login.getText().toString().trim();

                if (username.equals("admin") && password.equals("1234")){
                    openAdmin();

                }else {
                    boolean res = db.checkData(username, password);

                    if (res) {
                        Toast.makeText(loginScreen.this, "Success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(loginScreen.this, "Wrong", Toast.LENGTH_SHORT).show();
                    }
                }

            }

        });

    }



    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String username_login_string = username_login.getText().toString().trim();
            String password_login_string = password_login.getText().toString().trim();

            login.setEnabled(!username_login_string.isEmpty() && !password_login_string.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void loginPage(View view){
        String username =  username_login.getText().toString().trim();
        String password = password_login.getText().toString().trim();

        if (username.equals("admin") && password.equals("1234")){
            openAdmin();

        }
    }


    public void openAdmin(){
        Intent intent = new Intent(this, adminScreen.class);
        username_login.getText().clear();
        password_login.getText().clear();

        startActivity(intent);
    }


}
