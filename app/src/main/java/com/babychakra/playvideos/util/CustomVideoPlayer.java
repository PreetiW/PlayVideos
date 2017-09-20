package com.babychakra.playvideos.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;

import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * Created by Game on 18-09-17.
 */


public class CustomVideoPlayer extends TextureView implements TextureView.SurfaceTextureListener
{

    private static final String TAG = "CustomVideoPlayer";
    private Context context;

    private MediaPlayer mediaPlayer;
    private String source;
    private boolean isLooping = false, isPaused = false;

    private Callable<Integer> videoInfo = null;
    private Callable<Integer> showThumbnail = null;


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
                    try {
                        mediaPlayer.start();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    try {
                        Surface surface = new Surface(surfaceTexture);
                        mediaPlayer = new MediaPlayer();

                        if(videoInfo != null) {


                                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                    @Override
                                    public void onPrepared(MediaPlayer mp) {
                                         mp.start();
                                        ((Activity)context).runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    videoInfo.call();

                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }

                                            }
                                        });
                                    }
                                });

                                mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                                    @Override
                                    public boolean onInfo(final MediaPlayer mp, int what, int extra) {
                                        if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                                            ((Activity)context).runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    try {
                                                        videoInfo.call();

                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }

                                                }
                                            });
                                        }
                                        return false;
                                    }
                                });

                            AssetFileDescriptor afd = context.getAssets().openFd("big_buck_bunny.mp4");

                            mediaPlayer.setLooping(isLooping);
                            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                            mediaPlayer.setSurface(surface);
                            mediaPlayer.prepareAsync();
                            if (mediaPlayer != null) {
                                mediaPlayer.start();
                            }
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

        try {
            if(showThumbnail != null) {
                showThumbnail.call();
            }
        }catch (Exception e){
            e.printStackTrace();
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
        try {
            if(showThumbnail != null) {
                showThumbnail.call();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDetachedFromWindow();
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture arg0, int arg1,int arg2) {
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture arg0) {

    }

    public void setShowThumbnail(Callable<Integer> showThumbnail) {
        this.showThumbnail = showThumbnail;
    }

    public void setVideoInfo(Callable<Integer> videoInfo) {
        this.videoInfo = videoInfo;
    }

    public void startVideo()
    {
        Log.d(TAG, "Video Started");
        if(!isPaused){
            setSurfaceTextureListener(this);
            if (this.getSurfaceTexture() != null) {
                if (mediaPlayer != null) {
                    try {
                        mediaPlayer.start();
                        videoInfo.call();
                    } catch (Exception e){
                        e.printStackTrace();
                    }

                } else {
                    try {
                        Surface surface = new Surface(this.getSurfaceTexture());
                        mediaPlayer = new MediaPlayer();

                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                try {
                                    mp.start();

                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });



                            mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                                @Override
                                public boolean onInfo(final MediaPlayer mp, int what, int extra) {
                                    if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                                        ((Activity)context).runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    videoInfo.call();
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }

                                            }
                                        });

                                    }
                                    return false;
                                }
                            });

                        AssetFileDescriptor afd = context.getAssets().openFd("big_buck_bunny.mp4");

                        mediaPlayer.setLooping(isLooping);
                        mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                        mediaPlayer.setSurface(surface);
                        mediaPlayer.prepareAsync();
                        /*if(mediaPlayer != null) {
                            mediaPlayer.start();
                        }*/
                    } catch (IllegalArgumentException | SecurityException | IllegalStateException | IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        }

    }

    public void setSource(String source) {
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

    public void clearAll() {
        if (getSurfaceTexture() != null)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //pre lollipop needs SurfaceTexture it owns before calling onDetachedFromWindow super
                getSurfaceTexture().release();
            }
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
        }
        setSurfaceTextureListener(null);
    }
}
