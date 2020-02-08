package android.example.musicalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MusicActivity extends AppCompatActivity  {

    // Each music contains Song ID, Song Image, Song Name, Artist Name,
    public static ArrayList<Music> music;
    //Key word when I pass position value
    public static final String KEY_POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        music = new ArrayList<Music>();

        music.add(new Music(R.raw.alarm01,R.drawable.alarm, "Alarm song", "Windows Artist"));
        music.add(new Music(R.raw.alarm02,R.drawable.alarm, "Ring-ring song", "Windows Guy"));
        music.add(new Music(R.raw.alarm03,R.drawable.alarm, "Beep-beep song", "Windows Artist"));
        music.add(new Music(R.raw.alarm04,R.drawable.alarm, "Dong-dong song", "Windows Who"));
        music.add(new Music(R.raw.alarm05,R.drawable.alarm, "Rug-rug song", "Windows Artist"));
        music.add(new Music(R.raw.alarm06,R.drawable.alarm, "Teek-teek song", "Windows Group"));
        music.add(new Music(R.raw.alarm07,R.drawable.alarm, "No-no song", "Windows Noise"));
        music.add(new Music(R.raw.alarm08,R.drawable.alarm, "Flash-flash song", "Windows Light"));
        music.add(new Music(R.raw.alarm09,R.drawable.alarm, "Ta-Tara song", "Windows Birds"));
        music.add(new Music(R.raw.alarm10,R.drawable.alarm, "Gentle song", "Windows Artist"));

        MusicAdapter adapter = new MusicAdapter(this, music);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent songIntent = new Intent(MusicActivity.this, NowPlayingActivity.class);

                //Send the position to other Activity. ( Position chosen by user. Position = Music arraylist position)
                songIntent.putExtra(MusicActivity.KEY_POSITION,position);
                startActivity(songIntent);

                //Testing how to send current song name and artist
                //Music currentSong = (Music) parent.getItemAtPosition(position);
                //String songNameForPlaying = currentSong.getSongName();
                //String artistNameForPlaying = currentSong.getArtistName();
                //songIntent.putExtra("songname", songNameForPlaying);
                // songIntent.putExtra("artist", artistNameForPlaying);
                // startActivity(songIntent);
            }
        });
    }
}