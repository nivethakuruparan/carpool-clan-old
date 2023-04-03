package com.example.carpoolclan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class WeatherWizardPage extends AppCompatActivity {
    SwitchMaterial front_left, back_left, front_right, back_right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_wizard_page);

        front_left = findViewById(R.id.front_left);
        back_left = findViewById(R.id.back_left);
        front_right = findViewById(R.id.front_right);
        back_right = findViewById(R.id.back_right);

        handleWindowToggleButtons(front_left);
        handleWindowToggleButtons(back_left);
        handleWindowToggleButtons(front_right);
        handleWindowToggleButtons(back_right);
    }

    public void handleWindowToggleButtons(SwitchMaterial toggle) {
        toggle.setOnCheckedChangeListener((compoundButton, b) -> {
            String display_text;
            if (b) {
                display_text = toggle.getTextOn().toString();
            } else {
                display_text = toggle.getTextOff().toString();
            }
            Snackbar snackbar = Snackbar
                    .make(toggle, display_text, Snackbar.LENGTH_LONG);
            snackbar.show();
        });
    }
}