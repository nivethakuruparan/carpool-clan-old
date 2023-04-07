package com.example.carpoolclan;

import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MixMasterController {

    SpotifyAPI spotify;
    private final List<SongCard> songList; // the list of songs that is displayed on screen

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

        // initial: if there are any songs in the DB add it here
        // must do this because users can leave this app, and all info will disappear??
    }

    public List<SongCard> getSongList() {
        return songList;
    }

    public void addSong(SongCard song) {
        songList.add(song);

        // add this song to the DB
    }

    public void removeSong(int position) {
        songList.remove(position);

        // remove this song from the DB
    }

    public int getSongListSize() {
        return songList.size();
    }

    public Boolean validateSong(String songName, String songArtist) {
        if (spotify.validateSong(songName, songArtist)){
            addSong(new SongCard(songName, songArtist, "12.00"));
            return true;
        }
        return false;
    }
}
