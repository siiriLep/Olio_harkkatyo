package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.nio.file.Files;
import java.nio.file.Paths;

public class MovieActivity extends AppCompatActivity {

    Movie movie;
    TextView movieTitle;
    TextView originalTitle;
    TextView genres;
    TextView language;
    TextView runtime;
    TextView presentationMethod;
    TextView theaterName;
    TextView MovieStart;
    TextView MovieEnd;
    Button button;

    ImageView image;
    ImageView ratingImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        movie = (Movie) getIntent().getSerializableExtra("movie");
        movieTitle = (TextView) findViewById(R.id.movieTitle);
        image = (ImageView) findViewById(R.id.imageView);
        originalTitle = (TextView) findViewById(R.id.OGtitle2);
        genres = (TextView) findViewById(R.id.genres);
        ratingImage = (ImageView) findViewById(R.id.ageRating);
        language = (TextView) findViewById(R.id.textView9);
        runtime = (TextView) findViewById(R.id.length);
        presentationMethod = (TextView) findViewById(R.id.textView7);
        theaterName = (TextView) findViewById(R.id.theaters);
        button = (Button) findViewById(R.id.button2);
        MovieStart = (TextView) findViewById(R.id.startTime);
        MovieEnd = (TextView) findViewById(R.id.endTime);


        theaterName.setText(movie.getTheater());
        presentationMethod.setText(" "+movie.getPresentationMethod()+ " ");
        runtime.setText(String.valueOf(movie.getRuntime()) + "min"); // not entirely sure if valueOf is needed me tired u_u
        movieTitle.setText(movie.getTitle());
        originalTitle.setText(movie.getOgTitle());
        genres.setText(movie.getGenre());
        language.setText(movie.getLanguage());
        System.out.println(movie.getLandscapeImageUrl());
        Picasso.get().load(movie.getLandscapeImageUrl()).into(image);
        Picasso.get().load(movie.getRatingImage()).into(ratingImage);
        MovieStart.setText(movie.getStartOfMovie()+" - ");
        MovieEnd.setText(movie.getEndOfMovie());


    }



    public void loadAddRating (View view) {
        Intent intent;
        Context context = MovieActivity.this;
        // If a review exists for this movie, show it. Else create a new rating
        if(Files.exists(Paths.get(String.valueOf(Paths.get(context.getFilesDir().toString() + "/" + movie.getEventID() + ".json"))))) {
            intent = new Intent(MovieActivity.this, ViewRatingActivity.class);
        } else {
            intent = new Intent(MovieActivity.this, AddRating.class);
        }

        intent.putExtra("movie", movie);

        startActivity(intent);

    }


}