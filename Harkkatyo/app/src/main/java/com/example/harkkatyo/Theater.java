package com.example.harkkatyo;


import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/*
    ### Theater ###
    * Theater name and ID
    * all the movies the theater shows
    * Theater info (whatever it is)
    *

*/

public class Theater implements Serializable {
    private int id;
    private String name;

    ArrayList<Movie> movies = new ArrayList<>();

    Theater(@NonNull Element theaterInfo){
        this.id = Integer.parseInt(theaterInfo.getElementsByTagName("ID").item(0).getTextContent());
        this.name = theaterInfo.getElementsByTagName("Name").item(0).getTextContent();
    }


    // Gets all the movies shown in the theater
    @RequiresApi(api = Build.VERSION_CODES.O)
    // Call this function after the theater has been selected!
    void fetchMovies(String date, LocalTime filterTimePeriodStart, LocalTime filterTimePeriodEnd){  // Date format DDMMYYYY
        if(!movies.isEmpty()){
            movies.clear();
        }

        String url;

        // Null checks
        if(date != null) {
            url = "https://www.finnkino.fi/xml/Schedule/?area=" + id + "&dt=" + date;
        } else {
            url = "https://www.finnkino.fi/xml/Schedule/?area=" + id;
        }


        // TODO: check if this null checking is fine
        if(filterTimePeriodStart == null) {
            filterTimePeriodStart = LocalTime.MIDNIGHT;  //???
        }
        if(filterTimePeriodEnd == null) {
            filterTimePeriodEnd = LocalTime.MAX;    //???
        }

        try { // reading the xml
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(url);
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getDocumentElement().getElementsByTagName("Show");

            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;

                    // Get the movies starting time to filter out movies that aren't in the specified time range
                    String movieStartTime = element.getElementsByTagName("dttmShowStart").item(0).getTextContent();
                    LocalTime movieStartLocalTime = LocalTime.parse(movieStartTime.split("T")[1]);

                    if(movieStartLocalTime.isAfter(filterTimePeriodStart) && movieStartLocalTime.isBefore(filterTimePeriodEnd)){
                        Movie movie = new Movie(element);
                        movies.add(movie);
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getMovieNames() {
        ArrayList<String> movieNames = new ArrayList<>();
        for (Movie movie : movies) {
            movieNames.add(movie.getTitle());
        }
        return movieNames;
    }

    public Movie getMovie(int index) {
        return movies.get(index);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
