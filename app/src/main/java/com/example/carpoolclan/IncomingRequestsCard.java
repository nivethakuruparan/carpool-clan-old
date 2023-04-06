package com.example.carpoolclan;

public class IncomingRequestsCard {
    private String requesterID;
    private String tripTime;
    private String numPassengers;
    private String farePrice;
    private String polyLine;

    public IncomingRequestsCard(String requesterID, String tripTime, String numPassengers, String farePrice, String polyLine) {
        this.requesterID = requesterID;
        this.tripTime = tripTime;
        this.numPassengers = numPassengers;
        this.farePrice = farePrice;
        this.polyLine = polyLine;
    }

    public String getRequesterID() {
        return requesterID;
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
