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

//A custom ArrayAdapter to work with the Artist class
public class ArtistAdapter extends ArrayAdapter<Library.Artist> {

    private View.OnClickListener artistListener;

    public ArtistAdapter(Activity context, ArrayList<Library.Artist> artists, View.OnClickListener artistListener) {
        super(context, 0, artists);
        this.artistListener = artistListener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.artist_grid_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Library.Artist artistForDisplay = getItem(position);
        viewHolder.artistName.setText(artistForDisplay.getArtistName());
        viewHolder.imageView.setImageResource(artistForDisplay.getImageId());
        viewHolder.imageView.setOnClickListener(artistListener);
        viewHolder.imageView.setTag(artistForDisplay);

        return convertView;
    }

    public class ViewHolder {
        final TextView artistName;
        final ImageView imageView;

        ViewHolder(View view) {
            this.artistName = view.findViewById(R.id.artist_name_text_view);
            this.imageView = view.findViewById(R.id.artist_image_view);
        }
    }
}
