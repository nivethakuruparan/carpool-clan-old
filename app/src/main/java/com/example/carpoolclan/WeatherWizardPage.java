package com.example.carpoolclan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.slider.Slider;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class WeatherWizardPage extends AppCompatActivity {
    TripDurationController tripDurationController;
    WeatherWizardController weatherWizard;
    SessionController session;
    SwitchMaterial frontLeft, backLeft, frontRight, backRight;
    boolean frontLeftOpen, backLeftOpen, frontRightOpen, backRightOpen;
    TextView homePageRedirect, temperature;
    Slider temperatureSlider;
    public WeatherWizardPage() {
        weatherWizard = new WeatherWizardController();
        tripDurationController = new TripDurationController();
        session = new SessionController();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_wizard_page);

        homePageRedirect = findViewById(R.id.home_page_redirect);

        frontLeft = findViewById(R.id.front_left);
        backLeft = findViewById(R.id.back_left);
        frontRight = findViewById(R.id.front_right);
        backRight = findViewById(R.id.back_right);

        frontLeftOpen = false;
        backLeftOpen = false;
        frontRightOpen = false;
        backRightOpen = false;

        temperature = findViewById(R.id.current_temperature);
        temperatureSlider = findViewById(R.id.temperature_slider);

        homePageRedirect.setOnClickListener(view -> {
            String front_left_text = String.valueOf(frontLeftOpen);
            String back_left_text = String.valueOf(backLeftOpen);
            String front_right_text = String.valueOf(frontRightOpen);
            String back_right_text = String.valueOf(backRightOpen);
            String temperature_text = temperature.getText().toString();

            session.storeWeatherWizardData(tripDurationController.getTripID(), front_left_text, back_left_text, front_right_text, back_right_text, temperature_text);
            Intent intent = new Intent(WeatherWizardPage.this, HomePage.class);
            startActivity(intent);
        });

        // displays the current temperature on to the screen
        temperatureSlider.addOnChangeListener((slider, value, fromUser) -> temperature.setText("Current Temperature: " + value + "°C"));

        // displays the current status of the windows
        weatherWizard.handleWindowToggleButtons(frontLeft, frontLeftOpen);
        weatherWizard.handleWindowToggleButtons(backLeft, backLeftOpen);
        weatherWizard.handleWindowToggleButtons(frontRight, frontRightOpen);
        weatherWizard.handleWindowToggleButtons(backRight, backRightOpen);
    }
}