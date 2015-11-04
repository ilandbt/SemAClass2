package com.best.class2;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SecondActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    //views
    private TextView display;
    private Button show, play;

    //music
    private MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //init views
        display = (TextView) findViewById(R.id.display);
        show = (Button) findViewById(R.id.show);
        play = (Button) findViewById(R.id.play);

        //set event listeners
        show.setOnClickListener(readFileClickListener);
        play.setOnClickListener(playMusicClickListener);
    }


    //event listeners
    private View.OnClickListener readFileClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //init stream
            InputStream input = getResources().openRawResource(R.raw.soundtrack_list);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String text = "";
            String line = "";


            //remove old text
            display.setText("");

            //read text from file
            try {
                while ((line = reader.readLine()) != null) {
                    text  += "\n";
                    text += line;

                }

                //close inout streams
                input.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //display text
            display.setText(text);
        }
    };


    private View.OnClickListener playMusicClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //start the music
            mPlayer = MediaPlayer.create(v.getContext(), R.raw.soundtrack);
            mPlayer.start();
        }
    };

    @Override
    protected void onStop() {
        super.onStop();

        //stop the music
        if (mPlayer != null) {
            mPlayer.stop();
        }
    }
}
