package com.s23010175.manuli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;

import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        Button btnLogin = findViewById(R.id.btnLogin);

        databaseHelper = new DatabaseHelper(this);

        btnLogin.setOnClickListener(view ->  {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            }else {
                boolean inserted = databaseHelper.insertUser(username, password);
                if (inserted) {
                    Toast.makeText(LoginActivity.this, "User saved successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Error saving user", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });
    }
}
