package android.example.musicalapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NowPlayingActivity extends AppCompatActivity {

    private ImageView playAndPause,backward,forward;
    private MediaPlayer mediaPlayer;
    private TextView tx1,tx2;

    private double startTime = 0;
    private double finalTime = 0;
    private Handler myHandler = new Handler();
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private SeekBar seekbar;

    public static int oneTimeOnly = 0;
    int currentPlaying;
    Music nowPlayingSong;
    //Resource ID (Song ID)
    int resID ;
    String fname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
             currentPlaying = extras.getInt(MusicActivity.KEY_POSITION);
             nowPlayingSong = MusicActivity.music.get(currentPlaying);


            TextView nameTextView = findViewById(R.id.now_playing_song_name);
            nameTextView.setText(nowPlayingSong.getSongName());

            ImageView imageView = findViewById(R.id.now_playing_song_image);
            imageView.setImageResource(nowPlayingSong.getImageId());

            TextView artistTextView = findViewById(R.id.now_playing_song_artist);
            artistTextView.setText(nowPlayingSong.getArtistName());

            fname = Integer.toString(nowPlayingSong.getSongId());

            resID = getResources().getIdentifier(fname, "raw", getPackageName());
            tx1 = (TextView)findViewById(R.id.start_textView);
            tx2 = (TextView)findViewById(R.id.final_textView);
            playing();
        }
    }

    public void playing() {

        playAndPause = (ImageView)findViewById(R.id.play_pause);
        forward = (ImageView)findViewById(R.id.forward_arrow);
        backward = (ImageView)findViewById(R.id.backward_arrow);

        mediaPlayer = MediaPlayer.create(this, resID);
        seekbar = (SeekBar)findViewById(R.id.seek_bar);
        seekbar.setClickable(false);
        seekbar.setProgress((int) startTime);

        playAndPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer.isPlaying()){
                    Toast.makeText(getApplicationContext(), "Pausing sound",Toast.LENGTH_SHORT).show();
                    mediaPlayer.pause();
                     //myHandler.removeCallbacks(UpdateSongTime);
                    playAndPause.setImageResource(R.drawable.play_arrow);
                    oneTimeOnly=0;
                } else {
                    Toast.makeText(getApplicationContext(), "Playing sound", Toast.LENGTH_SHORT).show();
                    mediaPlayer.start();
                    playAndPause.setImageResource(R.drawable.pause);

                    finalTime = mediaPlayer.getDuration();
                    startTime = mediaPlayer.getCurrentPosition();

                    if (oneTimeOnly == 0) {
                        seekbar.setMax((int) finalTime);
                        oneTimeOnly = 1;
                    }

                     tx1.setText( milliSecondsToTimer((long) startTime));
                     tx2.setText( milliSecondsToTimer((long) finalTime));

                    seekbar.setProgress((int) startTime);
                    myHandler.postDelayed(UpdateSongTime, 100);
                }
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTime;

                if((temp+forwardTime)<=finalTime){
                    startTime = startTime + forwardTime;
                    mediaPlayer.seekTo((int) startTime);
                    Toast.makeText(getApplicationContext(),"You have Jumped forward 5 seconds",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Cannot jump forward 5 seconds",Toast.LENGTH_SHORT).show();
                }
            }
        });

        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTime;

                if((temp-backwardTime)>0){
                    startTime = startTime - backwardTime;
                    mediaPlayer.seekTo((int) startTime);
                    Toast.makeText(getApplicationContext(),"You have Jumped backward 5 seconds",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Cannot jump backward 5 seconds",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public  String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes +":"  + secondsString ;

        // return timer string
        return finalTimerString;
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            myHandler.removeCallbacks(UpdateSongTime);
            int start = mediaPlayer.getCurrentPosition();

            tx1.setText(milliSecondsToTimer((long) start));

            seekbar.setProgress((int)start);
            myHandler.postDelayed(this, 100);
        }
    };

}

