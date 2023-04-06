package com.example.carpoolclan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/*
 * NOTE TO US: LOL NOT REALLY SURE WHAT THIS DOES, BUT WHEN U CREATE A RECYCLERVIEW, U NEED AN ADAPTER TO MANAGE ALL THE DIFFERENT DATA
 * THERES A BUNCH OF YOUTUBE VIDEOS IF YOU SEARCH UP RECYCLERVIEW, AND I COPIED FROM THIS GITHUB AS WELL
 * https://github.com/Brijesh-kumar-sharma/RecyclerViewInAndroidStudio/tree/master/app/src/main/java/com/example/recyclerview
 */
public class PotentialOffersAdapter extends RecyclerView.Adapter<PotentialOffersAdapter.ViewHolder> {

    private List<PotentialOffersCard> offersCardList; // a list of all offers (offers is a class)
    OffersButtonClickListener buttonClickListener; // need to listen for a button click

    public PotentialOffersAdapter(List<PotentialOffersCard> offersCardList, OffersButtonClickListener buttonClickListener) {
        this.offersCardList = offersCardList;
        this.buttonClickListener = buttonClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // display the individual card; getting card design from display_potential_offers.xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_potential_offers,parent,false);
        return new ViewHolder(view, buttonClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // sets the text to the given information from the card
        String offererID = offersCardList.get(position).getOffererID();
        String tripTime = offersCardList.get(position).getTripTime();
        String numPassengers = offersCardList.get(position).getNumPassengers();
        String farePrice = offersCardList.get(position).getFarePrice();

        holder.setData(offererID, tripTime, numPassengers, farePrice); // this function is found in the ViewHolder class below
    }

    @Override
    public int getItemCount() {
        return offersCardList.size(); // get size of the offers list
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView offererIDDisplay;
        private TextView tripTimeDisplay;
        private TextView numPassengersDisplay;
        private TextView fareDisplay;
        Button googleMapsButton, confirmRequestButton;
        OffersButtonClickListener buttonClickListener;

        public ViewHolder(@NonNull View itemView, OffersButtonClickListener buttonClickListener) {
            super(itemView);

            // retrieve all the ids from XML file
            offererIDDisplay = itemView.findViewById(R.id.offerer_ID);
            tripTimeDisplay = itemView.findViewById(R.id.trip_time);
            numPassengersDisplay = itemView.findViewById(R.id.num_passengers);
            fareDisplay = itemView.findViewById(R.id.fare);
            googleMapsButton = itemView.findViewById(R.id.show_potential_route);
            confirmRequestButton = itemView.findViewById(R.id.confirm_request_button);

            // listen if the buttons got clicked (works for each individual card)
            this.buttonClickListener = buttonClickListener;
            googleMapsButton.setOnClickListener(this);
            confirmRequestButton.setOnClickListener(this);
        }

        public void setData(String offererID, String tripTime, String numPassengers, String farePrice) {
            // sets the text on the xml to the provided information
            offererIDDisplay.setText(offererID);
            tripTimeDisplay.setText(tripTime);
            numPassengersDisplay.setText(numPassengers);
            fareDisplay.setText(farePrice);
        }

        @Override
        public void onClick(View view) {
            // handles the button clicks
            if(view.getId() == R.id.show_potential_route)
                buttonClickListener.onMapsButtonClick(getAdapterPosition());
            if(view.getId() == R.id.confirm_request_button)
                buttonClickListener.onConfirmRequestButtonClick(getAdapterPosition());
        }
    }
    public interface OffersButtonClickListener {
        // interface for the button clicks
        void onMapsButtonClick(int position);

        void onConfirmRequestButtonClick(int position);
    }

}
