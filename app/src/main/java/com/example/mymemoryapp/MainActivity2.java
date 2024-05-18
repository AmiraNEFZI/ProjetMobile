package com.example.mymemoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    TextView tv_number, tv_score;
    EditText et_number;
    Button b_confirm;
    Random r;
    int currentLevel = 1;
    String generatedNumber;
    int score = 0; // Variable to keep track of the player's score
    String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv_score = findViewById(R.id.tv_score);
        tv_number = findViewById(R.id.tv_number);
        et_number = findViewById(R.id.et_number);
        b_confirm = findViewById(R.id.b_confirm);
        r = new Random();

        // Retrieve player name from intent extras
        playerName = getIntent().getStringExtra("playerName");

        tv_score.setText("Score: " + score);

        // Hide the input and the button and show the number
        et_number.setVisibility(View.GONE);
        b_confirm.setVisibility(View.GONE);
        tv_number.setVisibility(View.VISIBLE);

        // Display random number according to the level
        generatedNumber = generateNumberString(currentLevel);
        tv_number.setText(generatedNumber);

        // Display elements after 2 seconds and hide the number
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                et_number.setVisibility(View.VISIBLE);
                b_confirm.setVisibility(View.VISIBLE);
                tv_number.setVisibility(View.GONE);
                tv_number.requestFocus();
            }
        }, 2000);

        b_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the numbers are the same
                if (generatedNumber.equals(et_number.getText().toString())) {
                    // Increase the level and score
                    currentLevel++;
                    score++; // Increase score when passing a level
                    tv_score.setText("Score: " + score);

                    // Hide the input and the button and show the number
                    et_number.setVisibility(View.GONE);
                    b_confirm.setVisibility(View.GONE);
                    tv_number.setVisibility(View.VISIBLE);
                    // Remove text from input
                    et_number.setText("");

                    // Display random number according to the level
                    generatedNumber = generateNumberString(currentLevel);
                    tv_number.setText(generatedNumber);

                    // Display elements after 2 seconds and hide the number
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            et_number.setVisibility(View.VISIBLE);
                            b_confirm.setVisibility(View.VISIBLE);
                            tv_number.setVisibility(View.GONE);
                            tv_number.requestFocus();
                        }
                    }, 2000);

                } else {
                    String currentUserEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

                    // Game over
                    saveScoreToDatabase(playerName, score , currentUserEmail); // Save player's score to database
                    Toast.makeText(MainActivity2.this, "Game Over! The number was " + generatedNumber, Toast.LENGTH_SHORT).show();
                    b_confirm.setEnabled(false);
                }
            }
        });
    }

    private String generateNumberString(int digits) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < digits; i++) {
            int randomDigit = r.nextInt(10);
            output.append(randomDigit);
        }
        return output.toString();
    }

    private void saveScoreToDatabase(String playerName, int score, String email) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contract.UserEntry.COLUMN_NAME_NAME, playerName);
        values.put(Contract.UserEntry.COLUMN_NAME_EMAIL, email);
        values.put(Contract.UserEntry.COLUMN_NAME_SCORE, score);

        long newRowId = db.insert(Contract.UserEntry.TABLE_NAME, null, values);

        if (newRowId != -1) {
            Toast.makeText(MainActivity2.this, "Score saved to database!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity2.this, "Failed to save score to database.", Toast.LENGTH_SHORT).show();
        }
    }

}