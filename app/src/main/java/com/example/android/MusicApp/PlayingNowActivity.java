package com.example.android.MusicApp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//An activity for the currently played song
public class PlayingNowActivity extends AppCompatActivity {

    private Library.Artist artist;
    private Library.Song song;
    private Library.Album album;
    private Library library;
    private TextView artistName;
    private TextView albumName;
    private TextView songName;
    private ImageView image;
    private ImageView previousSong;
    private ImageView nextSong;
    private ImageView playSong;
    private ImageView backButton;
    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;
    private ArrayList<Library.Song> songsToDisplay;
    private Intent receivedIntent;
    private int position = 0;
    private View.OnClickListener next;
    private View.OnClickListener previous;
    private View.OnClickListener play;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_now);

        library = new Library();
        image = findViewById(R.id.playing_now_activity_image);
        artistName = findViewById(R.id.song_artist_name);
        albumName = findViewById(R.id.song_album_title);
        songName = findViewById(R.id.song_name);
        nextSong = findViewById(R.id.next);
        previousSong = findViewById(R.id.previous);
        playSong = findViewById(R.id.play);

        spinner = findViewById(R.id.playing_now_activity_settings_spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.settings_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        backButton = findViewById(R.id.playing_now_activity_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        receivedIntent = getIntent();
        type = receivedIntent.getStringExtra("type");
        if (type.equals("Song")) {
            songsToDisplay = library.getLibrarySongs();
            position = library.getSongPosition(receivedIntent.getStringExtra("song title"));
            displaySong();

        } else if (type.equals("Album")) {
            album = library.getSpecificAlbum(receivedIntent.getStringExtra("album title"));
            songsToDisplay = album.getAlbumSongs();
            displaySong();
        } else if (type.equals("Album song")) {
            song = library.getSpecificSong(receivedIntent.getStringExtra("song title"));
            album = library.getSpecificAlbum(song.getAlbumTitle());
            position = album.getAlbumSongPosition(song.getSongTitle());
            songsToDisplay = album.getAlbumSongs();
            displaySong();
        }

        next = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position + 1 != songsToDisplay.size())
                    changeSong(1);
                else {
                    Context context = getApplicationContext();
                    CharSequence toastCharSequence = "This is the last song!";
                    Toast toast = Toast.makeText(context, toastCharSequence, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        };
        previous = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position != 0)
                    changeSong(-1);
                else {
                    Context context = getApplicationContext();
                    CharSequence toastCharSequence = "This is the first song!";
                    Toast toast = Toast.makeText(context, toastCharSequence, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        };
        play = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence toastCharSequence = "To be implemented, knowledge not enough yet!";
                Toast toast = Toast.makeText(context, toastCharSequence, Toast.LENGTH_SHORT);
                toast.show();
            }
        };

        nextSong.setOnClickListener(next);
        previousSong.setOnClickListener(previous);
        playSong.setOnClickListener(play);
    }

    public void displaySong() {
        artist = library.getSpecificArtist(songsToDisplay.get(position).getArtistName());
        album = library.getSpecificAlbum(songsToDisplay.get(position).getAlbumTitle());
        song = library.getSpecificSong(songsToDisplay.get(position).getSongTitle());
        artistName.setText(artist.getArtistName());
        albumName.setText(album.getTitle());
        songName.setText(song.getSongTitle());
        switch (type) {
            case "Song": {
                image.setImageResource(artist.getImageId());
                break;
            }
            case "Album": {
                image.setImageResource(album.getImageId());
                break;
            }
            case "Album song": {
                image.setImageResource(album.getImageId());
                break;
            }
            default: {
                image.setImageResource(R.drawable.error);
                artistName.setText(R.string.error);
                albumName.setText(R.string.error);
                songName.setText(R.string.error);
                break;
            }
        }
    }

    public void changeSong(int changeAttribute) {
        position += changeAttribute;
        displaySong();
    }
}