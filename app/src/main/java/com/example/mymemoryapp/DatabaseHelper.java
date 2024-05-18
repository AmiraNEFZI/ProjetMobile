package com.example.mymemoryapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyDatabase.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + Contract.UserEntry.TABLE_NAME + " (" +
                        Contract.UserEntry._ID + " INTEGER PRIMARY KEY," +
                        Contract.UserEntry.COLUMN_NAME_NAME + " TEXT," +
                        Contract.UserEntry.COLUMN_NAME_EMAIL + " TEXT," +
                        Contract.UserEntry.COLUMN_NAME_PASSWORD + " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Add any upgrade logic here
    }
}

