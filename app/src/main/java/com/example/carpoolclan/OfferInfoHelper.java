package com.example.carpoolclan;

public class OfferInfoHelper {
    String taxiID, customerID, time, start, destination, numPassengers;

    public String getTaxiID() {
        return taxiID.replaceAll("@", "/");
    }

    public void setTaxiID(String taxi_id) {
        this.taxiID = taxi_id.replaceAll("/","@");
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customer_id) {
        this.customerID = customer_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
        return numPassengers;
    }

    public void setNumPassengers(String num_passengers) {
        this.numPassengers = num_passengers;
    }


    public OfferInfoHelper(String taxi_id, String customer_id, String time, String start, String destination, String num_passengers) {
        setTaxiID(taxi_id);
        setCustomerID(customer_id);
        setTime(time);
        setStart(start);
        setDestination(destination);
        setNumPassengers(num_passengers);
    }
}
