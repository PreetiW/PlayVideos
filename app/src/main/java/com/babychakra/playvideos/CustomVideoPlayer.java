package com.babychakra.playvideos;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;

import java.io.IOException;

/**
 * Created by Game on 18-09-17.
 */


public class CustomVideoPlayer extends TextureView implements TextureView.SurfaceTextureListener
{

    private static final String TAG = "CustomVideoPlayer";
    private Context context;

    private MediaPlayer mediaPlayer;
    private Uri source;
    private boolean isLooping = false, isPaused = false;


    public  CustomVideoPlayer(Context context){
        super(context, null, 0);
        this.context = context;
    }

    public CustomVideoPlayer(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
    }

    public CustomVideoPlayer(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    @Override
    public void onSurfaceTextureAvailable(final SurfaceTexture surfaceTexture, int arg1, int arg2) {

        if(!isPaused){

                if (mediaPlayer != null) {
                    mediaPlayer.start();

                } else {
                    try {
                        Surface surface = new Surface(surfaceTexture);
                        mediaPlayer = new MediaPlayer();


                        mediaPlayer.setLooping(isLooping);
                        mediaPlayer.setDataSource(context, source);
                        mediaPlayer.setSurface(surface);
                        mediaPlayer.prepare();
                        if(mediaPlayer != null) {
                            mediaPlayer.start();
                        }
                    } catch (IllegalArgumentException | SecurityException | IllegalStateException | IOException e) {
                        e.printStackTrace();
                    }

                }


        }
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //pre lollipop needs SurfaceTexture it owns before calling onDetachedFromWindow super
            surface.release();
        }
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        return true;
    }

    @Override
    protected void onDetachedFromWindow() {
        // release resources on detach
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDetachedFromWindow();
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture arg0, int arg1,int arg2) {
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture arg0) {

    }



        public void startVideo()
    {
        Log.d(TAG, "Start Video");
        if(!isPaused){
            setSurfaceTextureListener(this);
            if (this.getSurfaceTexture() != null) {
                if (mediaPlayer != null) {
                    mediaPlayer.start();


                } else {
                    try {
                        Surface surface = new Surface(this.getSurfaceTexture());
                        mediaPlayer = new MediaPlayer();


                        mediaPlayer.setLooping(isLooping);
                        mediaPlayer.setDataSource(context, source);
                        mediaPlayer.setSurface(surface);
                        mediaPlayer.prepare();
                        if(mediaPlayer != null) {
                            mediaPlayer.start();
                        }
                    } catch (IllegalArgumentException | SecurityException | IllegalStateException | IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        }

    }

    public void setSource(Uri source) {
        this.source = source;
    }

    public void setPaused(boolean paused) {
        this.isPaused = paused;
    }

    public boolean getPaused(){
        return isPaused;
    }

    public void setLooping(boolean looping) {
        isLooping = looping;
    }

    public void pauseVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public boolean isPlaying() {
        if (mediaPlayer != null) {
            return mediaPlayer.isPlaying();
        }
        return false;
    }

    public void muteVideo() {
        if (mediaPlayer != null)
            mediaPlayer.setVolume(0f, 0f);
    }

    public void unmuteVideo() {
        if (mediaPlayer != null)
            mediaPlayer.setVolume(1f, 1f);
    }
}
