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

import java.util.HashMap;
import java.util.Map;

public class ManageAccountPage extends AppCompatActivity {
    private final Map<String, String> userInfo = new HashMap<>();
    AccountManagementController accountManagement = new AccountManagementController();
    TextView accountName, accountEmail, accountDOB, accountPassword, homePageRedirect;
    Button editAccountRedirect, deleteAccountButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account_page);
        getUserData();

        accountName = findViewById(R.id.account_name);
        accountEmail = findViewById(R.id.account_email);
        accountDOB = findViewById(R.id.account_dob);
        accountPassword = findViewById(R.id.account_password);

        accountName.setText(userInfo.get("name"));
        accountEmail.setText(userInfo.get("email"));
        accountDOB.setText(userInfo.get("dob"));
        accountPassword.setText(userInfo.get("password"));

        homePageRedirect = findViewById(R.id.home_page_redirect);
        editAccountRedirect = findViewById(R.id.edit_account_page_redirect);
        deleteAccountButton = findViewById(R.id.delete_account_button);

        homePageRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(ManageAccountPage.this, HomePage.class);
            putUserData(intent);
            startActivity(intent);
        });

        editAccountRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(ManageAccountPage.this, EditAccountPage.class);
            putUserData(intent);
            startActivity(intent);
        });

        deleteAccountButton.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(ManageAccountPage.this);
            builder.setMessage("Are you sure you want to delete your account? If you delete your account, you will permanently lose all of your information.");
            builder.setTitle("Delete Account");
            builder.setCancelable(false);

            builder.setPositiveButton("Delete Account", (dialog, which) -> {
                if (accountManagement.deleteAccount(userInfo.get("email"))) {
                    Toast.makeText(getApplicationContext(), "Successfully Deleted Account", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ManageAccountPage.this, WelcomePage.class);
                    putUserData(intent);
                    startActivity(intent);
                }
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
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
}