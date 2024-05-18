package com.example.mymemoryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import com.example.mymemoryapp.ScoreAdapter;

public class scoreactivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ScoreAdapter scoreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreactivity);


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        scoreAdapter = new ScoreAdapter();
        recyclerView.setAdapter(scoreAdapter);

        // Retrieve player scores from intent extras
        int player1Score = getIntent().getIntExtra("player1Score", 0);
        int player2Score = getIntent().getIntExtra("player2Score", 0);

        // Update RecyclerView with player scores
        ArrayList<String> scores = new ArrayList<>();
        scores.add("Player 1: " + player1Score);
        scores.add("Player 2: " + player2Score);
        scoreAdapter.setData(scores);
    }
}
    
