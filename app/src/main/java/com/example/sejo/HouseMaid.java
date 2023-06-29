package com.example.sejo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HouseMaid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_maid);

        Button button1 = findViewById(R.id.button8);
        Button button2 = findViewById(R.id.button9);

        button1.setOnClickListener(view -> {
            Intent intent = new Intent(HouseMaid.this, HouseMaid_Profile.class);
            startActivity(intent);
        });

        button2.setOnClickListener(view -> {
            Intent intent = new Intent(HouseMaid.this, NeedHousemaid.class);
            startActivity(intent);
        });
    }
}