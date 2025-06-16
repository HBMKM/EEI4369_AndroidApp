package com.s23010175.manuli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;

import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To find button by its ID
        Button btnOpenLogin = findViewById(R.id.btnOpenLogin);

        // Set a click listener ti open LoginActivity
        btnOpenLogin.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}