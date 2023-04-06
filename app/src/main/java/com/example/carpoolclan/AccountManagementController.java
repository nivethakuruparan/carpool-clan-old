package com.example.carpoolclan;

import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountManagementController {
    SessionController session = new SessionController();
    // Checks if provided TextView is empty
    public Boolean checkEmptyFields(TextView field) {
        String text = field.getText().toString();

        if (text.isEmpty()) {
            field.setError("Field cannot be empty!");
            return false;
        }
        field.setError(null);
        return true;
    }

    public Boolean validateRegistration(EditText name, EditText email, Button dob, EditText password) {
        System.out.println("Validating Registration");
        return validateName(name) & validateEmail(email) & validateDOB(dob) & validatePassword(password);
    }

    private Boolean validateName(EditText name) {
        String text = name.getText().toString();
        if (!text.contains(" ")) {
            name.setError("Please enter your full name, separated by a space");
            return false;
        }
        name.setError(null);
        return true;
    }

    private Boolean validateEmail(EditText email) {
        String text = email.getText().toString();
        String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (!matcher.matches()) {
            email.setError("Please enter a valid e-mail");
            return false;
        }
        email.setError(null);
        return true;
    }

    private Boolean validateDOB(Button dob) {
        String text = dob.getText().toString();
        String[] date = text.split(" ");
        int month = getMonthInt(date[0]);
        int day = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);

        Calendar cal = Calendar.getInstance();
        int current_year = cal.get(Calendar.YEAR);
        int current_month = cal.get(Calendar.MONTH) + 1;
        int current_day = cal.get(Calendar.DAY_OF_MONTH);

        if (year > current_year) {
            dob.setError("Please enter a valid date of birth");
            return false;
        } else if (year == current_year && month > current_month) {
            dob.setError("Please enter a valid date of birth");
            return false;
        } else if (year == current_year && month == current_month && day > current_day) {
            dob.setError("Please enter a valid date of birth");
            return false;
        }
        dob.setError(null);
        return true;
    }

    private Boolean validatePassword(EditText password) {
        String text = password.getText().toString();
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (!matcher.matches()) {
            password.setError("Password must contain at least: one uppercase letter, one lowercase letter, one digit, and one symbol");
            return false;
        }
        password.setError(null);
        return true;
    }

    public int getMonthInt(String month) {
        switch (month) {
            case "JAN":
                return 1;
            case "FEB":
                return 2;
            case "MAR":
                return 3;
            case "APR":
                return 4;
            case "MAY":
                return 5;
            case "JUN":
                return 6;
            case "JUL":
                return 7;
            case "AUG":
                return 8;
            case"SEP":
                return 9;
            case "OCT":
                return 10;
            case "NOV":
                return 11;
            case "DEC":
                return 12;
            default:
                return 1; // will never occur
        }
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
