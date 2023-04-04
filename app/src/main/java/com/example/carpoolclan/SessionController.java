package com.example.carpoolclan;

import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SessionController {
    FirebaseDatabase db;
    DatabaseReference reference;
    Boolean emailExists;

    protected void storeRegistrationData(String name, String email, String dob, String password) {
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("customers");
        HelperClass helperClass = new HelperClass(name, email, dob, password);
        reference.child(helperClass.email).setValue(helperClass);
    }
//
//    protected Boolean checkEmailExistence(String email) {
//        db = FirebaseDatabase.getInstance();
//        reference = db.getReference("customers");
//        Query checkEmailInDatabase = reference.orderByChild("email").equalTo(email);
//        checkEmailInDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                System.out.println("Inside onDataChange");
//                emailExists = snapshot.exists();
//                System.out.println(emailExists);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                System.out.println("Inside onCancelled");
//            }
//        });
//        System.out.println(emailExists);
//        return emailExists;
//    }
}
