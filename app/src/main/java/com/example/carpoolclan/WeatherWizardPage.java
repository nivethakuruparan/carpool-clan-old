package com.example.carpoolclan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.slider.Slider;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class WeatherWizardPage extends AppCompatActivity {

    WeatherWizardController weatherWizard = new WeatherWizardController();
    SwitchMaterial frontLeft, backLeft, frontRight, backRight;
    TextView homePageRedirect, temperature;
    Slider temperatureSlider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_wizard_page);

        homePageRedirect = findViewById(R.id.home_page_redirect);

        frontLeft = findViewById(R.id.front_left);
        backLeft = findViewById(R.id.back_left);
        frontRight = findViewById(R.id.front_right);
        backRight = findViewById(R.id.back_right);

        temperature = findViewById(R.id.current_temperature);
        temperatureSlider = findViewById(R.id.temperature_slider);

        homePageRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(WeatherWizardPage.this, HomePage.class);
            startActivity(intent);
        });

        // displays the current temperature on to the screen
        temperatureSlider.addOnChangeListener((slider, value, fromUser) -> temperature.setText("Current Temperature: " + value + "°C"));

        // displays the current status of the windows
        weatherWizard.handleWindowToggleButtons(frontLeft);
        weatherWizard.handleWindowToggleButtons(backLeft);
        weatherWizard.handleWindowToggleButtons(frontRight);
        weatherWizard.handleWindowToggleButtons(backRight);
    }
}