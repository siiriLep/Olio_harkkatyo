package com.example.harkkatyo;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

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
public class Theater {
    private int id;
    private String name;
    ArrayList<Movie> movies;


    Theater(int id, String name){
        this.id = id;
        this.name = name;
    }

    // Gets all the movies shown in the theater
    void fetchMovies(String date){
        movies = new ArrayList<>();
        String url = "https://www.finnkino.fi/xml/Schedule/?area=" + id + "&dt=" + date;

        try { // reading the xml
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(url);
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getDocumentElement().getElementsByTagName("Show");

            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;

                    Movie movie = new Movie(Integer.parseInt(element.getElementsByTagName("ID").item(0).getTextContent()),
                            element.getElementsByTagName("Title").item(0).getTextContent());
                            // MORE INFORMATION TO BE ADDED ONCE WE DECIDE SOMETHING
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getMovieNames() {
        ArrayList<String> movieNames = new ArrayList<>();
        for (Movie movie : movies) {
            movieNames.add(movie.getName());
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
