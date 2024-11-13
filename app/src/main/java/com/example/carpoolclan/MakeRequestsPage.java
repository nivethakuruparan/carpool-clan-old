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

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MakeRequestsPage extends AppCompatActivity {
    private final Map<String, String> userInfo;
    SessionController session;
    DispatcherController dispatcher;
    TextView homePageRedirect;
    EditText numPassengers;
    AutoCompleteTextView dropDownMenu, startingLocation, destination;
    Button confirmMakeRequest;
    String[] filterOptions = {"Shortest Time", "Number of Passengers", "Lowest Fare"};
    String filter;
    Random rand = new Random();
    public MakeRequestsPage() {
        session = new SessionController();
        userInfo = session.getUserInfo();
    }
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


        // validate user request
        confirmMakeRequest.setOnClickListener(view -> {
            Boolean isValidated;
            if (!dispatcher.checkEmptyFields(startingLocation) | !dispatcher.checkEmptyFields(destination) | !dispatcher.checkEmptyFields(numPassengers) | !dispatcher.checkEmptyFields(dropDownMenu)){
                // startingLocation, destination, numPassengers, filter cannot be empty
                isValidated = false;
            } else {
                // validate request; 2 <= numPassengers <= capacity (4)
                isValidated = dispatcher.validateUserInput(numPassengers);
            }

            // redirect to generate offers page
            if (isValidated) {
                String request_id = String.valueOf(getID());
                String start_text = startingLocation.getText().toString();
                String destination_text = destination.getText().toString();
                String num_passengers_text = numPassengers.getText().toString();

                String current_time = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    current_time = ZonedDateTime
                            .now(ZoneId.systemDefault())
                            .format(DateTimeFormatter.ofPattern("uuuu.MM.dd.HH.mm.ss"));
                }

                session.storeRequestData(request_id, userInfo.get("email"), current_time, start_text, destination_text, num_passengers_text, filter);
                Toast.makeText(getApplicationContext(), "Successfully Created a Request", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MakeRequestsPage.this, GenerateOffersPage.class);

                intent.putExtra("requestID", request_id);
                intent.putExtra("customerID", userInfo.get("email"));
                intent.putExtra("time", current_time);
                intent.putExtra("start", start_text);
                intent.putExtra("destination", destination_text);
                intent.putExtra("numPassengers", num_passengers_text);
                intent.putExtra("filter", filter);

                startActivity(intent);
            }
        });
    }
    public int getID() {
        // return random 6 digit number for request id
        return rand.nextInt(999999-111111)+111111;
    }
}