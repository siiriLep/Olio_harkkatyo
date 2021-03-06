package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

public class AddRating extends AppCompatActivity {

    Movie movie;
    RatingBar ratingBar;
    EditText comment;
    RatingManager rm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rating);

        // Get movie element from intent
        movie = (Movie) getIntent().getSerializableExtra("movie");
        rm = new RatingManager(movie);
        ratingBar = findViewById(R.id.ratingBar);
        comment = findViewById(R.id.comment);
    }

    public void submitRating(View v){
        rm.addRating((int) ratingBar.getRating(), comment.getText().toString(), AddRating.this );

        // these two lines are just to get the user back to the last view after submitting a rating
        super.finish();
        super.onBackPressed();
    }


}