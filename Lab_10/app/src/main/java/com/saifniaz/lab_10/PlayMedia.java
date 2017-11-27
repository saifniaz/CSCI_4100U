package com.saifniaz.lab_10;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.VideoView;

public class PlayMedia extends AppCompatActivity {

    private SurfaceView videoSurface;
    private SurfaceHolder surfaceHolder;
    private TextView text;
    private String choice;
    private Button play, pause;
    private Resources res;
    private Spinner spinner;

    private MediaPlayer player;
    AssetManager manager;
    AssetFileDescriptor musicfd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_media);

        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this,
                R.array.video_list,
                android.R.layout.simple_spinner_item
        );

        spinner.setAdapter(adapter);;



        //System.out.println(choice + " THs is the choice");

        //videoSurface = (SurfaceView) findViewById(R.id.surfaceView);
        //surfaceHolder = videoSurface.getHolder();

        text = (TextView) findViewById(R.id.text);

        res = getResources();

        //player = new MediaPlayer();


        play = (Button)findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choice = spinner.getSelectedItem().toString();
                if(player == null){
                    player = new MediaPlayer();
                }else{
                    player.stop();
                    player.reset();
                }
                start();
            }
        });

        pause = (Button) findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.pause();
            }
        });
    }

    public void start(){
        Log.i("Choice", choice);
        if(choice.equals("hero.mp3")){
            musicfd = res.openRawResourceFd(R.raw.hero);
        }else if(choice.equals("summer.mp3")){
            musicfd = res.openRawResourceFd(R.raw.summer);
        }

        try{

            player.setDataSource(musicfd.getFileDescriptor(),
                    musicfd.getStartOffset(), musicfd.getLength());
            player.prepare();
            player.setLooping(true);

        }catch (Exception e){
            e.printStackTrace();
        }
        player.start();
        text.setText(choice);
    }
}
