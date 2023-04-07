package com.example.carpoolclan;

public class SongCard {
    String songName;
    String artistName;
    String duration;

    public SongCard(String songName, String artistName, String duration) {
        this.songName = songName;
        this.artistName = artistName;
        this.duration = duration;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getDuration() {
        return duration;
    }
}
