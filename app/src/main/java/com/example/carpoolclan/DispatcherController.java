package com.example.carpoolclan;

import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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

    public Boolean validateUserInput(int num_passengers) {
        if (0 <= num_passengers & num_passengers <= 4) {
            return true;
        }
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
}
