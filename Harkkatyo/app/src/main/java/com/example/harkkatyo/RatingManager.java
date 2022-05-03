package com.example.harkkatyo;



import android.content.Context;

import com.google.gson.Gson;

import java.io.File;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;





public class RatingManager {
    private int eventId;
    private String movieTitle;
    private Movie movie;

    Rating rating;


    RatingManager(Movie movie){
        this.movie = movie;
        this.eventId = movie.getEventID();
        this.movieTitle = movie.getTitle();

    }
    // Empty constructor for the AllRatingsActivity that doesn't have a movie
    RatingManager() {}

    public void addRating(int stars, String text, Context context) {

        rating = new Rating(movie, eventId, movieTitle, stars, text);
        // make a new GSON object
        Gson gson = new Gson();
        try {
            // Using eventID as file name, so the user cannot rate the same movie from different show times more than once
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(eventId + ".json", Context.MODE_PRIVATE));
            // fill GSON object up and parse it to string for easier storage
            String jsonString = gson.toJson(rating);
            ows.write(jsonString);
            ows.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Rating getRating(Context context) {

        // Reading the JSON file
        Gson gson = new Gson();
        try{
            String jsonString = new String(Files.readAllBytes(Paths.get(context.getFilesDir().toString() + "/" + eventId + ".json")));
            rating = gson.fromJson(jsonString, Rating.class);

        } catch (IOException e) {
            // If file doesn't exist return null to signify that no rating has been left.
            return null;
        }

        return rating;
    }

    public ArrayList<Rating> getAllRatings(Context context) {
        ArrayList<Rating> allRatings = new ArrayList<>();
        ArrayList<String> fileNames = new ArrayList<>();
        try {
            // Get all the files
            File[] files = new File(context.getFilesDir().toString()).listFiles();
            if (files != null) {

                // Save all filenames to an arraylist
                 for (File file : files) {
                     if(file.isFile()) {
                         fileNames.add(file.getName());
                     }
                 }

                 // Create a rating object from the parsed json data
                 for(String fileName : fileNames) {
                     Gson gson = new Gson();
                     String jsonString = new String(Files.readAllBytes(Paths.get(context.getFilesDir().toString() + "/" + fileName)));
                     rating = gson.fromJson(jsonString, Rating.class);
                     allRatings.add(rating);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allRatings;
    }

}
