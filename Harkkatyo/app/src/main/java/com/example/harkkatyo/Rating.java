package com.example.harkkatyo;


/*
    ### RATING ###
    * ratings from IMDB
    * ability to add own rating and comment

 */

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Rating {
    private Movie movie;
    private int id;
    private String movieTitle;

    Rating(Movie movie){
        this.movie = movie;
        this.id = movie.getId();
        movieTitle = movie.getTitle();
    }

    public void addRating(int stars, String text) throws JSONException, IOException {
        // Creating a json object
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ID", id);
        jsonObject.put("Title", movieTitle);
        jsonObject.put("Stars", stars);
        jsonObject.put("Comment", text);

        FileWriter fw = new FileWriter("file:///android_asset/reviews/"+id+".json");
        fw.write(String.valueOf(jsonObject));
        fw.close();
    }



}
