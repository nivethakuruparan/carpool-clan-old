package com.example.carpoolclan;

import java.util.ArrayList;
import java.util.List;

public class DDispatcherController {

    private static List<PotentialOffersCard> potentialOffersList; // the list of potential offers that is displayed
    private static List<IncomingRequestsCard> incomingRequestsList; // the list of incoming requests that is displayed

    public DDispatcherController() {
        potentialOffersList = new ArrayList<>();
        incomingRequestsList = new ArrayList<>();

        addingPotentialOffers();
    }

    public PotentialOffersCard getPotentialOffer(int position) {
        return potentialOffersList.get(position);
    }

    public void addPotentialOffer(PotentialOffersCard potentialOffer) {
        potentialOffersList.add(potentialOffer);
    }

    public void removePotentialOffer(int position) {
        potentialOffersList.remove(position);
    }

    public int getPotentialOffersListSize(){
        return potentialOffersList.size();
    }

    public void receivedPotentialOfferConfirmation(int position) {
        PotentialOffersCard potentialOfferConfirm = potentialOffersList.get(position);
        System.out.println("Confirmed Potential Offer for Offerer: " + potentialOfferConfirm.getOffererID());
        // code to get response from offerer
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

    public void addingIncomingRequest() {
        PotentialOffersCard cardA = new PotentialOffersCard("bob.john@gmail.com", "12 minutes", "3", "$2.00", "{qagGt_`gNmDc@`@sFbA_Oh@iIn@{JJmAjAeHDOGKi@{@cAuAUKWOgAe@oH_D_E_Ba@[iA{AcA_Bo@aAoAeB{C}DiAoAm@q@w@kA[}@");
        PotentialOffersCard cardB = new PotentialOffersCard("tom.smith@gmail.com", "12 minutes", "3", "$2.00", "{qagGt_`gNmDc@`@sFbA_Oh@iIn@{JJmAjAeHDOGKi@{@cAuAUKWOgAe@oH_D_E_Ba@[iA{AcA_Bo@aAoAeB{C}DiAoAm@q@w@kA[}@");
        PotentialOffersCard cardC = new PotentialOffersCard("prat.matt@gmail.com", "12 minutes", "3", "$2.00", "{qagGt_`gNmDc@`@sFbA_Oh@iIn@{JJmAjAeHDOGKi@{@cAuAUKWOgAe@oH_D_E_Ba@[iA{AcA_Bo@aAoAeB{C}DiAoAm@q@w@kA[}@");
        PotentialOffersCard cardD = new PotentialOffersCard("greg.beg@gmail.com", "12 minutes", "3", "$2.00", "{qagGt_`gNmDc@`@sFbA_Oh@iIn@{JJmAjAeHDOGKi@{@cAuAUKWOgAe@oH_D_E_Ba@[iA{AcA_Bo@aAoAeB{C}DiAoAm@q@w@kA[}@");

        addPotentialOffer(cardA);
        addPotentialOffer(cardB);
        addPotentialOffer(cardC);
        addPotentialOffer(cardD);
    }


    public List<IncomingRequestsCard> getIncomingRequestsList() {
        return incomingRequestsList;
    }

    public void addIncomingRequest(IncomingRequestsCard incomingRequests) {
        incomingRequestsList.add(incomingRequests);
    }

    public void removeIncomingRequest(int position) {
        incomingRequestsList.remove(position);
    }

    public int getIncomingRequestsSize(){
        return incomingRequestsList.size();
    }
}
