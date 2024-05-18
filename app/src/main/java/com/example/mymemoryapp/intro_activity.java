package com.example.mymemoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class intro_activity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_activity);
        // Find the ConstraintLayout containing the "Login" TextView
        ConstraintLayout loginLayout = findViewById(R.id.lb1);
        loginLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Login activity
                Intent intent = new Intent(intro_activity.this, Login.class);
                startActivity(intent);
            }
        });


        // Find the ConstraintLayout containing the "Sign Up" TextView
        ConstraintLayout signUpLayout = findViewById(R.id.lb2);
        signUpLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Sign Up activity
                Intent intent = new Intent(intro_activity.this, registration.class);
                startActivity(intent);
            }
        });


    }
}
