package android.example.musicalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.ArrayList;

public class MusicAdapter extends ArrayAdapter<Music> {

    private static final String LOG_TAG = MusicAdapter.class.getSimpleName();

    public MusicAdapter(Context context, ArrayList<Music> songs) {
        super(context, 0, songs);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_songs_list_item, parent, false);
        }
        // Get the {@link Music} object located at this position in the list
        Music local_music = getItem(position);

        ImageView mImageView = listItemView.findViewById((R.id.song_image));
        mImageView.setImageResource(local_music.getImageId());

        TextView musicTextView = listItemView.findViewById(R.id.song_name);
        musicTextView.setText(local_music.getSongName());

        TextView artistTextView = listItemView.findViewById(R.id.song_artist);
        artistTextView.setText(local_music.getArtistName());

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
