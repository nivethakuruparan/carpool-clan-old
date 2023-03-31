package com.example.carpoolclan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;

public class RegistrationPage extends AppCompatActivity {

    EditText signupName, signupEmail, signupDOB, signupPassword;
    TextView loginRedirectText;
    Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        name = findViewById(R.id.signup_name);
        email = findViewById(R.id.signup_email);
        dob = findViewById(R.id.signup_dob);
        password = findViewById(R.id.signup_password);
    }

    public void displayUserData() {

    }
}