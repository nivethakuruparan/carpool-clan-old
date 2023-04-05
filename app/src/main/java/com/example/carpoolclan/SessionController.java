package com.example.carpoolclan;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class SessionController {
    FirebaseDatabase db;
    DatabaseReference reference;

    protected void storeRegistrationData(String name, String email, String dob, String password) {
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("customers");
        HelperClass helperClass = new HelperClass(name, email, dob, password);
        reference.child(helperClass.email).setValue(helperClass);
    }

}
