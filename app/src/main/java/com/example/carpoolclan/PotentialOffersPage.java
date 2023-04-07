package com.example.carpoolclan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class PotentialOffersPage extends AppCompatActivity {

    DispatcherController dispatcher;
    TextView homePageRedirect;

    public PotentialOffersPage() {
        this.dispatcher = new DispatcherController();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potential_offers_page);

        RecyclerView recyclerView = findViewById(R.id.potential_offers_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PotentialOffersAdapter adapter = new PotentialOffersAdapter(dispatcher);
        recyclerView.setAdapter(adapter);

        homePageRedirect = findViewById(R.id.home_page_redirect);

        // let users go back to the home page; display an alert to notify about unsaved changes
        homePageRedirect.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(PotentialOffersPage.this);
            builder.setMessage("Are you sure you want to leave this page? The responses you see here might not be available.");
            builder.setTitle("Leave Page");
            builder.setCancelable(false);

            builder.setPositiveButton("Yes", (dialog, which) -> {
                Toast.makeText(getApplicationContext(), "Left Potential Offers Page", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(PotentialOffersPage.this, MakeRequestsPage.class);
                startActivity(intent);
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }
}