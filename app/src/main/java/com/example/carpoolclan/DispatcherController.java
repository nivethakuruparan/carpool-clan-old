package com.example.carpoolclan;

import android.widget.EditText;
import android.widget.TextView;

public class DispatcherController {
    // Checks if provided EditText is empty
    public Boolean checkEmptyFields(EditText field) {
        String text = field.getText().toString();

        if (text.isEmpty()) {
            field.setError("Field cannot be empty!");
            return false;
        }
        field.setError(null);
        return true;
    }

    public Boolean checkEmptyQRCode(TextView field, String qr_code) {
        if (qr_code.isEmpty()) {
            field.setError("Field cannot be empty!");
            return false;
        }
        field.setError(null);
        return true;
    }

    // validate offer; (1) taxiCode exists (2) taxiCode is not being in use,
    // (3) destination exists (4) numPassengers < capacity of taxi
    public Boolean validateMakeOffer(String taxiQRCode, EditText destination, EditText numPassengers){
        System.out.println("Validating Make Offer");
        return true;
    }


}
