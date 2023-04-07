package com.example.carpoolclan;

public class RequestInfoHelper {
    int id;
    String start, destination, num_passengers, filter;

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getNumPassengers() {
        return num_passengers;
    }

    public void setNumPassengers(String num_passengers) {
        this.num_passengers = num_passengers;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public RequestInfoHelper(int id, String start, String destination, String num_passengers, String filter) {
        setID(id);
        setStart(start);
        setDestination(destination);
        setNumPassengers(num_passengers);
        setFilter(filter);
    }

    public RequestInfoHelper() {
    }
}
