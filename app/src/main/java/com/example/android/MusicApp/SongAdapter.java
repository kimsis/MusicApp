package com.example.android.MusicApp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//A custom ArrayAdapter to work with the Song class

public class SongAdapter extends ArrayAdapter<Library.Song> {

    private View.OnClickListener songListener;

    public SongAdapter(Activity context, ArrayList<Library.Song> songs, View.OnClickListener songListener) {
        super(context, 0, songs);
        this.songListener = songListener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.song_list_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Library.Song songForDisplay = getItem(position);
        viewHolder.songTitle.setText(String.format("%s - %s", songForDisplay.getSongTitle(), songForDisplay.getArtistName()));
        viewHolder.albumName.setText(songForDisplay.getAlbumTitle());
        viewHolder.duration.setText(songForDisplay.getDuration());
        viewHolder.playButton.setOnClickListener(songListener);
        viewHolder.playButton.setTag(songForDisplay);

        return convertView;
    }

    public class ViewHolder {
        final TextView songTitle;
        final TextView albumName;
        final TextView duration;
        final ImageView playButton;

        ViewHolder(View view) {
            this.songTitle = view.findViewById(R.id.song_title);
            this.albumName = view.findViewById(R.id.album_name);
            this.duration = view.findViewById(R.id.duration);
            this.playButton = view.findViewById(R.id.song_play_button);
        }
    }
}
