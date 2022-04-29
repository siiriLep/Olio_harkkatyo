package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TheaterActivity extends AppCompatActivity {

    Theater theater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theater);
        theater = (Theater) getIntent().getSerializableExtra("object");
        System.out.println(theater.getName());
    }
}