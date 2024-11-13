package com.example.carpoolclan;

import android.content.Intent;
import android.se.omapi.Session;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MixMasterController {
    DatabaseReference reference;
    SessionController session;
    TripDurationController tripDurationController;
    SpotifyAPI spotify;
    private static List<SongCard> songList; // the list of songs that is displayed on screen

    public MixMasterController() {
        songList = new ArrayList<>();

        // to access the spotify DB
        Thread thread = new Thread(() -> {
            try  {
                spotify = new SpotifyAPI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();

        tripDurationController = new TripDurationController();
        session = new SessionController();

        // initial: if there are any songs in the DB add it here
        // must do this because users can leave this app, and all info will disappear??
        String tripID = session.encrypt(tripDurationController.getTripID());
        reference = FirebaseDatabase.getInstance().getReference("mixmaster");
        Query checkDatabase = reference.orderByChild("tripID").equalTo(session.encrypt(tripID));
        checkDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshots) {
                if (snapshots.exists()) {
                    for (DataSnapshot snapshot: snapshots.getChildren()) {
                        String index = session.decrypt(snapshot.getValue(String.class));
                        String songName = session.decrypt(snapshot.child("songName").getValue(String.class));
                        String artistName = session.decrypt(snapshot.child("artistName").getValue(String.class));
                        SongCard song = new SongCard(index, songName, artistName);
                        addSong(song);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public SongCard getSong(int position) {
        return songList.get(position);
    }

    public void addSong(SongCard song) {
        // CODE: add this song to the DB
        session.storeMixMasterData(tripDurationController.getTripID(), song);
        System.out.println("Adding: " + song.getSongName());
        songList.add(song);
    }

    public void removeSong(int position) {
        // CODE: remove this song from the DB
        SongCard song = songList.get(position);
        session.removeMixMasterData(tripDurationController.getTripID(), song);
        System.out.println("Removing: " + songList.get(position).getSongName());
        songList.remove(position);
    }

    public int getSongListSize() {
        return songList.size();
    }

    public Boolean validateSong(String songName, String songArtist) {
        if (spotify.validateSong(songName, songArtist)){
            addSong(new SongCard(String.valueOf(getSongListSize()), songName, songArtist));
            return true;
        }
        return false;
    }
}
