package com.example.clinic_seg2105;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addServiceDialog extends AppCompatDialogFragment {
    private EditText serviceName;
    private Spinner serviceRoleSpinner;

    private DatabaseReference mDatabase;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        mDatabase = FirebaseDatabase.getInstance().getReference();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_service, null);

        builder.setView(view)
                .setTitle("Add Service")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name_of_service = serviceName.getText().toString().trim();
                        String role_of_service = serviceRoleSpinner.getSelectedItem().toString().trim();

                        Service service = new Service(name_of_service, role_of_service);

                        mDatabase.child("Services").child(name_of_service).setValue(service)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            //Toast.makeText(getContext(),"Service Added", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }
                });

        serviceName = view.findViewById(R.id.serviceName);

        serviceRoleSpinner = view.findViewById(R.id.serviceRoleSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.service_role, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        serviceRoleSpinner.setAdapter(adapter);

        return builder.create();
    }


}
