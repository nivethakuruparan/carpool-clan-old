package com.example.carpoolclan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MakeOffersPage extends AppCompatActivity {

    DispatcherController dispatcher = new DispatcherController();
    Button scanQRCode, confirmMakeOffer;
    TextView homePageRedirect;
    TextView textQRCode;
    String finalTaxiCode;
    EditText destination, numPassengers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_offers_page);

        // initialize variables with corresponding IDs
        homePageRedirect = findViewById(R.id.home_page_redirect);
        scanQRCode = findViewById(R.id.scan_taxi_code);
        textQRCode = findViewById(R.id.make_offer_taxi_code);
        destination = findViewById(R.id.make_offer_destination);
        numPassengers = findViewById(R.id.make_offer_passengers);
        confirmMakeOffer = findViewById(R.id.confirm_make_offer_button);

        finalTaxiCode = ""; // initialize as empty string to store taxi code

        // let users go back to the home page; display an alert to notify about unsaved changes
        homePageRedirect.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MakeOffersPage.this);
            builder.setMessage("Are you sure you want to leave this page? Any changes you made will not be saved.");
            builder.setTitle("Unsaved Changes");
            builder.setCancelable(false);

            builder.setPositiveButton("Yes", (dialog, which) -> {
                Toast.makeText(getApplicationContext(), "Your changes have not been saved", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MakeOffersPage.this, HomePage.class);
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

        // let users validate their offers
        confirmMakeOffer.setOnClickListener(view -> {
            Boolean isValidated;
            if (!dispatcher.checkEmptyQRCode(textQRCode, finalTaxiCode) | !dispatcher.checkEmptyFields(destination) | !dispatcher.checkEmptyFields(numPassengers)){
                // taxiQRCode, destination, and numPassengers cannot be empty
                isValidated = false;
            } else {
                // validate offer; (1) taxiCode exists (2) taxiCode is not being in use,
                // (3) destination exists (4) numPassengers < capacity of taxi
                isValidated = dispatcher.validateMakeOffer(finalTaxiCode, destination, numPassengers);
            }

            // redirect to incoming request page? or home page with the state that users have successfully made an offer
            if (isValidated) {
                Toast.makeText(getApplicationContext(), "Successfully Created an Offer", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MakeOffersPage.this, HomePage.class);
                startActivity(intent);
            }
        });
    }

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
}