package com.example.assignment3;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.MediaController;

import java.io.IOException;

public class PlayVideo extends AppCompatActivity
        implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener,
        MediaController.MediaPlayerControl {
    private SurfaceView surfaceView;
    private MediaPlayer mediaPlayer;
    private SurfaceHolder surfaceHolder;

    private MediaController mediaController;
    private Handler handler = new Handler();
    private String videoSrc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_video);

        Intent intent = getIntent();
        videoSrc = intent.getStringExtra("url");

        surfaceView = (SurfaceView)findViewById(R.id.surfaceview);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(PlayVideo.this);

        surfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(mediaController != null){
                    mediaController.show();
                }
                return false;
            }
        });


    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try{
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDisplay(surfaceHolder);
            mediaPlayer.setDataSource(videoSrc);
            mediaPlayer.setOnPreparedListener(PlayVideo.this);
            mediaPlayer.setOnErrorListener(PlayVideo.this);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepare();
            mediaController = new MediaController(this);
        }   catch (IllegalArgumentException e){
                e.printStackTrace();
        }   catch (SecurityException e){
                e.printStackTrace();
        }   catch (IllegalStateException e){
                e.printStackTrace();
        }   catch (IOException e){
                e.printStackTrace();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }



    @Override
    public boolean onError(MediaPlayer mp, int what, int extra){
        Log.e("PlayVideo","what="+what+" extra="+extra);
        return false;
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra){
        Log.e("PlayVideo","what="+what+" extra="+extra);
        return  false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        setVideoSize();
        mediaPlayer.start();
        mediaController.setMediaPlayer(this);
        mediaController.setAnchorView(surfaceView);
        handler.post(new Runnable() {

            public void run() {
                mediaController.setEnabled(true);
                mediaController.show();
            }
        });
    }
    //Override app state
    @Override
    public void onDestroy(){
        super.onDestroy();
        releaseMediaPlayer();
    }
    @Override
    public void onPause(){
        super.onPause();
        releaseMediaPlayer();
    }
    @Override
    public void onResume(){
        super.onResume();
        releaseMediaPlayer();
    }

    //Override mediaController
    @Override
    public void start() {
        mediaPlayer.start();
    }
    @Override
    public void pause() {
        mediaPlayer.pause();
    }
    @Override
    public int getDuration() {
        return mediaPlayer.getDuration();
    }
    @Override
    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }
    @Override
    public void seekTo(int pos) {
        mediaPlayer.seekTo(pos);
    }
    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }
    @Override
    public int getBufferPercentage() {
        return 0;
    }
    @Override
    public boolean canPause() {
        return true;
    }
    @Override
    public boolean canSeekBackward() {
        return true;
    }
    @Override
    public boolean canSeekForward() {
        return true;
    }
    @Override
    public int getAudioSessionId() {
        return mediaPlayer.getAudioSessionId();
    }


    private void releaseMediaPlayer() {
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer = null ;
        }
    }

    private void setVideoSize() {

        // // Get the dimensions of the video
        int videoWidth = mediaPlayer.getVideoWidth();
        int videoHeight = mediaPlayer.getVideoHeight();
        float videoProportion = (float) videoWidth / (float) videoHeight;

        // Get the width of the screen
        int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        int screenHeight = getWindowManager().getDefaultDisplay().getHeight();
        float screenProportion = (float) screenWidth / (float) screenHeight;

        // Get the SurfaceView layout parameters
        android.view.ViewGroup.LayoutParams lp = surfaceView.getLayoutParams();
        if (videoProportion > screenProportion) {
            lp.width = screenWidth;
            lp.height = (int) ((float) screenWidth / videoProportion);
        } else {
            lp.width = (int) (videoProportion * (float) screenHeight);
            lp.height = screenHeight;
        }
        // Commit the layout parameters
        surfaceView.setLayoutParams(lp);
    }
}
