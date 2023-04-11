package com.example.carpoolclan;

import static com.example.carpoolclan.DirectionsAPI.approximateAlongRoute;
import static com.example.carpoolclan.DirectionsAPI.calculate;
import static com.example.carpoolclan.DirectionsAPI.parse;

import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DispatcherController {

    private static List<PotentialOffersCard> potentialOffersList; // the list of potential offers that is displayed
    private static List<IncomingRequestsCard> incomingRequestsList; // the list of incoming requests that is displayed

    public DispatcherController() {
        potentialOffersList = new ArrayList<>();
        incomingRequestsList = new ArrayList<>();

        // assuming that when users are redirected to potential offers page, there is at least one potential offer
        // i.e. there will be no empty potential offers page
        addingPotentialOffers();
        addingIncomingRequests();
    }

    public Boolean checkEmptyFields(TextView field) {
        String text = field.getText().toString();

        if (text.isEmpty()) {
            field.setError("Field cannot be empty!");
            return false;
        }
        field.setError(null);
        return true;
    }

    public Boolean validateUserInput(EditText numPassengers) {
        int num_passengers = Integer.parseInt(numPassengers.getText().toString());
        if (2 <= num_passengers & num_passengers <= 4) {
            numPassengers.setError(null);
            return true;
        }
        numPassengers.setError("Please enter a valid number of passengers");
        return false;
    }

    public PotentialOffersCard getPotentialOffer(int position) {
        return potentialOffersList.get(position);
    }

    public void addPotentialOffer(PotentialOffersCard potentialOffer) {
        potentialOffersList.add(potentialOffer);
    }

    public int getPotentialOffersListSize(){
        return potentialOffersList.size();
    }

    public void receivedPotentialOfferConfirmation(int position) {
        PotentialOffersCard potentialOfferConfirm = potentialOffersList.get(position);
        System.out.println("Confirmed Potential Offer for Offerer: " + potentialOfferConfirm.getOffererID());
    }

    public void addingPotentialOffers(){
        PotentialOffersCard cardA = new PotentialOffersCard("bob.john@gmail.com", "12 minutes", "3", "$2.00", "{qagGt_`gNmDc@`@sFbA_Oh@iIn@{JJmAjAeHDOGKi@{@cAuAUKWOgAe@oH_D_E_Ba@[iA{AcA_Bo@aAoAeB{C}DiAoAm@q@w@kA[}@");
        PotentialOffersCard cardB = new PotentialOffersCard("tom.smith@gmail.com", "12 minutes", "3", "$2.00", "{qagGt_`gNmDc@`@sFbA_Oh@iIn@{JJmAjAeHDOGKi@{@cAuAUKWOgAe@oH_D_E_Ba@[iA{AcA_Bo@aAoAeB{C}DiAoAm@q@w@kA[}@");
        PotentialOffersCard cardC = new PotentialOffersCard("prat.matt@gmail.com", "12 minutes", "3", "$2.00", "{qagGt_`gNmDc@`@sFbA_Oh@iIn@{JJmAjAeHDOGKi@{@cAuAUKWOgAe@oH_D_E_Ba@[iA{AcA_Bo@aAoAeB{C}DiAoAm@q@w@kA[}@");
        PotentialOffersCard cardD = new PotentialOffersCard("greg.beg@gmail.com", "12 minutes", "3", "$2.00", "{qagGt_`gNmDc@`@sFbA_Oh@iIn@{JJmAjAeHDOGKi@{@cAuAUKWOgAe@oH_D_E_Ba@[iA{AcA_Bo@aAoAeB{C}DiAoAm@q@w@kA[}@");

        addPotentialOffer(cardA);
        addPotentialOffer(cardB);
        addPotentialOffer(cardC);
        addPotentialOffer(cardD);
    }

    public IncomingRequestsCard getIncomingRequest(int position) {
        return incomingRequestsList.get(position);
    }

    public void addIncomingRequest(IncomingRequestsCard incomingRequest) {
        incomingRequestsList.add(incomingRequest);
    }

    public int getIncomingRequestsListSize(){
        return incomingRequestsList.size();
    }

    public void receivedIncomingRequestDenial(int position) {
        IncomingRequestsCard incomingRequestDeny = incomingRequestsList.get(position);
        System.out.println("Denied Incoming Request for Requester: " + incomingRequestDeny.getRequesterID());

        // make sure to delete from the list, once they notify the requester
        incomingRequestsList.remove(position);
    }

    public void receivedIncomingRequestConfirmation(int position) {
        IncomingRequestsCard incomingRequestConfirm = incomingRequestsList.get(position);
        System.out.println("Confirmed Incoming Request for Requester: " + incomingRequestConfirm.getRequesterID());
    }

    public void addingIncomingRequests() {
        IncomingRequestsCard cardA = new IncomingRequestsCard("bob.john@gmail.com", "12 minutes", "3", "$2.00", "{qagGt_`gNmDc@`@sFbA_Oh@iIn@{JJmAjAeHDOGKi@{@cAuAUKWOgAe@oH_D_E_Ba@[iA{AcA_Bo@aAoAeB{C}DiAoAm@q@w@kA[}@");
        IncomingRequestsCard cardB = new IncomingRequestsCard("tom.smith@gmail.com", "12 minutes", "3", "$2.00", "{qagGt_`gNmDc@`@sFbA_Oh@iIn@{JJmAjAeHDOGKi@{@cAuAUKWOgAe@oH_D_E_Ba@[iA{AcA_Bo@aAoAeB{C}DiAoAm@q@w@kA[}@");
        IncomingRequestsCard cardC = new IncomingRequestsCard("prat.matt@gmail.com", "12 minutes", "3", "$2.00", "{qagGt_`gNmDc@`@sFbA_Oh@iIn@{JJmAjAeHDOGKi@{@cAuAUKWOgAe@oH_D_E_Ba@[iA{AcA_Bo@aAoAeB{C}DiAoAm@q@w@kA[}@");
        IncomingRequestsCard cardD = new IncomingRequestsCard("greg.beg@gmail.com", "12 minutes", "3", "$2.00", "{qagGt_`gNmDc@`@sFbA_Oh@iIn@{JJmAjAeHDOGKi@{@cAuAUKWOgAe@oH_D_E_Ba@[iA{AcA_Bo@aAoAeB{C}DiAoAm@q@w@kA[}@");

        addIncomingRequest(cardA);
        addIncomingRequest(cardB);
        addIncomingRequest(cardC);
        addIncomingRequest(cardD);
    }
    // take in 1 request match to x offers return all offers
    public void generateMatches(Map<String, String> request, ArrayList<Map<String, String>> offers) throws JSONException, IOException, ParseException {

        ArrayList<Map<String,String>> applicableOffers = new ArrayList<>();
        ArrayList<Map<String,String>> extraData = new ArrayList<>();
        // THIS MAP HAS THE FOLLOWING: polyline1, polyline2, time, distance

        // for each offer see if request can join
        for (Map<String, String> offer: offers) {
            // if they can fit
            if(Integer.parseInt(request.get("numPassengers")) <= Integer.parseInt(offer.get("numPassengers"))){

                String requestlat =  request.get("start").split(",")[0].strip();
                String requestlng =  request.get("start").split(",")[1].strip();
                String startOfferLat =  offer.get("start").split(",")[0].strip();
                String startOfferLng =  offer.get("start").split(",")[1].strip();
                String endOfferLat =  offer.get("start").split(",")[0].strip();
                String endOfferLng =  offer.get("start").split(",")[1].strip();


                // calculate route
                String response = calculate(new String[]{startOfferLat, startOfferLng},new String[]{endOfferLat, endOfferLng});
                JSONObject jsonObject = new JSONObject(response);
                Route originalRoute = parse(jsonObject);

                // get time passed
                String time = offer.get("timestamp"); // 0year 1month 2date 3hour 4min 5sec
                String[] parts = time.split(".");
                String time1 = parts[3] +"." + parts[4] + parts[5];

                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                Date date1 = format.parse(time1);
                Date date2 = new Date();
                long difference = (date2.getTime() - date1.getTime())/1000; // in seconds

                // get current location
                double[] whereNow = approximateAlongRoute(originalRoute, difference);

                // calculate route from current location to request to requester
                String response2 = calculate(new String[]{String.valueOf(whereNow[0]), String.valueOf(whereNow[1])},new String[]{requestlat, requestlng});
                JSONObject jsonObject2 = new JSONObject(response2);
                Route routetoRequest = parse(jsonObject2);

                // route to final destination from requester
                String response3 = calculate(new String[]{requestlat, requestlng},new String[]{endOfferLat, endOfferLng});
                JSONObject jsonObject3 = new JSONObject(response3);
                Route routetoFinalDest = parse(jsonObject3);

                // save output
                applicableOffers.add(offer);

                Map<String, String> m = new HashMap<String, String>();
                m.put("polyline1", routetoRequest.overviewPolyline);
                m.put("polyline2", routetoFinalDest.overviewPolyline);
                m.put("time", String.valueOf(routetoRequest.duration + routetoFinalDest.duration));
                m.put("distance", String.valueOf(routetoRequest.distance + routetoFinalDest.distance));
                m.put("fare", String.valueOf((routetoRequest.distance + routetoFinalDest.distance)*0.01));


            }

        }




    }
}
