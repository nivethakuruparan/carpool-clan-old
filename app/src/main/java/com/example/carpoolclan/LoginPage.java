package com.example.carpoolclan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {

    AccountManagementController accountManagement = new AccountManagementController();
    EditText loginEmail, loginPassword;
    Button loginButton;
    TextView registrationPageRedirect;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        registrationPageRedirect = findViewById(R.id.registration_page_redirect);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!accountManagement.checkEmptyFields(loginEmail) | !accountManagement.checkEmptyFields(loginPassword)) {

                } else {
                    checkCredentials();
                }
            }
        });

        registrationPageRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(LoginPage.this, RegistrationPage.class);
            startActivity(intent);
        });
    }

    public void checkCredentials() {
        String email = loginEmail.getText().toString();
        String password = loginPassword.getText().toString();
        HelperClass helperClass = new HelperClass();
        helperClass.setEmail(email);
        reference = FirebaseDatabase.getInstance().getReference("customers");
        Query checkDatabase = reference.orderByChild("email").equalTo(email);

        checkDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    loginEmail.setError(null);
                    String passwordFromDB = snapshot.child(helperClass.email).child("password").getValue(String.class);

                    if (passwordFromDB.equals(password)) {
                        loginEmail.setError(null);

                        // pass user data using intent

                        String nameFromDB = snapshot.child(helperClass.email).child("name").getValue(String.class);
                        String emailFromDB = snapshot.child(helperClass.email).child("email").getValue(String.class);
                        String dobFromDB = snapshot.child(helperClass.email).child("dob").getValue(String.class);

                        Intent intent = new Intent(LoginPage.this, HomePage.class);

                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("dob", dobFromDB);
                        intent.putExtra("password", passwordFromDB);

                        startActivity(intent);
                    } else {
                        loginPassword.setError("Invalid Credentials!");
                        loginPassword.requestFocus();
                    }
                } else {
                    loginEmail.setError("Account with this e-mail does not exist!");
                    loginEmail.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}