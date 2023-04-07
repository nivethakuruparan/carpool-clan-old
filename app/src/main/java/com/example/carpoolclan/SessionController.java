package com.example.carpoolclan;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

public class SessionController {
    DatabaseReference reference;
    final String secret = "design";
    EncryptionController encryption = new EncryptionController();

    protected void storeRegistrationData(String name, String email, String dob, String password) {
        reference = FirebaseDatabase.getInstance().getReference("customers");
        AccountInfoHelper accountInfoHelper = new AccountInfoHelper(name, email, dob, password);
        reference.child(accountInfoHelper.email).setValue(accountInfoHelper);
    }

    protected void storeOfferData(String taxi_id, String destination, String num_passengers) {
        reference = FirebaseDatabase.getInstance().getReference("offers");
        OfferInfoHelper offerInfoHelper = new OfferInfoHelper(taxi_id, destination, num_passengers);
        reference.child(offerInfoHelper.id).setValue(offerInfoHelper);
    }

    protected void storeRequestData(int id, String start, String destination, String num_passengers, String filter) {
        reference = FirebaseDatabase.getInstance().getReference("requests");
        RequestInfoHelper requestInfoHelper = new RequestInfoHelper(id, start, destination, num_passengers, filter);
        reference.child(String.valueOf(requestInfoHelper.id)).setValue(requestInfoHelper);
    }

    protected String encrypt(String input) {
        return encryption.encrypt(input, secret);
    }

    protected String decrypt(String input) {
        return encryption.decrypt(input, secret);
    }

}
