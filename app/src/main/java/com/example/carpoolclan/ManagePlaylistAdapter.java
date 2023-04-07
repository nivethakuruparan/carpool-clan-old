package com.example.carpoolclan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ManagePlaylistAdapter extends RecyclerView.Adapter<ManagePlaylistViewHolder> {

    MixMasterController mixMaster;

    public ManagePlaylistAdapter(MixMasterController mixMaster) {
        this.mixMaster = mixMaster;
    }

    @NonNull
    @Override
    public ManagePlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_songs, parent, false);
        return new ManagePlaylistViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ManagePlaylistViewHolder holder, int position) {
        holder.songName.setText(mixMaster.getSong(position).getSongName());
        holder.artistName.setText(mixMaster.getSong(position).getArtistName());
    }

    @Override
    public int getItemCount() {
        return mixMaster.getSongListSize();
    }
}

class ManagePlaylistViewHolder extends RecyclerView.ViewHolder {
    TextView songName, artistName;
    private ManagePlaylistAdapter adapter;

    public ManagePlaylistViewHolder(@NonNull View itemView) {
        super(itemView);
        songName = itemView.findViewById(R.id.song_name);
        artistName = itemView.findViewById(R.id.artist_name);

        itemView.findViewById(R.id.delete_song_button).setOnClickListener(view -> {
            adapter.mixMaster.removeSong(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());
        });
    }

    public ManagePlaylistViewHolder linkAdapter(ManagePlaylistAdapter adapter) {
        this.adapter = adapter;
        return this;
    }
}