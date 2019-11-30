package com.example.clinic_seg2105;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class addDeleteServiceScreen extends AppCompatActivity {

    // Calling an instance of the database class that stores all the information
    EmployeeServiceDBHelper myDb;

    EmployeeServiceRepo repo = new EmployeeServiceRepo(this);

    private TextView addDeletePrompt;
    private Button addBTN;
    private Button delBTN;
    Spinner serviceSpinner;

    public static String service_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_service);



        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////// SPINNER 2 /////////////////////////////////////////
        serviceSpinner = (Spinner) findViewById(R.id.serviceSpinner);
        // Using login_dropdown (check string.xml) to allow user to select from dropdown menu
        //ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.login_dropdown, android.R.layout.simple_spinner_item);
        //adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner2.setAdapter(adapter2);
        //When user clicks the dropdown menu, either onItemSelected will activate or onNothingSelected (nothing happens)
        serviceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Service selected = (Service) parent.getItemAtPosition(position);
                //mTVservice.setText(selected.getService());

                String text = parent.getItemAtPosition(position).toString();
                service_type = text;
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ////////////////////////////////////////////////// INITIALIZATION ////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //just some initialization
        addDeletePrompt = findViewById(R.id.chooseServToAorD);
        addBTN = (Button)findViewById(R.id.addService);
        delBTN = (Button)findViewById(R.id.delService);

        // add screen
        if (employeeScreen.screenChoice == 2) {
            delBTN.setVisibility(View.INVISIBLE);
            loadServicesToAdd();
            addDeletePrompt.setText("Choose service to add to profile: ");
        }

        // add screen
        if (employeeScreen.screenChoice == 3) {
            addBTN.setVisibility(View.INVISIBLE);
            loadServicesToDelete();
            addDeletePrompt.setText("Choose service to delete from profile: ");
        }

        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addService();
            }
        });

        delBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteService();
            }
        });




    }

    private void loadServicesToAdd() {
        ArrayAdapter<String> spinnerAdapter;
        ServiceRepo db = new ServiceRepo(getApplicationContext());
        List<String> services = db.getAll();
        spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, services);
        serviceSpinner.setAdapter(spinnerAdapter);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    private void loadServicesToDelete() {
        ArrayAdapter<String> spinnerAdapter;
        EmployeeServiceRepo db = new EmployeeServiceRepo(getApplicationContext());
        List<String> services = db.getAll(loginScreen.activeUser);
        spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, services);
        serviceSpinner.setAdapter(spinnerAdapter);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    public void addService() {
        Intent intent = new Intent(this, employeeScreen.class);

        //adds service
        EmployeeService employeeService = new EmployeeService();
        employeeService.setUsername(loginScreen.activeUser);
        employeeService.setService(service_type);
        repo.insert(employeeService);

        startActivity(intent);
    }

    public void deleteService() {
        Intent intent = new Intent(this, employeeScreen.class);

        //deletes old service
        repo.delete(loginScreen.activeUser, service_type);

        startActivity(intent);
    }
}
