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

public class AlbumAdapter extends ArrayAdapter<Library.Album> {

    View.OnClickListener albumListener;
    View.OnClickListener albumPlayListener;

    public AlbumAdapter(Activity context, ArrayList<Library.Album> albums, View.OnClickListener albumListener, View.OnClickListener albumPlayListener) {
        super(context, 0, albums);
        this.albumListener = albumListener;
        this.albumPlayListener = albumPlayListener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        AlbumAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.album_list_item, parent, false);
            viewHolder = new AlbumAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (AlbumAdapter.ViewHolder) convertView.getTag();
        }

        Library.Album albumForDisplay = getItem(position);
        viewHolder.albumTitle.setText(albumForDisplay.getTitle());
        viewHolder.artistName.setText(albumForDisplay.getArtistName());
        viewHolder.imageView.setImageResource(albumForDisplay.getImageId());
        viewHolder.imageView.setOnClickListener(albumListener);
        viewHolder.imageView.setTag(albumForDisplay);
        viewHolder.playView.setOnClickListener(albumPlayListener);
        viewHolder.playView.setTag(albumForDisplay);

        return convertView;
    }

    public class ViewHolder {
        final TextView artistName;
        final TextView albumTitle;
        final ImageView imageView;
        final ImageView playView;

        ViewHolder(View view) {
            this.artistName = view.findViewById(R.id.artist_name);
            this.albumTitle = view.findViewById(R.id.album_title);
            this.imageView = view.findViewById(R.id.album_image);
            this.playView = view.findViewById(R.id.play_button);
        }
    }
}
