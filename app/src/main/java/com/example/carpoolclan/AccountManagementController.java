package com.example.carpoolclan;

import android.widget.EditText;

public class AccountManagementController {

    // Checks if provided string is empty
    public Boolean checkEmptyFields(EditText field) {
        String text = field.getText().toString();

        if (text.isEmpty()) {
            field.setError("Field cannot be empty!");
            return false;
        }
        field.setError(null);
        return true;
    }

    public Boolean validateLogin(EditText email, EditText password) {
        System.out.println("Validating Login");
        return true;
    }
}
