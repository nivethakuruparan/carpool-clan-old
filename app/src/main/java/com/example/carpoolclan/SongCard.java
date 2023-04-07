package com.example.carpoolclan;

public class SongCard {
    private String songName;
    private String artistName;

    public SongCard(String songName, String artistName) {
        this.songName = songName;
        this.artistName = artistName;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtistName() {
        return artistName;
    }

}
