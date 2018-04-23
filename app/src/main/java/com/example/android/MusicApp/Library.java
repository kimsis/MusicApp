package com.example.android.MusicApp;

import java.util.ArrayList;

public class Library {
    //Initializing the ArrayLists for the Artists, Albums and Songs
    private ArrayList<Artist> libraryArtists;
    private ArrayList<Album> libraryAlbums;
    private ArrayList<Song> librarySongs;

    // constuctor
    public Library() {
        this.librarySongs = makeSongs();
        this.libraryAlbums = makeAlbums();
        this.libraryArtists = makeArtists();
    }

    //Some getter methods for getting specific items

    public Album getSpecificAlbum(String albumName) {
        Album album = null;
        for (int i = 0; i < libraryAlbums.size(); i++) {
            if (libraryAlbums.get(i).getTitle().equals(albumName)) {
                album = libraryAlbums.get(i);
                break;
            }
        }
        return album;
    }

    public Artist getSpecificArtist(String artistName) {
        Artist artist = null;
        for (int i = 0; i < libraryArtists.size(); i++) {
            if (libraryArtists.get(i).getArtistName().equals(artistName)) {
                artist = libraryArtists.get(i);
                break;
            }
        }
        return artist;
    }

    public Song getSpecificSong(String songName) {
        Song song = null;
        for (int i = 0; i < librarySongs.size(); i++) {
            if (librarySongs.get(i).getSongTitle().equals(songName)) {
                song = librarySongs.get(i);
                break;
            }
        }
        return song;
    }

    public int getSongPosition(String songTitle) {
        int position = -1;
        for (int i = 0; i < librarySongs.size(); i++) {
            if (librarySongs.get(i).getSongTitle().equals(songTitle)) {
                position = i;
                break;
            }
        }
        return position;
    }

    //Some getter methods for the three types of array lists used

    public ArrayList<Artist> getLibraryArtists() {
        return libraryArtists;
    }

    public ArrayList<Album> getLibraryAlbums() {
        return libraryAlbums;
    }

    public ArrayList<Song> getLibrarySongs() {
        return librarySongs;
    }

    //Constructing the library Arrays

    private ArrayList<Album> makeAlbums() {
        ArrayList<Album> albums = new ArrayList<>();
        albums.add(new Album("Fall Out Boy", "Save Rock And Roll", R.drawable.fall_out_boy_save_rock_and_roll));
        albums.add(new Album("Fall Out Boy", "American Beauty/American Psycho", R.drawable.fall_out_boy_american_beauty_american_psycho));
        albums.add(new Album("Fort Minor", "The Rising Tied", R.drawable.fort_minor_the_rising_tied));
        albums.add(new Album("Kongos", "Lunatic", R.drawable.kongos_lunatic));
        albums.add(new Album("Queen", "A Night At The Opera", R.drawable.queen_a_night_at_the_opera));
        albums.add(new Album("Queen", "News Of The World", R.drawable.queen_news_of_the_world));
        albums.add(new Album("Thirty Seconds To Mars", "This Is War", R.drawable.thirty_seconds_to_mars_this_is_war));
        albums.add(new Album("Thousand Foot Crutch", "The End Is Where We Begin", R.drawable.thousand_foot_crutch_the_end_is_where_we_begin));


        return albums;
    }

    private ArrayList<Artist> makeArtists() {
        ArrayList<Artist> artists = new ArrayList<>();
        artists.add(new Artist("Fall Out Boy", R.drawable.fall_out_boy));
        artists.add(new Artist("Fort Minor", R.drawable.fort_minor));
        artists.add(new Artist("Kongos", R.drawable.kongos));
        artists.add(new Artist("Queen", R.drawable.queen));
        artists.add(new Artist("Thirty Seconds To Mars", R.drawable.thirty_seconds_to_mars));
        artists.add(new Artist("Thousand Foot Crutch", R.drawable.thousand_foot_crutch));

        return artists;
    }

    private ArrayList<Song> makeSongs() {
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("Save Rock And Roll", "My Songs Know What You Did In The Dark", "Fall Out Boy", 3, 7));
        songs.add(new Song("Save Rock And Roll", "The Phoenix", "Fall Out Boy", 4, 6));
        songs.add(new Song("American Beauty/American Psycho", "Irresistible", "Fall Out Boy", 3, 28));
        songs.add(new Song("American Beauty/American Psycho", "Centuries", "Fall Out Boy", 4, 32));

        songs.add(new Song("The Rising Tied", "Remember The Name", "Fort Minor", 3, 53));
        songs.add(new Song("The Rising Tied", "Petrified", "Fort Minor", 3, 41));

        songs.add(new Song("Lunatic", "Come With Me Now", "Kongos", 3, 32));
        songs.add(new Song("Lunatic", "I'm Only Joking", "Kongos", 4, 28));

        songs.add(new Song("A Night At The Opera", "Bohemian Rhapsody", "Queen", 5, 58));
        songs.add(new Song("A Night At The Opera", "God Save The Queen", "Queen", 1, 16));
        songs.add(new Song("News Of The World", "We Will Rock You", "Queen", 2, 15));
        songs.add(new Song("News Of The World", "We Are The Champions", "Queen", 3, 11));

        songs.add(new Song("This Is War", "This Is War", "Thirty Seconds To Mars", 4, 47));
        songs.add(new Song("This Is War", "Night Of The Hunter", "Thirty Seconds To Mars", 5, 41));


        songs.add(new Song("The End Is Where We Begin", "Courtesy Call", "Thousand Foot Crutch", 3, 53));
        songs.add(new Song("The End Is Where We Begin", "We Are", "Thousand Foot Crutch", 3, 18));

        return songs;
    }

    //Creating the necessary classes

    public class Album {
        public String artist;
        public String title;
        public ArrayList<Song> albumSongs;
        public int imageId;

        private Album(String artistName, String title, int imageId) {
            this.artist = artistName;
            this.title = title;
            this.albumSongs = makeAlbum();
            this.imageId = imageId;
        }

        private ArrayList<Song> makeAlbum() {
            ArrayList<Song> album = new ArrayList<>();
            for (int i = 0; i < librarySongs.size(); i++) {
                if (librarySongs.get(i).getAlbumTitle().equals(title)) {
                    album.add(librarySongs.get(i));
                }
            }

            return album;
        }

        public String getArtistName() {
            return artist;
        }

        public String getTitle() {
            return title;
        }

        public ArrayList<Song> getAlbumSongs() {
            return albumSongs;
        }

        public int getImageId() {
            return imageId;
        }

        public int getAlbumSongPosition(String songTitle) {
            int position = -1;
            for (int i = 0; i < albumSongs.size(); i++) {
                if (albumSongs.get(i).getSongTitle().equals(songTitle)) {
                    position = i;
                    break;
                }
            }
            return position;

        }
    }

    public class Artist {
        private String artistName;
        private ArrayList<Album> artistAlbums;
        private int imageId;

        private Artist(String artistName, int imageId) {
            this.artistName = artistName;
            this.artistAlbums = makeArtistAlbums(artistName);
            this.imageId = imageId;
        }

        private ArrayList<Album> makeArtistAlbums(String artistName) {
            ArrayList<Album> albums = new ArrayList<>();

            for (int i = 0; i < libraryAlbums.size(); i++) {
                if (libraryAlbums.get(i).getArtistName().equals(artistName)) {
                    albums.add(libraryAlbums.get(i));
                }
            }
            return albums;
        }

        public String getArtistName() {
            return artistName;
        }

        public ArrayList<Album> getArtistAlbums() {
            return artistAlbums;
        }

        public int getImageId() {
            return imageId;
        }
    }

    public class Song {

        private String albumTitle;
        private String songTitle;
        private String artistName;
        private int minutes;
        private int seconds;

        private Song(String album, String songName, String artist, int minutes, int seconds) {
            this.albumTitle = album;
            this.songTitle = songName;
            this.artistName = artist;
            this.minutes = minutes;
            this.seconds = seconds;
        }


        //Some getter functions
        String getAlbumTitle() {
            return albumTitle;
        }

        String getArtistName() {
            return artistName;
        }

        String getSongTitle() {
            return songTitle;
        }

        String getDuration() {
            return String.format("%d:%02d", minutes, seconds);
        }
    }
}

