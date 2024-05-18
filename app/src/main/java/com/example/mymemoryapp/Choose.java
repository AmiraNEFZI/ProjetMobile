package com.example.mymemoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Choose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        // Find the "Memory_cards" button
        Button memoryCardsButton = findViewById(R.id.bm);
        memoryCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start MainActivity1
                Intent intent = new Intent(Choose.this, MainActivity.class);
                startActivity(intent);
            }
        });


        // Find the "Memory_numbers" button
        Button memoryNumbersButton = findViewById(R.id.bn);
        memoryNumbersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start MainActivity3
                Intent intent = new Intent(Choose.this, MainActivity2.class);
                startActivity(intent);
            }
        });

    }
}