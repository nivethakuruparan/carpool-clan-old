package com.example.carpoolclan;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class WeatherWizardController {
    public void handleWindowToggleButtons(SwitchMaterial toggle, Boolean open) {
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

            updateOpen(open);
        });
    }
    public void updateOpen(boolean open) {
        if (!open) {
            open = true;
        } else {
            open = false;
        }
    }
}
