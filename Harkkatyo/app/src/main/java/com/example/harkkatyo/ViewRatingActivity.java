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
    TextView movieTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rating);

        movie = (Movie) getIntent().getSerializableExtra("movie");
        RatingManager rm = new RatingManager(movie);

        // get the rating from rating manager
        rating = rm.getRating(ViewRatingActivity.this);

        // setting texts and images via id
        image = findViewById(R.id.movieImage_rating);
        ratingBar = findViewById(R.id.ratingBar_view);
        comment = findViewById(R.id.comment_rating);
        movieTitle = findViewById(R.id.movieName_rating);

        comment.setText(rating.getComment());
        ratingBar.setRating(rating.getStars());
        Picasso.get().load(rating.getMovie().getLandscapeImageUrl()).into(image);
        movieTitle.setText(rating.getTitle());
    }
}