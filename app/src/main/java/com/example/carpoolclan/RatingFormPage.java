package com.example.carpoolclan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class RatingFormPage extends AppCompatActivity {

    TripDurationController tripDuration;
    TextView homePageRedirect;

    public RatingFormPage() {
        this.tripDuration = new TripDurationController();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_form_page);

        RecyclerView recyclerView = findViewById(R.id.rating_form_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RatingFormAdapter adapter = new RatingFormAdapter(tripDuration);
        recyclerView.setAdapter(adapter);

        homePageRedirect = findViewById(R.id.home_page_redirect);

        // let users go back to the home page, normally
        homePageRedirect.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Left Rating Form Page", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RatingFormPage.this, HomePage.class);
            startActivity(intent);
        });
    }
}