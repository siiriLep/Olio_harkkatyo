package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewRatingActivity extends AppCompatActivity {

    Movie movie;
    Rating rating;
    ImageView image;
    RatingBar ratingBar;
    TextView comment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rating);

        movie = (Movie) getIntent().getSerializableExtra("movie");
        RatingManager rm = new RatingManager(movie);
        rating = rm.getRating();

        image = findViewById(R.id.movieImage_rating);
        ratingBar = findViewById(R.id.ratingBar_view);
        comment = findViewById(R.id.comment_rating);

        comment.setText(rating.getComment());
        ratingBar.setRating(rating.getStars());
        Picasso.get().load(movie.getLandscapeImageUrl()).into(image);
    }
}