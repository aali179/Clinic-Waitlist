package com.example.clinic_seg2105;

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
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import java.sql.PreparedStatement;

import androidx.appcompat.app.AppCompatActivity;

public class editServiceScreen extends AppCompatActivity {

    // Calling an instance of the database class that stores all the information
    ServiceDBHelper myDb;

    ServiceRepo repo = new ServiceRepo(this);


    // Creating EditText fields for NewName
    private EditText NewName;
    private TextView NewNamePrompt;
    private TextView EditPrompt;
    private TextView RolePrompt;

    // Create Button field for Update
    private Button update;
    private Button add;
    private Button del;

    public static String person_type;
    public static String service_type;
    public String name;


    Spinner spinner2;
    Spinner spinner3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////// SPINNER 3 /////////////////////////////////////////
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        // Using login_dropdown (check string.xml) to allow user to select from dropdown menu
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.service_dropdown, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter);
        //When user clicks the dropdown menu, either onItemSelected will activate or onNothingSelected (nothing happens)
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////// SPINNER 2 /////////////////////////////////////////
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        // Using login_dropdown (check string.xml) to allow user to select from dropdown menu
        //ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.login_dropdown, android.R.layout.simple_spinner_item);
        //adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner2.setAdapter(adapter2);
        //When user clicks the dropdown menu, either onItemSelected will activate or onNothingSelected (nothing happens)
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        ////////////////////////////////////////////////// INITIALIZATION ////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //just some initialization
        NewName = findViewById(R.id.newNameChoice);
        NewNamePrompt = findViewById(R.id.newName);
        RolePrompt = findViewById(R.id.newRole);
        EditPrompt = findViewById(R.id.editChoose);
        update = (Button)findViewById(R.id.updateService);
        add = (Button)findViewById(R.id.addServ);
        del = (Button)findViewById(R.id.deleteServ);

        // We pass crrAccTextWatcher (a function) to the addTextChangedListener method to Name, Username, Password and
        // password-reenter
        //NewName.addTextChangedListener(crrAccTextWatcher);
        //populateDB();
        loadService();

        // add screen
        if (adminScreen.screenChoice == 1) {
            EditPrompt.setVisibility(View.INVISIBLE);
            spinner2.setVisibility(View.INVISIBLE);
            update.setVisibility(View.INVISIBLE);
            del.setVisibility(View.INVISIBLE);
        }

        //edit screen
        if (adminScreen.screenChoice == 2) {
            add.setVisibility(View.INVISIBLE);
            del.setVisibility(View.INVISIBLE);
        }

        //delete screen
        if (adminScreen.screenChoice == 3) {
            add.setVisibility(View.INVISIBLE);
            update.setVisibility(View.INVISIBLE);
            NewName.setVisibility(View.INVISIBLE);
            NewNamePrompt.setVisibility(View.INVISIBLE);
            RolePrompt.setVisibility(View.INVISIBLE);
            spinner3.setVisibility(View.INVISIBLE);
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addService();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateService();
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteService();
            }
        });


        // edits data to database
        // EditData();
    }

    // LOGIN TEXT WATCHER METHOD //
    // private TextWatcher crrAccTextWatcher = new TextWatcher() {

    //}

    public void addService() {
        Intent intent = new Intent(this, adminScreen.class);

        //deletes old service
        //repo.delete(service_type);

        //insert new service
        Service service = new Service();
        service.setRole(person_type);
        service.setService(NewName.getText().toString());
        repo.insert(service);

        startActivity(intent);
    }

    public void updateService() {
        Intent intent = new Intent(this, adminScreen.class);

        //deletes old service
        repo.delete(service_type);

        //insert new service
        Service service = new Service();
        service.setRole(person_type);
        service.setService(NewName.getText().toString());
        repo.insert(service);

        startActivity(intent);
    }

    public void deleteService() {
        Intent intent = new Intent(this, adminScreen.class);

        //deletes old service
        repo.delete(service_type);

        //insert new service
        //Service service = new Service();
        //service.setRole(person_type);
        //service.setService(NewName.getText().toString());
        //repo.insert(service);

        startActivity(intent);
    }

    private void loadService(){
        ArrayAdapter<String> spinnerAdapter;
        ServiceRepo db = new ServiceRepo(getApplicationContext());
        List<String> services = db.getAll();
        spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, services);
        spinner2.setAdapter(spinnerAdapter);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    private void populateDB(){

        //ServiceRepo repo = new ServiceRepo(this);
        //repo.Delete();

        for (Integer i=0;i<5;i++){
            Service service = new Service();
            service.setRole("Patient");
            service.setService("Checkup " + i.toString());

            repo.insert(service);


        }

    }
}
