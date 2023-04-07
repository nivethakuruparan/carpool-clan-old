package com.example.carpoolclan;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PotentialOffersAdapter extends RecyclerView.Adapter<PotentialOffersViewHolder> {

    DispatcherController dispatcherController;

    public PotentialOffersAdapter(DispatcherController dispatcherController) {
        this.dispatcherController = dispatcherController;
    }

    @NonNull
    @Override
    public PotentialOffersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_potential_offers, parent,false);
        return new PotentialOffersViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull PotentialOffersViewHolder holder, int position) {
        holder.offererID.setText(dispatcherController.getPotentialOffer(position).getOffererID());
        holder.tripTime.setText(dispatcherController.getPotentialOffer(position).getTripTime());
        holder.numPassengers.setText(dispatcherController.getPotentialOffer(position).getNumPassengers());
        holder.farePrice.setText(dispatcherController.getPotentialOffer(position).getFarePrice());
    }

    @Override
    public int getItemCount() {
        return dispatcherController.getPotentialOffersListSize();
    }
}

class PotentialOffersViewHolder extends RecyclerView.ViewHolder {
    TextView offererID, tripTime, numPassengers, farePrice;
    private PotentialOffersAdapter adapter;

    public PotentialOffersViewHolder(@NonNull View itemView) {
        super(itemView);

        offererID = itemView.findViewById(R.id.offerer_ID);
        tripTime = itemView.findViewById(R.id.trip_time);
        numPassengers = itemView.findViewById(R.id.num_passengers);
        farePrice = itemView.findViewById(R.id.fare);

        // handle google maps button click
        itemView.findViewById(R.id.show_potential_route).setOnClickListener(view ->{
            Intent intent = new Intent(itemView.getContext(), GoogleMapsInterface.class);
            intent.putExtra("polyLine", adapter.dispatcherController.getPotentialOffer(getAdapterPosition()).getPolyLine());
            itemView.getContext().startActivity(intent);
        });

        // handle confirm button click
        itemView.findViewById(R.id.confirm_request_button).setOnClickListener(view -> {
            adapter.dispatcherController.receivedPotentialOfferConfirmation(getAdapterPosition());
            // CODE: what happens after button click is pressed
        });
    }

    public PotentialOffersViewHolder linkAdapter(PotentialOffersAdapter adapter) {
        this.adapter = adapter;
        return this;
    }
}