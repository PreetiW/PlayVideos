package com.babychakra.playvideos;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Game on 19-09-17.
 */

public class CustomViewHolder extends RecyclerView.ViewHolder {

    private CustomVideoPlayer videoPlayer;
    private String videoUrl;
    private boolean isPaused = false;

    public CustomViewHolder(View itemView) {
        super(itemView);
        //videoPlayer = (CustomVideoPlayer) itemView.findViewWithTag("customVideoPlayer");
    }

    public void playVideo() {
        this.videoPlayer.setPaused(false);
        this.videoPlayer.startVideo();

    }

    public void initVideoView(String url){
        Uri uri = Uri.parse(url);
        this.videoPlayer.setSource(uri);
        this.videoPlayer.setLooping(true);
        this.videoPlayer.setSource(uri);
    }

    public void pauseVideo(){
        this.videoPlayer.pauseVideo();
        this.videoPlayer.setPaused(true);
    }

    public void setVideoPlayer(CustomVideoPlayer videoPlayer) {
        this.videoPlayer = videoPlayer;
    }

    public CustomVideoPlayer getVideoPlayer(){
        return this.videoPlayer;
    }

    public boolean isPlaying() {
        return this.videoPlayer.isPlaying();
    }

    public boolean isPaused() {
        return  isPaused;
    }

    public void setPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

    public void muteVideo() {
        this.videoPlayer.muteVideo();
    }

    public void unmuteVideo() {
        this.videoPlayer.unmuteVideo();
    }

    public void setVideoUrl(String videoUrl){
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl + "";
    }
}
