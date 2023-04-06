package com.example.carpoolclan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IncomingRequestsAdapter extends RecyclerView.Adapter<IncomingRequestsAdapter.ViewHolder> {

    private List<IncomingRequestsCard> requestsCardList; // a list of all offers (offers is a class)
    RequestsButtonClickListener buttonClickListener; // need to listen for a button click

    public IncomingRequestsAdapter(List<IncomingRequestsCard> requestsCardList, RequestsButtonClickListener buttonClickListener) {
        this.requestsCardList = requestsCardList;
        this.buttonClickListener = buttonClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // display the individual card; getting card design from display_potential_offers.xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_incoming_requests,parent,false);
        return new ViewHolder(view, buttonClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // sets the text to the given information from the card
        String requesterID = requestsCardList.get(position).getRequesterID();
        String tripTime = requestsCardList.get(position).getTripTime();
        String numPassengers = requestsCardList.get(position).getNumPassengers();
        String farePrice = requestsCardList.get(position).getFarePrice();

        holder.setData(requesterID, tripTime, numPassengers, farePrice); // this function is found in the ViewHolder class below
    }

    @Override
    public int getItemCount() {
        return requestsCardList.size(); // get size of the offers list
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView requesterIDDisplay;
        private TextView tripTimeDisplay;
        private TextView numPassengersDisplay;
        private TextView fareDisplay;
        Button googleMapsButton, confirmRequestButton, rejectRequestButton;
        RequestsButtonClickListener buttonClickListener;

        public ViewHolder(@NonNull View itemView, RequestsButtonClickListener buttonClickListener) {
            super(itemView);

            // retrieve all the ids from XML file
            requesterIDDisplay = itemView.findViewById(R.id.requester_email);
            tripTimeDisplay = itemView.findViewById(R.id.trip_time);
            numPassengersDisplay = itemView.findViewById(R.id.num_passengers);
            fareDisplay = itemView.findViewById(R.id.fare);
            googleMapsButton = itemView.findViewById(R.id.show_potential_route);
            confirmRequestButton = itemView.findViewById(R.id.confirm_request_button);
            rejectRequestButton = itemView.findViewById(R.id.deny_request_button);

            // listen if the buttons got clicked (works for each individual card)
            this.buttonClickListener = buttonClickListener;
            googleMapsButton.setOnClickListener(this);
            confirmRequestButton.setOnClickListener(this);
            rejectRequestButton.setOnClickListener(this);
        }

        public void setData(String offererID, String tripTime, String numPassengers, String farePrice) {
            // sets the text on the xml to the provided information
            requesterIDDisplay.setText(offererID);
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
            if(view.getId() == R.id.deny_request_button)
                buttonClickListener.onRejectRequestButtonClick(getAdapterPosition());
        }
    }
    public interface RequestsButtonClickListener {
        // interface for the button clicks
        void onMapsButtonClick(int position);

        void onConfirmRequestButtonClick(int position);

        void onRejectRequestButtonClick(int position);
    }
}
