package com.example.harkkatyo;


/*
    ### Movie ###
    * run time
    * restrictions
    * thumbnail image
    * etc. etc.
 */
public class Movie {
    private int id;
    private String name;
    private int ageRestriction;

    Movie(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }
}
