package com.example.carpoolclan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MakeRequestsPage extends AppCompatActivity {

    DispatcherController dispatcher;
    TextView homePageRedirect;
    EditText numPassengers;
    AutoCompleteTextView dropDownMenu, startingLocation, destination;
    Button confirmMakeRequest;
    String[] filterOptions = {"Shortest Time", "Number of Passengers", "Lowest Fare"};
    String filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_requests_page);

        //initializing dispatcher
        dispatcher = new DispatcherController();

        // initialize variables with corresponding IDs
        dropDownMenu = findViewById(R.id.filled_exposed);
        homePageRedirect = findViewById(R.id.home_page_redirect);
        startingLocation = findViewById(R.id.make_request_starting_location);
        destination = findViewById(R.id.make_request_destination);
        numPassengers = findViewById(R.id.make_request_passengers);
        confirmMakeRequest = findViewById(R.id.confirm_make_request_button);

        // initialize as empty string to store filter selection
        filter = "";

        // setting up the drop down menu display
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MakeRequestsPage.this,
                android.R.layout.simple_spinner_dropdown_item, filterOptions);
        dropDownMenu.setAdapter(adapter);

        // handling clicks to the drop down menu; saving result to filter
        dropDownMenu.setOnItemClickListener((adapterView, view, i, l) -> filter = dropDownMenu.getText().toString());

        // let users go back to the home page; display an alert to notify about unsaved changes
        homePageRedirect.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MakeRequestsPage.this);
            builder.setMessage("Are you sure you want to leave this page? Any changes you made will not be saved.");
            builder.setTitle("Unsaved Changes");
            builder.setCancelable(false);

            builder.setPositiveButton("Yes", (dialog, which) -> {
                Toast.makeText(getApplicationContext(), "Your changes have not been saved", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MakeRequestsPage.this, HomePage.class);
                startActivity(intent);
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

        // allowing autofill in order to retrieve a valid location
        startingLocation.setAdapter(new LocationAutoComplete(MakeRequestsPage.this, android.R.layout.simple_list_item_1));
        destination.setAdapter(new LocationAutoComplete(MakeRequestsPage.this, android.R.layout.simple_list_item_1));

        // handling confirm find offers button click
        confirmMakeRequest.setOnClickListener(view -> {
            // CODE: once confirm make offer button has been made
            // NOTE: remember to check for empty fields
            System.out.println("Clicking Make Request");
        });
    }
}