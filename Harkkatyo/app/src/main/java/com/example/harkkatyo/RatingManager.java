package com.example.harkkatyo;



import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


/*
    ### RATING ###
    * ratings from IMDB
    * ability to add own rating and comment

 */

public class RatingManager {
    private int eventId;
    private String movieTitle;
    private Movie movie;

    Rating rating;

    RatingManager(Movie movie){
        this.movie = movie;
        this.eventId = movie.getEventID();
        movieTitle = movie.getTitle();

    }

    RatingManager() {}

    public void addRating(int stars, String text) {

        rating = new Rating(movie, eventId, movieTitle, stars, text);
        Gson gson = new Gson();
        String filePath = "file:///android_asset/reviews/" + eventId + ".json";
        try {
            gson.toJson(rating, new FileWriter(filePath));
        } catch (IOException e){
            e.printStackTrace();
        }


    }

    public Rating getRating() {
        Gson gson = new Gson();
        try{
            String jsonString = new String(Files.readAllBytes(Paths.get("file:///android_asset/reviews/"+eventId+".json")));  // TODO KORJAA TÄMÄ MUUALLE KUIN ASSETSIIN ASSETS READ ONLY
            rating = gson.fromJson(jsonString, Rating.class);

        } catch (IOException e) {
            // If file doesn't exist return null to signify that no rating has been left.
            return null;
        }

        return rating;
    }

    public ArrayList<Rating> getAllRatings() {
        ArrayList<Rating> allRatings = new ArrayList<>();
        ArrayList<String> fileNames = new ArrayList<>();
        try {
            File[] files = new File("file:///android_assets/reviews/").listFiles();

            if (files != null) {

                 for (File file : files) {
                     if(file.isFile()) {
                         fileNames.add(file.getName());
                     }
                 }

                 for(String fileName : fileNames) {
                     Gson gson = new Gson();
                     String jsonString = new String(Files.readAllBytes(Paths.get("file:///android_asset/reviews/"+fileName)));
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
