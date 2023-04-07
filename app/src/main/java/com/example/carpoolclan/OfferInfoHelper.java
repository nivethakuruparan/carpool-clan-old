package com.example.carpoolclan;

public class OfferInfoHelper {
    String id, destination, num_passengers;

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
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

    public OfferInfoHelper(String id, String destination, String num_passengers) {
        setID(id);
        setDestination(destination);
        setNumPassengers(num_passengers);
    }

    public OfferInfoHelper() {
    }
}
