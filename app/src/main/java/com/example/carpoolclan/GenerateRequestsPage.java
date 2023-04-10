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

public class GenerateRequestsPage extends AppCompatActivity {
    private final Map<String, String> userInfo = new HashMap<>();
    DatabaseReference reference;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_requests);
        //getUserData();

        SessionController session = new SessionController();
        reference = FirebaseDatabase.getInstance().getReference("requests");
        Query checkDatabase = reference.orderByChild("id");

        checkDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshots) {
                if (snapshots.exists()) {
                    ArrayList<Map<String, String>> requests = new ArrayList<>();
                    for (DataSnapshot snapshot: snapshots.getChildren()) {
                        Map<String, String> request = new HashMap<>();
                        request.put("id", session.decrypt(snapshot.child("id").getValue(String.class)));
                        request.put("destination", session.decrypt(snapshot.child("destination").getValue(String.class)));
                        request.put("filter", session.decrypt(snapshot.child("filter").getValue(String.class)));
                        request.put("numPassengers", session.decrypt(snapshot.child("numPassengers").getValue(String.class)));
                        request.put("start", session.decrypt(snapshot.child("start").getValue(String.class)));
                        requests.add(request);
                    }
                    for (Map<String, String> request: requests) {
                        System.out.println("id: " + request.get("id"));
                        System.out.println("destination: " + request.get("destination"));
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "There are no requests that match your offer at this time", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(GenerateRequestsPage.this, MakeOffersPage.class);
                    startActivity(intent);
                }
                Toast.makeText(getApplicationContext(), "Requests successfully generated", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(GenerateRequestsPage.this, IncomingRequestsPage.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getUserData () {
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
