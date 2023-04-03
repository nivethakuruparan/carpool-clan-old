package com.example.carpoolclan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class WelcomePage extends AppCompatActivity {

    Button loginPageRedirect, registrationPageRedirect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        loginPageRedirect = findViewById(R.id.login_page_redirect);
        registrationPageRedirect = findViewById(R.id.registration_page_redirect);

        loginPageRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(WelcomePage.this, LoginPage.class);
            startActivity(intent);
        });

        registrationPageRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(WelcomePage.this, RegistrationPage.class);
            startActivity(intent);
        });
    }
}