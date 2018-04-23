package com.example.android.MusicApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Creating the Library and the necessary Views to be used
    private Library library;
    private GridView artistsGridView;
    private ListView albumsListView;
    private ListView songsListView;
    private View mainLayout;
    private View artistsClicked;
    private View albumsClicked;
    private View songsClicked;
    private View artistsButton;
    private View albumsButton;
    private View songsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initializing the Library and creating the appropriate references towards the XML Views
        library = new Library();
        artistsGridView = findViewById(R.id.artists_grid_view);
        albumsListView = findViewById(R.id.albums_list_view);
        songsListView = findViewById(R.id.songs_list_view);
        mainLayout = findViewById(R.id.main_layout);
        artistsClicked = findViewById(R.id.artists_clicked);
        albumsClicked = findViewById(R.id.albums_clicked);
        songsClicked = findViewById(R.id.songs_clicked);
        artistsButton = findViewById(R.id.artists_button);
        albumsButton = findViewById(R.id.albums_button);
        songsButton = findViewById(R.id.songs_button);

        //Initializing the scrollable views
        artists();
        albums();
        songs();

        //Setting the initial setup to be the songs ListView
        onSongsIsClicked();

        //Setting Listeners on the Toolbar LinearLayouts to be used as buttons
        artistsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onArtistsIsClicked();
            }
        });
        albumsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAlbumsIsClicked();
            }
        });
        songsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSongsIsClicked();
            }
        });
    }

    private void onArtistsIsClicked() {
        artistsGridView.setVisibility(View.VISIBLE);
        albumsListView.setVisibility(View.GONE);
        songsListView.setVisibility(View.GONE);
        artistsClicked.setBackgroundColor(0xff0000ff);
        albumsClicked.setBackgroundColor(0xff000000);
        songsClicked.setBackgroundColor(0xff000000);
        mainLayout.setBackgroundColor(0xff888888);
    }

    private void onAlbumsIsClicked() {
        artistsGridView.setVisibility(View.GONE);
        albumsListView.setVisibility(View.VISIBLE);
        songsListView.setVisibility(View.GONE);
        artistsClicked.setBackgroundColor(0xff000000);
        albumsClicked.setBackgroundColor(0xff0000ff);
        songsClicked.setBackgroundColor(0xff000000);
        mainLayout.setBackgroundColor(0xff000000);
    }

    private void onSongsIsClicked() {
        artistsGridView.setVisibility(View.GONE);
        albumsListView.setVisibility(View.GONE);
        songsListView.setVisibility(View.VISIBLE);
        artistsClicked.setBackgroundColor(0xff000000);
        albumsClicked.setBackgroundColor(0xff000000);
        songsClicked.setBackgroundColor(0xff0000ff);
        mainLayout.setBackgroundColor(0xff000000);
    }

    private void artists() {
        View.OnClickListener artistListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Library.Artist artist = (Library.Artist) view.getTag();
                Intent artistIntent = new Intent(MainActivity.this, ArtistActivity.class);
                artistIntent.putExtra("artist name", artist.getArtistName());
                startActivity(artistIntent);
            }
        };
        ArrayList<Library.Artist> artists = library.getLibraryArtists();
        ArtistAdapter adapter = new ArtistAdapter(this, artists, artistListener);
        artistsGridView.setAdapter(adapter);
    }

    private void albums() {
        View.OnClickListener albumListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Library.Album album = (Library.Album) view.getTag();
                Intent albumIntent = new Intent(MainActivity.this, AlbumActivity.class);
                albumIntent.putExtra("album title", album.getTitle());
                startActivity(albumIntent);
            }
        };
        View.OnClickListener albumPlayingNowListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Library.Album album = (Library.Album) view.getTag();
                Intent albumPlayingNowIntent = new Intent(MainActivity.this, PlayingNowActivity.class);
                albumPlayingNowIntent.putExtra("album title", album.getTitle());
                albumPlayingNowIntent.putExtra("type", "Album");
                startActivity(albumPlayingNowIntent);
            }
        };

        ArrayList<Library.Album> albums = library.getLibraryAlbums();
        AlbumAdapter adapter = new AlbumAdapter(this, albums, albumListener, albumPlayingNowListener);
        albumsListView.setAdapter(adapter);
    }

    private void songs() {
        View.OnClickListener songListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Library.Song song = (Library.Song) view.getTag();
                Intent songPlayingNowIntent = new Intent(MainActivity.this, PlayingNowActivity.class);
                songPlayingNowIntent.putExtra("song title", song.getSongTitle());
                songPlayingNowIntent.putExtra("type", "Song");
                startActivity(songPlayingNowIntent);
            }
        };
        ArrayList<Library.Song> songs = library.getLibrarySongs();
        SongAdapter adapter = new SongAdapter(this, songs, songListener);
        songsListView.setAdapter(adapter);
    }
}
