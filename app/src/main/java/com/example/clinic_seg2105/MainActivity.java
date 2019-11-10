package com.example.clinic_seg2105;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

    // Calling an instance of the database class that stores all the information
    Database_helper myDb;

    // Creating EditText fields for Name, Username and Password
    public EditText Name;
    private EditText Username;
    private EditText Password;
    private Button create;

    //EditText field for password re-entry
    public EditText Password_re;


    public static String person_type;
    public static String user_name;
    Spinner spinner; // The drop down list

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////// SPINNER ///////////////////////////////////////////
        spinner = (Spinner)findViewById(R.id.spinner);
        // Using login_dropdown (check string.xml) to allow user to select from dropdown menu
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.login_dropdown, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //When user clicks the dropdown menu, either onItemSelected will activate or onNothingSelected (nothing happens)
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
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////// INITIALIZATION ////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //just some initialization
        myDb = new Database_helper(this);
        Name = findViewById(R.id.fullName);
        Username = findViewById(R.id.userName);
        Password = findViewById(R.id.userPassword);
        Password_re = findViewById(R.id.passRe);
        create = findViewById(R.id.createAccount);

        // We pass crrAccTextWatcher (a function) to the addTextChangedListener method to Name, Username, Password and
        // password-reenter
        Name.addTextChangedListener(crrAccTextWatcher);
        Username.addTextChangedListener(crrAccTextWatcher);
        Password.addTextChangedListener(crrAccTextWatcher);
        Password_re.addTextChangedListener(crrAccTextWatcher);

        //adds data to database
        AddData();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // LOGIN TEXT WATCHER METHOD //
    private TextWatcher crrAccTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String nameInput = Name.getText().toString().trim();
            String usernameInput = Username.getText().toString().trim();
            String passwordInput = Password.getText().toString().trim();
            String passwordReInput = Password_re.getText().toString().trim();

            //Checking if the two passwords entered are the same
            boolean passChecker;

            if (passwordInput.equals(passwordReInput)){
                passChecker = true;
            } else {passChecker = false;}

            // Enabling the create button if passwords are same and all feilds are non-empty
            create.setEnabled(!nameInput.isEmpty() && !usernameInput.isEmpty() && !passwordInput.isEmpty() && !passwordReInput.isEmpty() && passChecker);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };



    public void switchtoLogin (View view){
        openLoginScreen();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Add Data to Database
    public void AddData() {
        create.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(Name.getText().toString(), Username.getText().toString(), Password.getText().toString(), person_type);

                        if (isInserted) {
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();

                        user_name = Name.getText().toString();

                        openLoginScreen();
                    }
                }
        );
    }
    public void openLoginScreen(){
        Intent intent = new Intent(this, loginScreen.class);

        // clearing the fields , so if user goes back the fields are empty
        Name.getText().clear();
        Username.getText().clear();
        Password.getText().clear();
        Password_re.getText().clear();

        startActivity(intent);
    }



}


