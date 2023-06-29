package com.example.sejo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Job extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        Button button = findViewById(R.id.button6);
        Button button1 = findViewById(R.id.button7);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(Job.this, HouseMaid.class);
            startActivity(intent);
        });

        button1.setOnClickListener(view -> {
            Intent intent = new Intent(Job.this, OtherJobs.class);
            startActivity(intent);
        });
    }
}