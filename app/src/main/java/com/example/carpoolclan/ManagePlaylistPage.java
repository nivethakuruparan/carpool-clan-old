package com.example.carpoolclan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ManagePlaylistPage extends AppCompatActivity {

    MixMasterController mixMaster = new MixMasterController();
    Button addSong;
    TextView homePageRedirect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_playlist_page);

        RecyclerView recyclerView = findViewById(R.id.display_songs_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ManagePlaylistAdapter adapter = new ManagePlaylistAdapter(mixMaster);
        recyclerView.setAdapter(adapter);

        addSong = findViewById(R.id.add_song_button);
        homePageRedirect = findViewById(R.id.home_page_redirect);


        homePageRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(ManagePlaylistPage.this, HomePage.class);
            startActivity(intent);
        });

        addSong.setOnClickListener(view -> {
            Dialog dialog = new Dialog(ManagePlaylistPage.this);
            dialog.setContentView(R.layout.display_search_songs);

            EditText searchSongName = dialog.findViewById(R.id.search_song_name);
            EditText searchSongArtist = dialog.findViewById(R.id.search_song_artist);
            Button searchSong = dialog.findViewById(R.id.search_song_button);
            TextView managePlaylistRedirect = dialog.findViewById(R.id.search_songs_back_redirect);

            // lets user leave the dialog box without entering a song
            managePlaylistRedirect.setOnClickListener(view1 -> dialog.dismiss());

            searchSong.setOnClickListener(view1 -> {
                String songNameSearch;
                String artistNameSearch;

                // check if the fields are empty; if empty set an error
                if (searchSongName.getText().toString().isEmpty())
                    searchSongName.setError("Field Cannot Be Empty");
                if (searchSongArtist.getText().toString().isEmpty())
                    searchSongArtist.setError("Field Cannot Be Empty");

                // if the fields are not empty
                if (!searchSongName.getText().toString().isEmpty() && !searchSongArtist.getText().toString().isEmpty()) {
                    songNameSearch = searchSongName.getText().toString();
                    artistNameSearch = searchSongArtist.getText().toString();

                    // checks and adds song to playlist if music exists
                    if (mixMaster.validateSong(songNameSearch, artistNameSearch)) {
                        // song has been added to playlist; notify adapter
                        adapter.notifyItemInserted(mixMaster.getSongListSize() - 1);
                        dialog.dismiss();
                    } else {
                        // song does not exist
                        searchSongName.setError("Unable to find Song");
                        searchSongArtist.setError("Unable to find Song");
                    }
                }
            });
            dialog.show();
        });
    }
}