package com.example.carpoolclan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

public class HomePage extends AppCompatActivity {
    private final Map<String, Object> userInfo = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getUserData();
    }

    public void getUserData() {
        Intent intent = getIntent();

        String nameUser = intent.getStringExtra("name");
        String emailUser = intent.getStringExtra("email");
        String dobUser = intent.getStringExtra("dob");
        String passwordUser = intent.getStringExtra("password");

        userInfo.put("name", nameUser);
        userInfo.put("email", emailUser);
        userInfo.put("dob", dobUser);
        userInfo.put("password", passwordUser);
    }
}