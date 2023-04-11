package com.example.carpoolclan;

public class MixMasterInfoHelper {
    String tripID, index, songName, artistName;

    public String getTripID() {
        return tripID.replaceAll("@","/");
    }

    public void setTripID(String tripID) {
        this.tripID = tripID.replaceAll("/", "@");
    }

    public String getIndex() {
        return index.replaceAll("@","/");
    }

    public void setIndex(String index) {
        this.index = index.replaceAll("/","@");
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public MixMasterInfoHelper(String tripID, String index, String songName, String artistName) {
        setTripID(tripID);
        setIndex(index);
        setSongName(songName);
        setArtistName(artistName);
    }

    public MixMasterInfoHelper() {
    }
}
