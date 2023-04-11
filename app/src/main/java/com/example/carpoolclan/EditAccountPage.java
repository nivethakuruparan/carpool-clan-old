package com.example.carpoolclan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EditAccountPage extends AppCompatActivity {
    private final Map<String, String> userInfo = new HashMap<>();
    AccountManagementController accountManagement = new AccountManagementController();
    TextView manageAccountPageRedirect;
    EditText editName, editPassword;
    DatePickerDialog datePickerDialog;
    Button editDOB, confirmEdits;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account_page);
        getUserData();
        initDatePicker();

        manageAccountPageRedirect = findViewById(R.id.manage_account_page_redirect);
        editName = findViewById(R.id.edit_name);
        editDOB = findViewById(R.id.edit_dob);
        editPassword = findViewById(R.id.edit_password);
        confirmEdits = findViewById(R.id.confirm_edits_button);

        editName.setText(userInfo.get("name"));
        editDOB.setText(userInfo.get("dob"));
        editPassword.setText(userInfo.get("password"));

        manageAccountPageRedirect.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(EditAccountPage.this);
            builder.setMessage("Are you sure you want to leave this page? Any changes you made will not be saved.");
            builder.setTitle("Unsaved Changes");
            builder.setCancelable(false);

            builder.setPositiveButton("Yes", (dialog, which) -> {
                Toast.makeText(getApplicationContext(), "Your changes have not been saved", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EditAccountPage.this, ManageAccountPage.class);
                putUserData(intent);
                startActivity(intent);
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

        confirmEdits.setOnClickListener(view -> {
            Boolean isValidated;
            if (!accountManagement.checkEmptyFields(editName) | !accountManagement.checkEmptyFields(editDOB) | !accountManagement.checkEmptyFields(editPassword)){
                // check for any empty fields
                isValidated = false;
            } else {
                // validate user input
                isValidated = accountManagement.validateEdits(editName, editDOB, editPassword);
            }
            if (isValidated) {
                // all inputs valid, store updated data
                String name = editName.getText().toString();
                String dob = editDOB.getText().toString();
                String password = editPassword.getText().toString();

                SessionController session = new SessionController();
                session.storeRegistrationData(name, userInfo.get("email"), dob, password);
                // display success message and redirect to manage account page
                userInfo.put("name", name);
                userInfo.put("dob", dob);
                userInfo.put("password", password);

                Toast.makeText(getApplicationContext(), "Your changes have been saved", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EditAccountPage.this, ManageAccountPage.class);
                putUserData(intent);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "There was an error with your changes", Toast.LENGTH_LONG).show();
            }
        });
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

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month += 1;
            String date = makeDateString(day, month, year);
            editDOB.setText(date);
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = android.app.AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        switch (month) {
            case 1:
                return "JAN";
            case 2:
                return "FEB";
            case 3:
                return "MAR";
            case 4:
                return "APR";
            case 5:
                return "MAY";
            case 6:
                return "JUN";
            case 7:
                return "JUL";
            case 8:
                return "AUG";
            case 9:
                return "SEP";
            case 10:
                return "OCT";
            case 11:
                return "NOV";
            case 12:
                return "DEC";
            default:
                return "JAN"; // will never occur
        }
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }
}