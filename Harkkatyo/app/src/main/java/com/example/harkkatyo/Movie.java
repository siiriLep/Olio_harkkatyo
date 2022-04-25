package com.example.harkkatyo;


import org.w3c.dom.Element;

/*
    ### Movie ###
    * run time
    * restrictions
    * thumbnail image
    * etc. etc.
 */
public class Movie {
    private int id;
    private String title;
    private int ageRestriction;


    // Constructor is given the whole element from the xml, and it parses itself
    // This is done this way in order to keep the expanding of this software as easy as possible
    Movie(Element movieInfo) {
        this.title = movieInfo.getElementsByTagName("Title").item(0).getTextContent();
        this.id = Integer.parseInt(movieInfo.getElementsByTagName("ID").item(0).getTextContent());

    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }
}
