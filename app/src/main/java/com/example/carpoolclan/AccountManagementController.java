package com.example.carpoolclan;

import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountManagementController {

    // Checks if provided EditText is empty
    public Boolean checkEmptyFields(TextView field) {
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

    public Boolean validateRegistration(EditText name, EditText email, Button dob, EditText password) {
        System.out.println("Validating Registration");
        String name_text = name.getText().toString();
        String email_text = email.getText().toString();
        String dob_text = dob.getText().toString();
        String password_text = password.getText().toString();

        // name validation

        // email validation
        String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email_text);
        if (!matcher.matches()) {
            email.setError("Please enter a valid e-mail");
            return false;
        }
        SessionController session = new SessionController();
//        if(session.checkEmailExistence(email_text)) {
//            email.setError("An account with this e-mail already exists.");
//            return false;
//        }
        name.setError(null);
        email.setError(null);
        dob.setError(null);
        password.setError(null);
        return true;
    }

    public Boolean deleteAccount() {
        System.out.println("Deleting Account");
        return true;
    }

    public Boolean validateEdits() {
        System.out.println("Validating Edits");
        return true;
    }
}
