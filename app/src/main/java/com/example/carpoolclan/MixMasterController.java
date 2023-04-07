package com.example.carpoolclan;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MixMasterController {

    private final List<SongCard> songList; // the list of songs that is displayed on screen
    private SongCard currentSong; // this is the current song that is playing

    public MixMasterController() {
        songList = new ArrayList<>();

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
        System.out.println("Validating Song Request");
        addSong(new SongCard(songName, songArtist, "12:00"));
        return true;
    }

    public void displayNewCurrentSong(TextView currentSongText, TextView currentSongArtist) {
        currentSongText.setText(currentSong.getSongName());
        currentSongArtist.setText(currentSong.getArtistName());
    }
}
