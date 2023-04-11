package com.example.carpoolclan;

import java.util.ArrayList;

public class Route {

    // point A to B original route
    int distance; // meters
    int duration; // seconds
    double start_location_lat;
    double start_location_lng;
    double end_location_lat;
    double end_location_lng;

    String overviewPolyline;

    // all intermediate steps, locations and timings to get to location B from A
    ArrayList<Step> steps = new ArrayList<Step>();

    public Route(double start_lat, double start_lng, double end_lat, double end_lng, int distance, int duration){
        this.distance = distance;
        this.duration = duration;
        this.start_location_lat = start_lat;
        this.start_location_lng = start_lng;
        this.end_location_lat = end_lat;
        this.end_location_lng = end_lng;
    }

}
