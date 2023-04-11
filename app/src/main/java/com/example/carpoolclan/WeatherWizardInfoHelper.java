package com.example.carpoolclan;

public class WeatherWizardInfoHelper {
    String taxiID, frontLeft, backLeft, frontRight, backRight, temp;

    public String getTaxiID() {
        return taxiID.replaceAll("@", "/");
    }

    public void setTaxiID(String taxiID) {
        this.taxiID = taxiID.replaceAll("/", "@");
    }

    public String getFrontLeft() {
        return frontLeft;
    }

    public void setFrontLeft(String frontLeft) {
        this.frontLeft = frontLeft;
    }

    public String getBackLeft() {
        return backLeft;
    }

    public void setBackLeft(String backLeft) {
        this.backLeft = backLeft;
    }

    public String getFrontRight() {
        return frontRight;
    }

    public void setFrontRight(String frontRight) {
        this.frontRight = frontRight;
    }

    public String getBackRight() {
        return backRight;
    }

    public void setBackRight(String backRight) {
        this.backRight = backRight;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public WeatherWizardInfoHelper(String taxiID, String frontLeft, String backLeft, String frontRight, String backRight, String temp) {
        setTaxiID(taxiID);
        setFrontLeft(frontLeft);
        setBackLeft(backLeft);
        setFrontRight(frontRight);
        setBackRight(backRight);
        setTemp(temp);
    }
}
