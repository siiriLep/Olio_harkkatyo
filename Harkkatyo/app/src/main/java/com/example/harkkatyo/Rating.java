package com.example.harkkatyo;

public class Rating {

    private int stars;
    private String comment;
    private int id;
    private String title;
    private Movie movie;

    Rating(Movie movie ,int id, String title, int stars, String text){
        this.stars = stars;
        this.comment = text;
        this.id = id;
        this.title = title;
    }

    public int getStars() {
        return stars;
    }

    public String getComment() {
        return comment;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Movie getMovie() {
        return movie;
    }
}
