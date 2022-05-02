package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class MovieActivity extends AppCompatActivity {

    Movie movie;
    TextView movieTitle;
    TextView originalTitle;
    TextView genres;
    TextView language;
    TextView runtime;
    TextView presentationMethod;
    TextView theater;

    ImageView image;
    ImageView ratingImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        movie = (Movie) getIntent().getSerializableExtra("movie");
        movieTitle = (TextView) findViewById(R.id.movieTitle);
        image = (ImageView) findViewById(R.id.imageView);
        originalTitle = (TextView) findViewById(R.id.OGtitle);
        genres = (TextView) findViewById(R.id.genres);
        ratingImage = (ImageView) findViewById(R.id.ageRating);
        language = (TextView) findViewById(R.id.textView9);
        runtime = (TextView) findViewById(R.id.length);
        presentationMethod = (TextView) findViewById(R.id.textView7);
        theater = (TextView) findViewById(R.id.theaters);


        theater.setText(movie.getTheater());
        presentationMethod.setText(" "+movie.getPresentationMethod()+ " ");
        runtime.setText(String.valueOf(movie.getRuntime()) + "min"); // not entirely sure if valueOf is needed me tired u_u
        movieTitle.setText(movie.getTitle());
        originalTitle.setText(movie.getOgTitle());
        genres.setText(movie.getGenre());
        language.setText(movie.getLanguage());
        System.out.println(movie.getLandscapeImageUrl());
        Picasso.get().load(movie.getLandscapeImageUrl()).into(image);
        Picasso.get().load(movie.getRatingImage()).into(ratingImage);


    }





}