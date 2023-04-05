package com.example.carpoolclan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewPotentialOffersPage extends AppCompatActivity {

    Button showMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_potential_offers_page);

        showMap = findViewById(R.id.button);

        showMap.setOnClickListener(view -> {
            Intent intent = new Intent(ViewPotentialOffersPage.this, GoogleMapsInterface.class);
            startActivity(intent);
        });
    }
}