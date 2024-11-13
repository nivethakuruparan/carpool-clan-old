package com.example.carpoolclan;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class IncomingRequestsAdapter extends RecyclerView.Adapter<IncomingRequestsViewHolder> {

    DispatcherController dispatcherController;

    public IncomingRequestsAdapter(DispatcherController dispatcherController){
        this.dispatcherController = dispatcherController;
    }

    @NonNull
    @Override
    public IncomingRequestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_incoming_requests, parent,false);
        return new IncomingRequestsViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomingRequestsViewHolder holder, int position) {
        holder.requesterID.setText(dispatcherController.getIncomingRequest(position).getRequesterID());
        holder.tripTime.setText(dispatcherController.getIncomingRequest(position).getTripTime());
        holder.numPassengers.setText(dispatcherController.getIncomingRequest(position).getNumPassengers());
        holder.farePrice.setText(dispatcherController.getIncomingRequest(position).getFarePrice());
    }

    @Override
    public int getItemCount() {
        return dispatcherController.getIncomingRequestsListSize();
    }
}

class IncomingRequestsViewHolder extends RecyclerView.ViewHolder {
    TextView requesterID, tripTime, numPassengers, farePrice;
    private IncomingRequestsAdapter adapter;

    public IncomingRequestsViewHolder(@NonNull View itemView) {
        super(itemView);

        requesterID = itemView.findViewById(R.id.requester_email);
        tripTime = itemView.findViewById(R.id.trip_time);
        numPassengers = itemView.findViewById(R.id.num_passengers);
        farePrice = itemView.findViewById(R.id.fare);

        // handle google maps button click
        itemView.findViewById(R.id.show_potential_route).setOnClickListener(view ->{
            Intent intent = new Intent(itemView.getContext(), GoogleMapsInterface.class);
            intent.putExtra("polyLine", adapter.dispatcherController.getIncomingRequest(getAdapterPosition()).getPolyLine());
            itemView.getContext().startActivity(intent);
        });

        // handle delete button click
        itemView.findViewById(R.id.deny_request_button).setOnClickListener(view -> {
            adapter.dispatcherController.receivedIncomingRequestDenial(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());
        });

        // handle confirm button click
        itemView.findViewById(R.id.confirm_request_button).setOnClickListener(view -> {
            adapter.dispatcherController.receivedIncomingRequestConfirmation(getAdapterPosition());
            // CODE: what happens after button click is pressed
        });
    }

    public IncomingRequestsViewHolder linkAdapter(IncomingRequestsAdapter adapter) {
        this.adapter = adapter;
        return this;
    }
}