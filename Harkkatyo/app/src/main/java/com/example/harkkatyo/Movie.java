package com.example.harkkatyo;


import androidx.annotation.NonNull;

import org.w3c.dom.Element;

import java.io.Serializable;

/*
    ### Movie ###
    * run time
    * restrictions
    * thumbnail image
    * etc. etc.
 */
public class Movie implements Serializable {
    private int id;
    private String title;
    private int runtime;



    // Constructor is given the whole element from the xml, and it parses itself
    // This is done this way in order to keep the expanding of this software as easy as possible
    Movie(@NonNull Element movieInfo) {
        this.title = movieInfo.getElementsByTagName("Title").item(0).getTextContent();
        this.id = Integer.parseInt(movieInfo.getElementsByTagName("ID").item(0).getTextContent());
        this.runtime = Integer.parseInt(movieInfo.getElementsByTagName("LengthInMinutes").item(0).getTextContent());
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
}
