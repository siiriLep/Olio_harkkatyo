package com.example.harkkatyo;



import androidx.annotation.NonNull;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import java.io.Serializable;
import java.time.LocalTime;

import java.util.ArrayList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class Theater implements Serializable {
    private int id;
    private String name;

    ArrayList<Movie> movies = new ArrayList<>();

    Theater(@NonNull Element theaterInfo){
        this.id = Integer.parseInt(theaterInfo.getElementsByTagName("ID").item(0).getTextContent());
        this.name = theaterInfo.getElementsByTagName("Name").item(0).getTextContent();
    }


    // Gets all the movies shown in the theater
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

        // if filter times are left null, put them to the start and end of day AKA show the whole day
        if(filterTimePeriodStart == null) {
            filterTimePeriodStart = LocalTime.MIDNIGHT;
        }
        if(filterTimePeriodEnd == null) {
            filterTimePeriodEnd = LocalTime.MAX;
        }

        try { // reading the xml
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(url);
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getDocumentElement().getElementsByTagName("Show");

            // loop through the nodes
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;

                    // Get the movies starting time to filter out movies that aren't in the specified time range
                    String movieStartTime = element.getElementsByTagName("dttmShowStart").item(0).getTextContent();
                    LocalTime movieStartLocalTime = LocalTime.parse(movieStartTime.split("T")[1]);

                    // check wether the starting time of the movie is between the filters
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
