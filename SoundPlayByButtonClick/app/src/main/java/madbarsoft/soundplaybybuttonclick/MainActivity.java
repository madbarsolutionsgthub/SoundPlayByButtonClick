package madbarsoft.soundplaybybuttonclick;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button btnPlay, btnPush, btnResume, btnStop;

    /**
     * Variables
     */
    MediaPlayer mplayer        = null;
    int currentPosition = 6660;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPlay = (Button)findViewById(R.id.btnPlay);
        btnPush = (Button)findViewById(R.id.btnPush);
        btnResume = (Button)findViewById(R.id.btnResume);
        btnStop = (Button)findViewById(R.id.btnStop);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                managerOfSound("play");
                Toast.makeText(getApplicationContext(), "Sound Play....", Toast.LENGTH_SHORT).show();
            }
        });
        btnPush.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currentPosition = mplayer.getCurrentPosition();
                Toast.makeText(getApplicationContext(), "Sound Push"+mplayer.getCurrentPosition(), Toast.LENGTH_SHORT).show();
                managerOfSound("push");
            }
        });
        btnResume.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                managerOfSound("resume");
                Toast.makeText(getApplicationContext(), "Sound Resume From: "+currentPosition, Toast.LENGTH_SHORT).show();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                managerOfSound("stop");
                Toast.makeText(getApplicationContext(), "Sound Stop", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * Manager of Sounds
     */

    protected void managerOfSound(String theText) {
        if (mplayer != null) {
            mplayer.reset();
            mplayer.release();
        }
        mplayer = MediaPlayer.create(this, R.raw.mongol_dip_song);
        if (theText.equals("play")){
            currentPosition = 0;
            mplayer.start();
        }else if(theText.equals("push")){
            mplayer.pause();
        }else if(theText.equals("resume")){
            mplayer.seekTo(currentPosition);
            mplayer.start();
        }else{
            currentPosition = 0;
            mplayer.stop();
        }
    }

}
