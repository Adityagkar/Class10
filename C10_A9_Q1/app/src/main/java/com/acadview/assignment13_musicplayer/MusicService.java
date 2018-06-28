package com.acadview.assignment13_musicplayer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.net.URISyntaxException;

/**
 * Created by Aditya on 5/14/2018.
 */

public class MusicService extends Service{
    MediaPlayer mediaPlayer;
    int cur_position;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {


        return null;
    }

    @Override
    public void onCreate() {
        mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.classic);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(intent.getStringExtra("order").equalsIgnoreCase("play")) {
            mediaPlayer.start();
            mediaPlayer.setLooping(true);

        }
        else if(intent.getStringExtra("order").equals("pause")){
            cur_position=mediaPlayer.getCurrentPosition();
            mediaPlayer.pause();
        }
        else if(intent.getStringExtra("order").equalsIgnoreCase("resume")){
            mediaPlayer.seekTo(cur_position);
            mediaPlayer.start();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {

        mediaPlayer.stop();
        super.onDestroy();
    }
}

