package com.example.carpoolclan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationPage extends AppCompatActivity {

    AccountManagementController accountManagement = new AccountManagementController();
    EditText registrationName, registrationEmail, registrationDOB, registrationPassword;
    Button registrationButton;
    TextView loginPageRedirect;
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
            Boolean isValidated;
            if (!accountManagement.checkEmptyFields(registrationName) | !accountManagement.checkEmptyFields(registrationEmail) | !accountManagement.checkEmptyFields(registrationDOB) | !accountManagement.checkEmptyFields(registrationPassword) ){
                isValidated = false;
            } else {
                isValidated = accountManagement.validateRegistration(registrationName, registrationEmail, registrationDOB, registrationPassword);
            }

            if (isValidated) {
                Toast.makeText(getApplicationContext(), "Successfully Registered an Account", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegistrationPage.this, LoginPage.class);
                startActivity(intent);
            }
        });

        loginPageRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(RegistrationPage.this, LoginPage.class);
            startActivity(intent);
        });
    }
}