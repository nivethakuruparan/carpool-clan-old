package com.example.carpoolclan;

/*
 * This is the class that stores all data relevant to a potential offer
 */

public class PotentialOffersCard {
    private String offererID;
    private String tripTime;
    private String numPassengers;
    private String farePrice;
    private String polyLine;

    public PotentialOffersCard(String offererID, String tripTime, String numPassengers, String farePrice, String polyLine) {
        this.offererID = offererID;
        this.tripTime = tripTime;
        this.numPassengers = numPassengers;
        this.farePrice = farePrice;
        this.polyLine = polyLine;
    }

    public String getOffererID() {
        return offererID;
    }

    public String getTripTime() {
        return tripTime;
    }

    public String getNumPassengers() {
        return numPassengers;
    }

    public String getFarePrice() {
        return farePrice;
    }

    public String getPolyLine() {
        return polyLine;
    }
}
