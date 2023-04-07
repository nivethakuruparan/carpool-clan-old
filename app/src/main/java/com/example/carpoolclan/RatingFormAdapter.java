package com.example.carpoolclan;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RatingFormAdapter extends RecyclerView.Adapter<RatingFormViewHolder> {

    TripDurationController tripDuration;

    public RatingFormAdapter(TripDurationController tripDuration) {
        this.tripDuration = tripDuration;
    }

    @NonNull
    @Override
    public RatingFormViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_rating, parent,false);
        return new RatingFormViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingFormViewHolder holder, int position) {
        holder.customerID.setText(tripDuration.getCustomerID(position));
    }

    @Override
    public int getItemCount() {
        return tripDuration.getCustomerIDsListSize();
    }
}

class RatingFormViewHolder extends RecyclerView.ViewHolder {
    TextView customerID;
    RatingBar ratingBar;
    private RatingFormAdapter adapter;

    public RatingFormViewHolder(@NonNull View itemView) {
        super(itemView);

        customerID = itemView.findViewById(R.id.customer_name);
        ratingBar = itemView.findViewById(R.id.rating_score);

        // handle submit button click
        itemView.findViewById(R.id.submit_rating_button).setOnClickListener(view -> {
            // users must input a rating between 1-5; will receive a message otherwise
            if (ratingBar.getRating() != 0.0) {
                adapter.tripDuration.receivedRating(getAdapterPosition(), ratingBar.getRating());
                adapter.notifyItemRemoved(getAdapterPosition());
            } else {
                Toast.makeText(itemView.getContext(), "Please input a rating for passenger: " + customerID.getText().toString(), Toast.LENGTH_SHORT).show();
            }

            // if there are no more ratings to do, send user back to home page
            if(adapter.getItemCount() == 0) {
                Toast.makeText(itemView.getContext(), "Thank you for completing the Rating Form!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(itemView.getContext(), HomePage.class);
                itemView.getContext().startActivity(intent);
            }
        });
    }

    public RatingFormViewHolder linkAdapter(RatingFormAdapter adapter) {
        this.adapter = adapter;
        return this;
    }
}
