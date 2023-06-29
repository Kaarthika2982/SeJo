package com.example.sejo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class get_started extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        Button button1 = findViewById(R.id.button2);
        Button button2 = findViewById(R.id.button3);

        button1.setOnClickListener(view -> {
            Intent intent = new Intent(get_started.this, Login.class);
            startActivity(intent);
        });

        button2.setOnClickListener(view -> {
            Intent intent = new Intent(get_started.this, Register.class);
            startActivity(intent);
        });
    }
}