package com.example.sejo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");

        final EditText editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        final EditText editTextTextEmailAddress2 = findViewById(R.id.editTextTextEmailAddress2);
        final EditText editTextPhone = findViewById(R.id.editTextPhone);
        final EditText editTextTextPassword2 = findViewById(R.id.editTextTextPassword2);
        final EditText editTextTextPassword3 = findViewById(R.id.editTextTextPassword3);
        final Button button5 = findViewById(R.id.button5);

        button5.setOnClickListener(v -> {
            String name = editTextTextPersonName.getText().toString().trim();
            String email = editTextTextEmailAddress2.getText().toString().trim();
            String phone = editTextPhone.getText().toString().trim();
            String password = editTextTextPassword2.getText().toString();
            String confirmPassword = editTextTextPassword3.getText().toString();

            // Validate email
            if (!isValidEmail(email)) {
                Toast.makeText(Register.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validate password criteria
            if (!isValidPassword(password)) {
                Toast.makeText(Register.this, "Please enter a valid password! The password must contain Capital alphabet, small alphabet, number and special characters with the total password length between 6 to 16 characters.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validate phone number
            if (!isValidPhoneNumber(phone)) {
                Toast.makeText(Register.this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check if passwords match
            if (!password.equals(confirmPassword)) {
                Toast.makeText(Register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Modify the email address to make it valid for the database path
            String modifiedEmail = email.replace(".", "-");

            // Check if email is already registered
            databaseReference.child(modifiedEmail).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        Toast.makeText(Register.this, "Email is already registered", Toast.LENGTH_SHORT).show();
                    } else {
                        // Store user details in Firebase
                        User user = new User(name, email, phone, password);
                        databaseReference.child(modifiedEmail).setValue(user);

                        Toast.makeText(Register.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Register.this, "Registration failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z\\d._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }

    private boolean isValidPassword(String password) {
        String passwordPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,16}$";
        return password.matches(passwordPattern);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10}");
    }

    // User class for storing user details
    public static class User {
        public String name;
        public String email;
        public String phone;
        public String password;

        public User(String name, String email, String phone, String password) {
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.password = password;
        }
    }
}