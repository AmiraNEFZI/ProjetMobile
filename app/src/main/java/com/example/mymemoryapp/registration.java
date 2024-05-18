package com.example.mymemoryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;


public class registration extends AppCompatActivity {

        EditText editextemail,editextpassword,editextname;
        FirebaseAuth mAuth;
       DatabaseHelper dbHelper;
       ConstraintLayout regi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth = FirebaseAuth.getInstance();
        dbHelper = new DatabaseHelper(this);
        editextemail = findViewById(R.id.edr2);
        editextpassword = findViewById(R.id.edr3);
        editextname = findViewById(R.id.edr1);
        regi = findViewById(R.id.lbr4);






        regi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String name,email,password;
                name = String.valueOf(editextname.getText());
                password = String.valueOf(editextpassword.getText());
                email = String.valueOf( editextemail.getText());

                if (TextUtils.isEmpty(name)){
                    Toast.makeText(registration.this, "enter your name", Toast.LENGTH_SHORT ).show();
                    return;

                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(registration.this, "enter your password", Toast.LENGTH_SHORT ).show();
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(registration.this, "enter your email", Toast.LENGTH_SHORT ).show();
                     return;
                }

                /*
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.UserEntry.COLUMN_NAME_NAME, name);
                values.put(Contract.UserEntry.COLUMN_NAME_EMAIL, email);
                values.put(Contract.UserEntry.COLUMN_NAME_PASSWORD, password);
                long newRowId = db.insert(Contract.UserEntry.TABLE_NAME, null, values);

                if (newRowId != -1) {
                    Toast.makeText(registration.this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(registration.this, "Failed to create account.", Toast.LENGTH_SHORT).show();
                }*/


                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(registration.this, "Account created successfully!",
                                            Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getApplicationContext(), Choose.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(registration.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });




            }
        });
    }
}