package com.example.carpoolclan;

import java.util.ArrayList;
import java.util.List;

public class MixMasterController {
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

        // initial: if there are any songs in the DB add it here
        // must do this because users can leave this app, and all info will disappear??

    }

    public SongCard getSong(int position) {
        return songList.get(position);
    }

    public void addSong(SongCard song) {
        // CODE: add this song to the DB
        System.out.println("Adding: " + song.getSongName());
        songList.add(song);
    }

    public void removeSong(int position) {
        // CODE: remove this song from the DB
        System.out.println("Removing: " + songList.get(position).getSongName());
        songList.remove(position);
    }

    public int getSongListSize() {
        return songList.size();
    }

    public Boolean validateSong(String songName, String songArtist) {
        if (spotify.validateSong(songName, songArtist)){
            addSong(new SongCard(songName, songArtist));
            return true;
        }
        return false;
    }
}
