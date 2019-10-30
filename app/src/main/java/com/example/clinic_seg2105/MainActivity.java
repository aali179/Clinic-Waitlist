package com.example.clinic_seg2105;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    Database_helper myDb;

    public EditText Name;
    private EditText Username;
    private EditText Password;
    //public String person_type;
    private Button create;
    public EditText Password_re;

    public static String person_type;
    public static String user_name;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.login_dropdown, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                person_type = text;
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        myDb = new Database_helper(this);
        Name = findViewById(R.id.fullName);
        Username = findViewById(R.id.userName);
        Password = findViewById(R.id.userPassword);
        Password_re = findViewById(R.id.passRe);

        create = findViewById(R.id.createAccount);

        Name.addTextChangedListener(loginTextWatcher);
        Username.addTextChangedListener(loginTextWatcher);
        Password.addTextChangedListener(loginTextWatcher);

        Password_re.addTextChangedListener(loginTextWatcher);

        AddData();

    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String nameInput = Name.getText().toString().trim();
            String usernameInput = Username.getText().toString().trim();
            String passwordInput = Password.getText().toString().trim();
            String passwordReInput = Password_re.getText().toString().trim();

            boolean passChecker;

            if (passwordInput.equals(passwordReInput)){
                passChecker = true;
            } else {passChecker = false;}

            create.setEnabled(!nameInput.isEmpty() && !usernameInput.isEmpty() && !passwordInput.isEmpty() && !passwordReInput.isEmpty() && passChecker);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void AddData() {
        create.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(Name.getText().toString(), Username.getText().toString(), Password.getText().toString(), person_type);

                        if (isInserted == true) {
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();

                        user_name = Name.getText().toString();

                        openActivity2();
                    }
                }
        );
    }


    public void openActivity2(){
        Intent intent = new Intent(this, createAccount.class);
        startActivity(intent);
    }



}


