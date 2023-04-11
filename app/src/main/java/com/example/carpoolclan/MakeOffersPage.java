package com.example.carpoolclan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class MakeOffersPage extends AppCompatActivity {
    private final Map<String, String> userInfo = new HashMap<>();
    DispatcherController dispatcher;
    Button scanQRCode, confirmMakeOffer;
    TextView homePageRedirect, textQRCode;
    String finalTaxiCode;
    AutoCompleteTextView startingLocation, destination;
    EditText numPassengers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_offers_page);
        getUserData();

        //initializing dispatcher
        dispatcher = new DispatcherController();

        // initialize variables with corresponding IDs
        homePageRedirect = findViewById(R.id.home_page_redirect);
        scanQRCode = findViewById(R.id.scan_taxi_code);
        textQRCode = findViewById(R.id.make_offer_taxi_code);
        startingLocation = findViewById(R.id.make_offer_starting_location);
        destination = findViewById(R.id.make_offer_destination);
        numPassengers = findViewById(R.id.make_offer_passengers);
        confirmMakeOffer = findViewById(R.id.confirm_make_offer_button);

        // initialize as empty string to store taxi code; since taxiQRCode has the form: "Taxi Code: {taxi_id}"
        finalTaxiCode = "";

        // let users go back to the home page; display an alert to notify about unsaved changes
        homePageRedirect.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MakeOffersPage.this);
            builder.setMessage("Are you sure you want to leave this page? Any changes you made will not be saved.");
            builder.setTitle("Unsaved Changes");
            builder.setCancelable(false);

            builder.setPositiveButton("Yes", (dialog, which) -> {
                Toast.makeText(getApplicationContext(), "Your changes have not been saved", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MakeOffersPage.this, HomePage.class);
                putUserData(intent);
                startActivity(intent);
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

        // let users scan QR code and displays output on screen
        scanQRCode.setOnClickListener(view -> {
            IntentIntegrator i = new IntentIntegrator(MakeOffersPage.this);
            i.setOrientationLocked(true);
            i.setPrompt("Scan Taxi QR Code");
            i.setDesiredBarcodeFormats(i.QR_CODE);
            i.initiateScan();
        });

        // allowing autofill in order to retrieve a valid location
        startingLocation.setAdapter(new LocationAutoComplete(MakeOffersPage.this, android.R.layout.simple_list_item_1));
        destination.setAdapter(new LocationAutoComplete(MakeOffersPage.this, android.R.layout.simple_list_item_1));

        // handling confirm make offer button click
        confirmMakeOffer.setOnClickListener(view -> {
            Boolean isValidated;
            if (!dispatcher.checkEmptyFields(textQRCode) | !dispatcher.checkEmptyFields(startingLocation) | !dispatcher.checkEmptyFields(destination) | !dispatcher.checkEmptyFields(numPassengers)){
                // no fields can be empty
                isValidated = false;
            } else {
                // validate offer; 0 <= numPassengers <= capacity (4)
                int num_passengers = Integer.getInteger(numPassengers.getText().toString());
                isValidated = dispatcher.validateUserInput(num_passengers);
            }

            // redirect to incoming request page? or home page with the state that users have successfully made an offer
            if (isValidated) {
                String id_text = finalTaxiCode;
                String start_text = startingLocation.getText().toString();
                String destination_text = destination.getText().toString();
                String num_passengers_text = numPassengers.getText().toString();

                SessionController session = new SessionController();
                String current_time = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    current_time = ZonedDateTime
                            .now(ZoneId.systemDefault())
                            .format(DateTimeFormatter.ofPattern("uuuu.MM.dd.HH.mm.ss"));
                }
                session.storeOfferData(id_text, userInfo.get("email"), current_time, start_text, destination_text, num_passengers_text);
                Toast.makeText(getApplicationContext(), "Successfully Created an Offer", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MakeOffersPage.this, MakeOffersPage.class);
                putUserData(intent);
                startActivity(intent);
            }
        });
    }

    // helper method for QR Code Scanning
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult i = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (i != null) {
            String contents = i.getContents();
            if (contents != null) {
                textQRCode.setText("TAXI CODE: " + i.getContents()); // displays code to users
                finalTaxiCode = i.getContents(); // stores code as a String
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void getUserData() {
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

    public void putUserData(Intent intent) {
        intent.putExtra("name", userInfo.get("name"));
        intent.putExtra("email", userInfo.get("email"));
        intent.putExtra("dob", userInfo.get("dob"));
        intent.putExtra("password", userInfo.get("password"));
    }
}