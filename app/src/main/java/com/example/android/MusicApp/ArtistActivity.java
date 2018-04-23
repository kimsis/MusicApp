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

public class ArtistActivity extends AppCompatActivity {

    private Library library;
    private ImageView backButton;
    private ImageView artistImage;
    private ListView albumsListView;
    private Spinner spinner;
    private ArrayList<Library.Album> albums;
    private ArrayAdapter<CharSequence> adapter;
    private Intent receivedIntent;
    private Library.Artist artist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        library = new Library();
        backButton = findViewById(R.id.artist_activity_back_button);
        artistImage = findViewById(R.id.artist_activity_image);
        albumsListView = findViewById(R.id.artist_view_list);

        //Setting the spinner for the settings menu
        spinner = findViewById(R.id.artist_activity_settings_spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.settings_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        receivedIntent = getIntent();
        artist = library.getSpecificArtist(receivedIntent.getStringExtra("artist name"));
        albums = artist.getArtistAlbums();
        artistImage.setImageResource(artist.getImageId());
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Populating the grid for the albums
        albums();
    }

    private void albums() {
        View.OnClickListener albumListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Library.Album album = (Library.Album) view.getTag();
                Intent albumIntent = new Intent(ArtistActivity.this, AlbumActivity.class);
                albumIntent.putExtra("album title", album.getTitle());
                startActivity(albumIntent);
            }
        };
        View.OnClickListener albumPlayingNowListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Library.Album album = (Library.Album) view.getTag();
                Intent albumPlayingNowIntent = new Intent(ArtistActivity.this, PlayingNowActivity.class);
                albumPlayingNowIntent.putExtra("album title", album.getTitle());
                albumPlayingNowIntent.putExtra("type", "Album");
                startActivity(albumPlayingNowIntent);
            }
        };

        AlbumAdapter adapter = new AlbumAdapter(this, albums, albumListener, albumPlayingNowListener);
        albumsListView.setAdapter(adapter);
    }
}
