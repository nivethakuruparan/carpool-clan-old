package com.example.carpoolclan;

public class SessionController {

    private static Boolean tripStatus; // false for no trip; true for current trip
    private static String customerType; // "offerer, requester"
    private static String polyLine; // current polyLine being used on the trip

    public SessionController() {
        tripStatus = true;
        customerType = "offerer";
        polyLine = "{qagGt_`gNmDc@`@sFbA_Oh@iIn@{JJmAjAeHDOGKi@{@cAuAUKWOgAe@oH_D_E_Ba@[iA{AcA_Bo@aAoAeB{C}DiAoAm@q@w@kA[}@";
    }

    public static Boolean getTripStatus() {
        return tripStatus;
    }

    public static void setTripStatus(Boolean tripStatus) {
        SessionController.tripStatus = tripStatus;
    }

    public static String getCustomerType() {
        return customerType;
    }

    public static void setCustomerType(String customerType) {
        SessionController.customerType = customerType;
    }
    public static String getPolyLine() {
        return polyLine;
    }

    public static void setPolyLine(String polyLine) {
        SessionController.polyLine = polyLine;
    }
}
