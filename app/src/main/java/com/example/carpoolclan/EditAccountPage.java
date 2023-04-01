package com.example.carpoolclan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditAccountPage extends AppCompatActivity {

    AccountManagementController accountManagement = new AccountManagementController();
    TextView manageAccountPageRedirect;
    EditText editName, editDOB, editPassword;
    Button confirmEdits;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account_page);

        manageAccountPageRedirect = findViewById(R.id.manage_account_page_redirect);
        editName = findViewById(R.id.edit_name);
        editDOB = findViewById(R.id.edit_dob);
        editPassword = findViewById(R.id.edit_password);
        confirmEdits = findViewById(R.id.confirm_edits_button);

        manageAccountPageRedirect.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(EditAccountPage.this);
            builder.setMessage("Are you sure you want to leave this page? Any changes you made will not be saved.");
            builder.setTitle("Unsaved Changes");
            builder.setCancelable(false);

            builder.setPositiveButton("Yes", (dialog, which) -> {
                Toast.makeText(getApplicationContext(), "Your changes have not been saved", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EditAccountPage.this, ManageAccountPage.class);
                startActivity(intent);
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

        confirmEdits.setOnClickListener(view -> {
            // NOTE TO SELF: missing the check if users did not write in any of the fields
            if (accountManagement.validateEdits()) {
                Toast.makeText(getApplicationContext(), "Your changes have been saved", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EditAccountPage.this, ManageAccountPage.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "There was an error with your changes", Toast.LENGTH_LONG).show();
            }
        });
    }
}