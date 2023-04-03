package com.example.carpoolclan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ManageAccountPage extends AppCompatActivity {

    AccountManagementController accountManagement = new AccountManagementController();
    TextView homePageRedirect;
    Button editAccountRedirect, deleteAccountButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account_page);

        homePageRedirect = findViewById(R.id.home_page_redirect);
        editAccountRedirect = findViewById(R.id.edit_account_page_redirect);
        deleteAccountButton = findViewById(R.id.delete_account_button);

        homePageRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(ManageAccountPage.this, HomePage.class);
            startActivity(intent);
        });

        editAccountRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(ManageAccountPage.this, EditAccountPage.class);
            startActivity(intent);
        });

        deleteAccountButton.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(ManageAccountPage.this);
            builder.setMessage("Are you sure you want to delete your account? If you delete your account, you will permanently lose all of your information.");
            builder.setTitle("Delete Account");
            builder.setCancelable(false);

            builder.setPositiveButton("Delete Account", (dialog, which) -> {
                if (accountManagement.deleteAccount()) {
                    Toast.makeText(getApplicationContext(), "Successfully Deleted Account", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ManageAccountPage.this, WelcomePage.class);
                    startActivity(intent);
                }
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }
}