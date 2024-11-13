package com.example.carpoolclan;

public class RequestInfoHelper {
    String requestID, customerID, time, start, destination, numPassengers, filter;

    public String getRequestID() {
        return requestID.replaceAll("@", "/");
    }

    public void setRequestID(String request_id) {
        this.requestID = request_id.replaceAll("/","@");
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

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public RequestInfoHelper(String request_id, String customer_id, String time, String start, String destination, String num_passengers, String filter) {
        setRequestID(request_id);
        setCustomerID(customer_id);
        setTime(time);
        setStart(start);
        setDestination(destination);
        setNumPassengers(num_passengers);
        setFilter(filter);
    }

    public RequestInfoHelper() {
    }
}
