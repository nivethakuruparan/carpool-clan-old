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
    }
}