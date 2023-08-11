package com.example.findjobproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginBtn = findViewById(R.id.loginBtn);
        Button signUpBtn = findViewById(R.id.signUpBtn);

        loginBtn.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, login.class);
            startActivity(i);
        });

        signUpBtn.setOnClickListener(view -> {
            Intent j = new Intent(MainActivity.this, createNewAccount.class);
            startActivity(j);
        });

    }

    @Override
    public void onBackPressed(){
    }

}