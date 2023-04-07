package com.example.carpoolclan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    TripDurationController tripDuration;
    TextView tripStatus, startLocation, endLocation, duration, fareCost, tripSimulation;
    Button makeRequestsPageRedirect, makeOffersPageRedirect, endTrip, managePlaylistPageRedirect;

    boolean hasTrip;
    public HomePage() {
        this.tripDuration = new TripDurationController();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // buttons
        makeRequestsPageRedirect = findViewById(R.id.make_requests_page_redirect);
        makeOffersPageRedirect = findViewById(R.id.make_offers_page_redirect);
        endTrip = findViewById(R.id.end_trip);
        managePlaylistPageRedirect = findViewById(R.id.manage_playlist_page_redirect);

        // textviews
        tripStatus = findViewById(R.id.trip_status);
        startLocation = findViewById(R.id.start_location);
        endLocation = findViewById(R.id.end_location);
        duration = findViewById(R.id.duration);
        fareCost = findViewById(R.id.fare_cost);
        tripSimulation = findViewById(R.id.trip_simulation);

        // booleans
        hasTrip = false;

        tripSimulation.setOnClickListener(view -> {
            hasTrip = !hasTrip;
            if (hasTrip) {
                makeRequestsPageRedirect.setVisibility(View.GONE);
                makeOffersPageRedirect.setVisibility(View.GONE);
                endTrip.setVisibility(View.VISIBLE);
                managePlaylistPageRedirect.setVisibility(View.VISIBLE);

                tripStatus.setText("new Trip");

                startLocation.setVisibility(View.VISIBLE);
                startLocation.setText("new location");

                endLocation.setVisibility(View.VISIBLE);
                endLocation.setText("new end location");

                duration.setVisibility(View.VISIBLE);
                duration.setText("new duration");

                fareCost.setVisibility(View.VISIBLE);
                fareCost.setText("new fare cost");


            } else {
                makeRequestsPageRedirect.setVisibility(View.VISIBLE);
                makeOffersPageRedirect.setVisibility(View.VISIBLE);
                endTrip.setVisibility(View.GONE);
                managePlaylistPageRedirect.setVisibility(View.GONE);

                tripStatus.setText("no current trip");
                startLocation.setVisibility(View.GONE);
                endLocation.setVisibility(View.GONE);
                duration.setVisibility(View.GONE);
                fareCost.setVisibility(View.GONE);
            }
        });

        makeRequestsPageRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(HomePage.this, MakeRequestsPage.class);
            startActivity(intent);
        });

        makeOffersPageRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(HomePage.this, MakeOffersPage.class);
            startActivity(intent);
        });
    }
}