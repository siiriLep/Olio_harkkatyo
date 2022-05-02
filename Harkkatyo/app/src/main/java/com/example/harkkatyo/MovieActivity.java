package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieActivity extends AppCompatActivity {

    Movie movie;
    TextView movieTitle;
    TextView originalTitle;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        movie = (Movie) getIntent().getSerializableExtra("movie");
        movieTitle = (TextView) findViewById(R.id.movieTitle);
        image = (ImageView) findViewById(R.id.imageView);



        movieTitle.setText(movie.getTitle());
        System.out.println(movie.getLandscapeImageUrl());
        Picasso.get().load(movie.getLandscapeImageUrl()).into(image);


    }





}