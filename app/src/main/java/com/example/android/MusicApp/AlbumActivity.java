package com.example.android.MusicApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class AlbumActivity extends AppCompatActivity {

    private ArrayList<Library.Song> songs;
    private ListView songsListView;
    private ImageView albumImage;
    private ImageView backButton;
    private Spinner spinner;
    private Library.Album album;
    private Library library;
    private ArrayAdapter<CharSequence> adapter;
    private Intent receivedIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        library = new Library();
        albumImage = findViewById(R.id.album_activity_image);
        songsListView = findViewById(R.id.album_activity_list_view);
        backButton = findViewById(R.id.album_activity_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Setting the spinner for the settings menu
        spinner = findViewById(R.id.album_activity_settings_spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.settings_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        receivedIntent = getIntent();
        album = library.getSpecificAlbum(receivedIntent.getStringExtra("album title"));
        songs = album.getAlbumSongs();
        albumImage.setImageResource(album.getImageId());

        //Populating the grid for the songs
        songs();
    }

    private void songs() {
        View.OnClickListener songListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Library.Song song = (Library.Song) view.getTag();
                Intent songPlayingNowIntent = new Intent(AlbumActivity.this, PlayingNowActivity.class);
                songPlayingNowIntent.putExtra("song title", song.getSongTitle());
                songPlayingNowIntent.putExtra("type", "Album song");
                startActivity(songPlayingNowIntent);
            }

        };
        SongAdapter adapter = new SongAdapter(this, songs, songListener);
        songsListView.setAdapter(adapter);
    }
}
