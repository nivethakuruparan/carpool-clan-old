package com.example.carpoolclan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {

    AccountManagementController accountManagement = new AccountManagementController();
    EditText loginEmail, loginPassword;
    Button loginButton;
    TextView registrationPageRedirect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        registrationPageRedirect = findViewById(R.id.registration_page_redirect);

        loginButton.setOnClickListener(view -> {
            Boolean isValidated;
            if (!accountManagement.checkEmptyFields(loginEmail) | !accountManagement.checkEmptyFields(loginPassword)){
                isValidated = false;
            } else {
                isValidated = accountManagement.validateLogin(loginEmail, loginPassword);
            }

            if (isValidated) {
                Intent intent = new Intent(LoginPage.this, HomePage.class);
                startActivity(intent);
            }
        });

        registrationPageRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(LoginPage.this, RegistrationPage.class);
            startActivity(intent);
        });
    }
}