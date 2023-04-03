package com.example.carpoolclan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

public class RegistrationPage extends AppCompatActivity {

    AccountManagementController accountManagement = new AccountManagementController();
    EditText registrationName, registrationEmail, registrationDOB, registrationPassword;
    Button registrationButton;
    TextView loginPageRedirect;
    FirebaseDatabase db;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        registrationName = findViewById(R.id.registration_name);
        registrationEmail = findViewById(R.id.registration_email);
        registrationDOB = findViewById(R.id.registration_dob);
        registrationPassword = findViewById(R.id.registration_password);
        registrationButton = findViewById(R.id.registration_button);
        loginPageRedirect = findViewById(R.id.login_page_redirect);

        registrationButton.setOnClickListener(view -> {

        });

        registrationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                db = FirebaseDatabase.getInstance();
                reference = db.getReference("users");

                String name = registrationName.getText().toString();
                String email = registrationEmail.getText().toString();
                String dob = registrationDOB.getText().toString();
                String password = registrationPassword.getText().toString();

                Boolean isValidated;
                if (!accountManagement.checkEmptyFields(registrationName) | !accountManagement.checkEmptyFields(registrationEmail) | !accountManagement.checkEmptyFields(registrationDOB) | !accountManagement.checkEmptyFields(registrationPassword) ){
                    isValidated = false;
                } else {
                    isValidated = accountManagement.validateRegistration(registrationName, registrationEmail, registrationDOB, registrationPassword);
                }

                if (isValidated) {
                    HelperClass helperClass = new HelperClass(name, email, dob, password);
                    reference.child(name).setValue(helperClass);

                    Toast.makeText(RegistrationPage.this, "You have signed up successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationPage.this, LoginPage.class);
                    startActivity(intent);
                }
            }
        });
    }
}