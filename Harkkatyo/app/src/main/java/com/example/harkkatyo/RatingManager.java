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
    Rating rating;

    RatingManager(Movie movie){
        this.eventId = movie.getEventID();
        movieTitle = movie.getTitle();
    }

    public void addRating(int stars, String text) throws IOException {

        rating = new Rating(eventId, movieTitle, stars, text);
        Gson gson = new Gson();
        String json = gson.toJson(rating);

        FileWriter fw = new FileWriter("file:///android_asset/reviews/"+eventId+".json");
        fw.write(String.valueOf(json));
        fw.close();



    }

    public Rating getRating() {
        Gson gson = new Gson();
        try{
            String jsonString = new String(Files.readAllBytes(Paths.get("file:///android_asset/reviews/"+eventId+".json")));
            rating = gson.fromJson(jsonString, Rating.class);

        } catch (IOException e) {
            // If file doesn't exist return null to signify that no rating has been left.
            return null;
        }

        return rating;
    }

    public ArrayList<Rating> getAllRatings() throws IOException {
        ArrayList<Rating> allRatings = new ArrayList<>();
        ArrayList<String> fileNames = new ArrayList<>();
        File[] files = new File("file:///android_assets/reviews/").listFiles();

        assert files != null: "No ratings available";
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

        return allRatings;
    }

}
