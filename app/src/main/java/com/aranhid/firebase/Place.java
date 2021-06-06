package com.aranhid.firebase;

public class Place {
    public String name;
    public Double lat, lon;

    public Place() {

    }

    public Place(String name, Double lat, Double lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public String toString() {
        return String.format("name: %s, latitude: %f, longitude: %f", name, lat, lon);
    }
}
