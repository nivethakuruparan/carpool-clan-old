package com.example.carpoolclan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SpotifyAPI {
    // first index song name, second index song artist
    String[][] dataset;
    // full pulled dataset with the following columns
    // track_id,track_name,track_artist,track_popularity,track_album_id,
    // track_album_name,track_album_release_date,playlist_name,playlist_id,
    // playlist_genre,playlist_subgenre,danceability,energy,key,loudness,
    // mode,speechiness,acousticness,instrumentalness,liveness,valence,tempo,duration_ms
    String[][] fullDataset;

    public SpotifyAPI() throws IOException {
        // Read CSV file from URL
        String csvUrl = "https://raw.githubusercontent.com/rfordatascience/tidytuesday/master/data/2020/2020-01-21/spotify_songs.csv";
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new URL(csvUrl).openStream(), StandardCharsets.UTF_8));

        // Create list to hold rows
        List<String[]> rows = new ArrayList<>();

        // Read CSV data and store in list
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            rows.add(values);
        }

        // Close resources
        br.close();

        // Convert list to 2D array
        String[][] data = new String[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            data[i] = rows.get(i);
        }
        this.fullDataset = data;
        this.dataset = new String[data.length][2];
        int i = 0;
        // everything lower case and delete the blank spaces
        for (String[] row : data) {
            // index 1 = song name and 2 = artist name is needed
            String song = row[1].toLowerCase().replaceAll("\\s+", "");
            String artist = row[2].toLowerCase().replaceAll("\\s+", "");
            dataset[i][0] = song;
            dataset[i][1] = artist;
            i++;

        }

    }

    public boolean validateSong(String song, String artist) {
        String searchSong = song.toLowerCase().replaceAll("\\s+", "");
        String serachArtist = artist.toLowerCase().replaceAll("\\s+", "");

        for (String[] row : this.dataset) {
            if (row[0].equals(searchSong) && row[1].equals(serachArtist)) {
                return true;
            }
        }

        return false;
    }
}
