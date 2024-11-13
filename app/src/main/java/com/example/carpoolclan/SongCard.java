package com.example.carpoolclan;

public class SongCard {
    private String index;
    private String songName;
    private String artistName;

    public SongCard(String index, String songName, String artistName) {
        this.index = index;
        this.songName = songName;
        this.artistName = artistName;
    }

    public String getIndex() { return index; }

    public String getSongName() {
        return songName;
    }

    public String getArtistName() {
        return artistName;
    }
}
