package com.example.carpoolclan;

import java.util.ArrayList;
import java.util.List;

public class TripDurationController {
    // the list of customerID's that is displayed to an individual user
    // every user will have a different customerIDs list
    private static List<String> customerIDs;

    public TripDurationController() {
        customerIDs = new ArrayList<>();

        addRatingCard();
    }

    public String getCustomerID(int position) { return customerIDs.get(position); }

    public void receivedRating(int position, float score) {
        System.out.println("Retrieved Rating for: " + customerIDs.get(position) + " Score: " + score);
        // CODE: handle code to receive rating score for individual
        customerIDs.remove(position);
    }
    public int getCustomerIDsListSize() {
        return customerIDs.size();
    }

    public void addRatingCard() {
        // this is all the DB stuff; including all passengers except for the user
        // iterate through DB and add to the list here
        String customerID_A = "bobby123.john@gmail.com";
        String customerID_B = "john.bob@gmail.com";
        String customerID_C = "sean.john@gmail.com";
        String customerID_D = "tom.john@gmail.com";

        customerIDs.add(customerID_A);
        customerIDs.add(customerID_B);
        customerIDs.add(customerID_C);
        customerIDs.add(customerID_D);
    }
}