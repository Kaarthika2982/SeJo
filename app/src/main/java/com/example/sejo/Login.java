package com.example.sejo;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sejo-41297-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button = findViewById(R.id.button4);
        final EditText editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        final EditText editTextTextPassword = findViewById(R.id.editTextTextPassword);

        button.setOnClickListener(v -> {
            final String emailTxt = editTextTextEmailAddress.getText().toString().trim();
            final String passwordTxt = editTextTextPassword.getText().toString().trim();

            if (emailTxt.isEmpty() || passwordTxt.isEmpty()) {
                Toast.makeText(Login.this, "Please enter your Email or Password", Toast.LENGTH_SHORT).show();
            } else {
                databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        boolean isUserRegistered = false;
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            String userEmail = childSnapshot.getKey();
                            if (userEmail != null) {
                                userEmail = userEmail.replace("-", ".");
                                if (userEmail.equals(emailTxt)) {
                                    isUserRegistered = true;
                                    String getPassword = childSnapshot.child("password").getValue(String.class);
                                    if (getPassword != null && getPassword.equals(passwordTxt)) {
                                        Toast.makeText(Login.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Login.this, Job.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(Login.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                    }
                                    break;
                                }
                            }
                        }
                        if (!isUserRegistered) {
                            Toast.makeText(Login.this, "User not registered", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Login.this, "Database Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
