package com.example.carpoolclan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class HomePage extends AppCompatActivity {
    private final Map<String, String> userInfo = new HashMap<>();

    TripDurationController tripDuration;
    TextView manageAccountPageRedirect, tripStatus, customerType, customerID, tripTime, numPassengers, fare, destination;
    LinearLayout customerTypeText, tripTimeText, numPassengersText, fareText;
    Button makeRequestsPageRedirect, makeOffersPageRedirect, showRoute, incomingRequestsPageRedirect, managePlaylistPageRedirect, weatherWizardPageRedirect, ratingPageRedirect;

    public HomePage() {
        this.tripDuration = new TripDurationController();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getUserData();

        // setting up IDs from XML
        manageAccountPageRedirect = findViewById(R.id.manage_account_page_redirect);
        tripStatus = findViewById(R.id.trip_status);
        customerType = findViewById(R.id.customer_type);
        customerID = findViewById(R.id.customer_ID);
        tripTime = findViewById(R.id.trip_time);
        numPassengers = findViewById(R.id.num_passengers);
        fare = findViewById(R.id.fare);
        destination = findViewById(R.id.destination);

        customerTypeText = findViewById(R.id.customer_type_text);
        tripTimeText = findViewById(R.id.trip_time_text);
        numPassengersText = findViewById(R.id.num_passengers_text);
        fareText = findViewById(R.id.fare_text);

        makeRequestsPageRedirect = findViewById(R.id.make_requests_page_redirect);
        makeOffersPageRedirect = findViewById(R.id.make_offers_page_redirect);
        showRoute = findViewById(R.id.show_potential_route);
        incomingRequestsPageRedirect = findViewById(R.id.incoming_requests_page_redirect);
        managePlaylistPageRedirect = findViewById(R.id.mixmaster_page_redirect);
        weatherWizardPageRedirect = findViewById(R.id.weatherwizard_redirect);
        ratingPageRedirect = findViewById(R.id.rating_form_page_redirect);

        // let users go back to the manage account page, normally
        manageAccountPageRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(HomePage.this, ManageAccountPage.class);
            putUserData(intent);
            startActivity(intent);
        });

        // let users go back to the make offer page, normally
        makeOffersPageRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(HomePage.this, MakeOffersPage.class);
            putUserData(intent);
            startActivity(intent);
        });

        // let users go back to the make requests page, normally
        makeRequestsPageRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(HomePage.this, MakeRequestsPage.class);
            putUserData(intent);
            startActivity(intent);
        });

        // let users view their current route
        showRoute.setOnClickListener(view -> {
            Intent intent = new Intent(HomePage.this, GoogleMapsInterface.class);
            intent.putExtra("polyLine", tripDuration.getPolyLine());
            startActivity(intent);
        });

        // let users go back to the incoming requests page, normally
        incomingRequestsPageRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(HomePage.this, IncomingRequestsPage.class);
            startActivity(intent);
        });

        // let users go back to the weatherWizard page, normally
        weatherWizardPageRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(HomePage.this, WeatherWizardPage.class);
            startActivity(intent);
        });

        // let users go back to the manage playlist page, normally
        managePlaylistPageRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(HomePage.this, ManagePlaylistPage.class);
            startActivity(intent);
        });

        // let users go to the rating form page; display an alert to notify about the inability to go back
        ratingPageRedirect.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(HomePage.this);
            builder.setMessage("Are you sure your trip has ended? Once you click this button all trip information will be lost.");
            builder.setTitle("End Trip");
            builder.setCancelable(false);

            builder.setPositiveButton("Yes", (dialog, which) -> {
                Toast.makeText(getApplicationContext(), "Thank you for riding with Carpool Clan. Please take the time to rate your fellow commuters!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(HomePage.this, RatingFormPage.class);
                startActivity(intent);
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

        // handle the home page display
        if (!tripDuration.getTripStatus()) {
            tripStatus.setVisibility(View.VISIBLE);
            customerTypeText.setVisibility(View.GONE);
            tripTimeText.setVisibility(View.GONE);
            numPassengersText.setVisibility(View.GONE);
            fareText.setVisibility(View.GONE);
            makeRequestsPageRedirect.setVisibility(View.VISIBLE);
            makeOffersPageRedirect.setVisibility(View.VISIBLE);
            showRoute.setVisibility(View.GONE);
            incomingRequestsPageRedirect.setVisibility(View.GONE);
            managePlaylistPageRedirect.setVisibility(View.GONE);
            weatherWizardPageRedirect.setVisibility(View.GONE);
            ratingPageRedirect.setVisibility(View.GONE);
        } else {
            tripStatus.setVisibility(View.GONE);
            customerTypeText.setVisibility(View.VISIBLE);
            tripTimeText.setVisibility(View.VISIBLE);
            numPassengersText.setVisibility(View.VISIBLE);
            fareText.setVisibility(View.VISIBLE);
            makeRequestsPageRedirect.setVisibility(View.GONE);
            makeOffersPageRedirect.setVisibility(View.GONE);
            showRoute.setVisibility(View.VISIBLE);
            managePlaylistPageRedirect.setVisibility(View.VISIBLE);
            weatherWizardPageRedirect.setVisibility(View.VISIBLE);
            ratingPageRedirect.setVisibility(View.VISIBLE);

            // differentiate requester and offerer
            if (tripDuration.getCustomerType().equals("offerer")) {
                incomingRequestsPageRedirect.setVisibility(View.VISIBLE);
                customerType.setText("Offerer ID:");
            } else if (tripDuration.getCustomerType().equals("requester")){
                customerType.setText("Requester ID:");
            }

            // get trip details
            customerID.setText("390292");
            tripTime.setText("12:00");
            numPassengers.setText("3");
            fare.setText("$10.21");
            destination.setText("1280 Main St W, Hamilton, ON L8S 4L8");
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