package com.example.carpoolclan;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

public class SessionController {
    DatabaseReference reference;
    final String secret;
    EncryptionController encryption;
    private static Boolean tripStatus; // false for no trip; true for current trip
    private static String customerType; // "offerer, requester"
    private static String polyLine; // current polyLine being used on the trip

    public SessionController() {
        secret = "design";
        encryption = new EncryptionController();
        tripStatus = true;
        customerType = "offerer";
        polyLine = "{qagGt_`gNmDc@`@sFbA_Oh@iIn@{JJmAjAeHDOGKi@{@cAuAUKWOgAe@oH_D_E_Ba@[iA{AcA_Bo@aAoAeB{C}DiAoAm@q@w@kA[}@";
    }

    protected void storeRegistrationData(String name, String email, String dob, String password) {
        reference = FirebaseDatabase.getInstance().getReference("customers");
        AccountInfoHelper accountInfoHelper = new AccountInfoHelper(encrypt(name), encrypt(email), encrypt(dob), encrypt(password));
        reference.child(accountInfoHelper.email).setValue(accountInfoHelper);
    }

    protected void storeOfferData(String taxi_id, String destination, String num_passengers) {
        reference = FirebaseDatabase.getInstance().getReference("offers");
        OfferInfoHelper offerInfoHelper = new OfferInfoHelper(encrypt(taxi_id), encrypt(destination), encrypt(num_passengers));
        reference.child(offerInfoHelper.id).setValue(offerInfoHelper);
    }

    protected void storeRequestData(int id, String start, String destination, String num_passengers, String filter) {
        reference = FirebaseDatabase.getInstance().getReference("requests");
        RequestInfoHelper requestInfoHelper = new RequestInfoHelper(encrypt(String.valueOf(id)), encrypt(start), encrypt(destination), encrypt(num_passengers), encrypt(filter));
        reference.child(String.valueOf(requestInfoHelper.id)).setValue(requestInfoHelper);
    }

    protected String encrypt(String input) {
        return encryption.encrypt(input, secret);
    }

    protected String decrypt(String input) {
        return encryption.decrypt(input, secret);
    }

    public static Boolean getTripStatus() {
        return tripStatus;
    }

    public static void setTripStatus(Boolean tripStatus) {
        SessionController.tripStatus = tripStatus;
    }

    public static String getCustomerType() {
        return customerType;
    }

    public static void setCustomerType(String customerType) {
        SessionController.customerType = customerType;
    }
    public static String getPolyLine() {
        return polyLine;
    }

    public static void setPolyLine(String polyLine) {
        SessionController.polyLine = polyLine;
    }
}

