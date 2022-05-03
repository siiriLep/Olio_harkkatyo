package com.example.harkkatyo;


import android.content.Context;

import androidx.annotation.NonNull;

import org.w3c.dom.Element;

import java.io.Serializable;
import java.util.ArrayList;

/*
    ### Movie ###
    * run time
    * restrictions
    * thumbnail image
    * etc. etc.
 */
public class Movie implements Serializable {
    private int id;
    private int eventID;
    private String title;
    private int runtime;

    private String ImageUrl;
    private String ratingImage;
    private String ogTitle;
    private String genre;
    private String language;
    private String presentationMethod;
    private String theater;


    // Constructor is given the whole element from the xml, and it parses itself
    // This is done this way in order to keep the expanding of this software as easy as possible
    Movie(@NonNull Element movieInfo) {
        this.title = movieInfo.getElementsByTagName("Title").item(0).getTextContent();
        this.id = Integer.parseInt(movieInfo.getElementsByTagName("ID").item(0).getTextContent());
        this.runtime = Integer.parseInt(movieInfo.getElementsByTagName("LengthInMinutes").item(0).getTextContent());
        this.ImageUrl = movieInfo.getElementsByTagName("Images").item(0).getTextContent();
        this.ratingImage = movieInfo.getElementsByTagName("RatingImageUrl").item(0).getTextContent();
        this.ogTitle = movieInfo.getElementsByTagName("OriginalTitle").item(0).getTextContent();
        this.genre = movieInfo.getElementsByTagName("Genres").item(0).getTextContent();
        this.language = movieInfo.getElementsByTagName("SpokenLanguage").item(0).getTextContent();
        this.presentationMethod = movieInfo.getElementsByTagName("PresentationMethod").item(0).getTextContent();
        this.theater = movieInfo.getElementsByTagName("Theatre").item(0).getTextContent();
        this.eventID = Integer.parseInt(movieInfo.getElementsByTagName("EventID").item(0).getTextContent());

    }
    



    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getLandscapeImageUrl() {
        String[] urls = ImageUrl.split("\n");

        for(String url : urls) {
            if (url.matches(".*landscape_large.*")) {
                return url.trim();
            }
        }
        for(String url : urls) {
            if (url.matches(".*landscape_large.*")) {
                return url.trim();
            }
        }
        for(String url : urls) {
            if (url.matches(".*landscape_large.*")) {
                return url.trim();
            }
        }

        return null;
    }

    public String getPortraitImageUrl() {
        String[] urls = ImageUrl.split("\n");
        for (String url : urls) {
            if (url.matches(".*portrait_large.*")) {
                return url.trim();
            }
        }
        for (String url : urls) {
            if (url.matches(".*portrait_medium.*")) {
                return url.trim();
            }
        }
        for (String url : urls) {
            if (url.matches(".*portrait_small.*")) {
                return url.trim();
            }
        }
        return null;
    }

    public String getOgTitle() {
        return ogTitle;
    }

    public String getGenre() {
        return genre;
    }

    public int getEventID() {
        return eventID;
    }

    public String getRatingImage(){
        return ratingImage;
    }

    public String getLanguage(){
        return language;
    }

    public String getPresentationMethod() {
        return presentationMethod;
    }

    public String getTheater() {
        return theater;
    }
}
