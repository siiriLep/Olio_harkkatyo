package com.example.harkkatyo;


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


    Theater(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
