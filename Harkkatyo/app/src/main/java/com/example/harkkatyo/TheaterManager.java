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
   ### Manages all the theaters ###
   * Gets all theaters
   * Created with singleton model

   * Get all movies
   * Search movies

*/
public class TheaterManager  {

    //General variables for this class
    private static TheaterManager tm;
    ArrayList<Theater> theaters;


    // Singleton model
    public static TheaterManager getInstance(){
        if(tm == null){
            tm = new TheaterManager();
        }
        return tm;
    }

    // Fetches all of the finnkino theaters from the xml file
    public void fetchTheaters(){
        theaters = new ArrayList<>();
        try {
            // read the XML
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String theatersXML = "https://www.finnkino.fi/xml/TheatreAreas/";
            Document doc = builder.parse(theatersXML);
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getDocumentElement().getElementsByTagName("TheatreArea");


            // foreach theater in XML create a theater object with the data given
            for(int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;

                    // Checking whether the name of the theater has ":" in it
                    if(element.getElementsByTagName("Name").item(0).getTextContent().matches(".*:.*")){
                        // creating the actual object
                        Theater theater = new Theater(element);
                        // adding object to list of theaters
                        theaters.add(theater);
                    }



                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }


    // returns a list of theater names
    public ArrayList<String> getTheaterNames() {
        ArrayList<String> names = new ArrayList<>();
        for(Theater theater : theaters) {
            names.add(theater.getName());
        }
        return names;
    }

    // returns a single theater object
    public Theater getTheater(int index){
        return theaters.get(index);
    }


}


