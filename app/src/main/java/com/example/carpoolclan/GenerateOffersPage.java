package com.example.carpoolclan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GenerateOffersPage extends AppCompatActivity {
    private final Map<String, String> request = new HashMap<>();
    SessionController session;
    DatabaseReference reference;

    public GenerateOffersPage() {
        session = new SessionController();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_offers_page);
        getRequestInfo();

        reference = FirebaseDatabase.getInstance().getReference("offers");
        Query checkDatabase = reference.orderByChild("taxiID");

        checkDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshots) {
                if (snapshots.exists()) {
                    ArrayList<Map<String, String>> offers = new ArrayList<>();
                    for (DataSnapshot snapshot: snapshots.getChildren()) {
                        Map<String, String> offer = new HashMap<>();
                        offer.put("taxi_id", session.decrypt(snapshot.child("taxiID").getValue(String.class)));
                        offer.put("customer_id", session.decrypt(snapshot.child("customerID").getValue(String.class)));
                        offer.put("timestamp", session.decrypt(snapshot.child("time").getValue(String.class)));
                        offer.put("start", session.decrypt(snapshot.child("start").getValue(String.class)));
                        offer.put("destination", session.decrypt(snapshot.child("destination").getValue(String.class)));
                        offer.put("numPassengers", session.decrypt(snapshot.child("numPassengers").getValue(String.class)));
                        offers.add(offer);
                    }
                    for (Map<String, String> offer: offers) {
                        System.out.println("taxi id: " + offer.get("taxi_id"));
                        System.out.println("customer id: " + offer.get("customer_id"));
                        System.out.println("timestamp: " + offer.get("timestamp"));
                        System.out.println("destination: " + offer.get("destination"));
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "There are no offers that match your request at this time", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(GenerateOffersPage.this, MakeRequestsPage.class);
                    startActivity(intent);
                }
                Toast.makeText(getApplicationContext(), "Offers successfully generated", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(GenerateOffersPage.this, PotentialOffersPage.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void getRequestInfo() {
        Intent intent = getIntent();

        request.put("requestID", intent.getStringExtra("requestID"));
        request.put("customerID", intent.getStringExtra("customerID"));
        request.put("time", intent.getStringExtra("time"));
        request.put("start", intent.getStringExtra("start"));
        request.put("destination", intent.getStringExtra("destination"));
        request.put("numPassengers", intent.getStringExtra("numPassengers"));
        request.put("filter", intent.getStringExtra("filter"));
    }
}
