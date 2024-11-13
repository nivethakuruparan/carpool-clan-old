package com.example.carpoolclan;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

public class SessionController {
    DatabaseReference reference;
    final String secret;
    EncryptionController encryption;
    private static HashMap<String,String> userInfo = new HashMap<>();
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

    protected void storeOfferData(String taxi_id, String customer_id, String time, String start, String destination, String num_passengers) {
        reference = FirebaseDatabase.getInstance().getReference("offers");
        OfferInfoHelper offerInfoHelper = new OfferInfoHelper(encrypt(taxi_id), encrypt(customer_id), encrypt(time), encrypt(start), encrypt(destination), encrypt(num_passengers));
        reference.child(offerInfoHelper.taxiID).setValue(offerInfoHelper);
    }

    protected void storeRequestData(String request_id, String customer_id, String time, String start, String destination, String num_passengers, String filter) {
        reference = FirebaseDatabase.getInstance().getReference("requests");
        RequestInfoHelper requestInfoHelper = new RequestInfoHelper(encrypt(request_id), encrypt(customer_id), encrypt(time), encrypt(start), encrypt(destination), encrypt(num_passengers), encrypt(filter));
        reference.child(requestInfoHelper.requestID).setValue(requestInfoHelper);
    }

    protected void storeWeatherWizardData(String taxi_id, String frontLeft, String backLeft, String frontRight, String backRight, String temp) {
        reference = FirebaseDatabase.getInstance().getReference("weatherwizard");
        WeatherWizardInfoHelper weatherWizardInfoHelper = new WeatherWizardInfoHelper(encrypt(taxi_id), encrypt(frontLeft), encrypt(backLeft), encrypt(frontRight), encrypt(backRight), encrypt(temp));
        reference.child(weatherWizardInfoHelper.taxiID).setValue(weatherWizardInfoHelper);
    }

    protected void storeMixMasterData(String tripID, SongCard songCard) {
        String index = songCard.getIndex();
        String songName = songCard.getSongName();
        String artistName = songCard.getArtistName();
        reference = FirebaseDatabase.getInstance().getReference("mixmaster");
        MixMasterInfoHelper mixMasterInfoHelper = new MixMasterInfoHelper(encrypt(tripID), encrypt(index), encrypt(songName), encrypt(artistName));
        reference.child(mixMasterInfoHelper.tripID).child(mixMasterInfoHelper.index).setValue(mixMasterInfoHelper);
    }

    protected void removeMixMasterData(String tripID, SongCard songCard) {
        String index = songCard.getIndex();
        reference = FirebaseDatabase.getInstance().getReference("mixmaster");
        MixMasterInfoHelper mixMasterInfoHelper = new MixMasterInfoHelper();
        mixMasterInfoHelper.setTripID(encrypt(tripID));
        mixMasterInfoHelper.setIndex(encrypt(index));
        reference.child(mixMasterInfoHelper.tripID).child(mixMasterInfoHelper.index).removeValue();
    }

    protected void deleteAccount(String email) {
        reference = FirebaseDatabase.getInstance().getReference("customers");
        AccountInfoHelper accountInfoHelper = new AccountInfoHelper();
        accountInfoHelper.setEmail(encrypt(email));
        reference.child(accountInfoHelper.email).removeValue();
    }

    public static void setUserInfo(String name, String email, String dob, String password) {
        userInfo.put("name", name);
        userInfo.put("email", email);
        userInfo.put("dob", dob);
        userInfo.put("password", password);
    }

    public static void updateUserInfo(String name, String dob, String password) {
        userInfo.replace("name", name);
        userInfo.replace("dob", dob);
        userInfo.replace("password", password);
    }

    public static HashMap<String, String> getUserInfo() {
        return userInfo;
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

