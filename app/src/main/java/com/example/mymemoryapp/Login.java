package com.example.mymemoryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {
    FirebaseFirestore firestore;
    EditText edemail, edpassword;
    ConstraintLayout log;
    FirebaseAuth mAuth;
    ProgressBar pr;
    TextView tl;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        // Initialize other views
        edemail = findViewById(R.id.edl2);
        edpassword = findViewById(R.id.edl3);
        tl = findViewById(R.id.tvlog);
        //pr = findViewById(R.id.progressBar);

        tl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edemail.getText().toString().trim();
                String password = edpassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login.this, "Enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "Enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }

                pr.setVisibility(View.VISIBLE); // Show progress indicator

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                pr.setVisibility(View.GONE); // Hide progress indicator
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(Login.this, "Login successful!",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Choose.class);
                                    startActivity(intent);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                                    switch (errorCode) {
                                        case "ERROR_INVALID_EMAIL":
                                            Toast.makeText(Login.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                                            break;
                                        case "ERROR_WRONG_PASSWORD":
                                            Toast.makeText(Login.this, "Wrong password", Toast.LENGTH_SHORT).show();
                                            break;
                                        case "ERROR_USER_NOT_FOUND":
                                            Toast.makeText(Login.this, "User not found", Toast.LENGTH_SHORT).show();
                                            break;
                                        default:
                                            Toast.makeText(Login.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                                            break;
                                    }
                                }
                            }
                        });
            }
        });
    }


}