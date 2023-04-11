package com.example.carpoolclan;

public class Step {

    double start_location_lat;
    double start_location_lng;
    double end_location_lat;
    double end_location_lng;
    int distance;
    int duration;
    String polyline;
    String instructions;


    public Step(double start_lat, double start_lng, double end_lat, double end_lng, int distance, int duration, String instructions, String polyline){
        this.start_location_lat = start_lat;
        this.start_location_lng = start_lng;
        this.end_location_lat = end_lat;
        this.end_location_lng = end_lng;
        this.distance = distance;
        this.duration = duration;
        this.instructions = instructions;
        this.polyline = polyline;
    }
}
