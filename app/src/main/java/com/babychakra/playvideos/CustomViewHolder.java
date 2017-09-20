package com.babychakra.playvideos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.concurrent.Callable;

/**
 * Created by Game on 19-09-17.
 */

public class CustomViewHolder extends RecyclerView.ViewHolder {

    private CustomFrameLayout frameLayout;
    private String videoUrl;
    private boolean isPaused = false;

    public CustomViewHolder(View itemView) {
        super(itemView);
        frameLayout = (CustomFrameLayout) itemView.findViewWithTag("CustomVideoPlayer");
    }

    public void playVideo() {
        this.frameLayout.getCustomVideoView().setPaused(false);
        this.frameLayout.getCustomVideoView().startVideo();

    }

    public void initVideoView(String url){
        this.frameLayout.getCustomVideoView().setVisibility(View.VISIBLE);
       // Uri uri = Uri.parse(url);
        this.frameLayout.getCustomVideoView().setSource(url);
        this.frameLayout.getCustomVideoView().setLooping(true);
        this.frameLayout.getCustomVideoView().setVideoInfo(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                showVideo();
                return null;
            }
        });
        this.frameLayout.getCustomVideoView().setShowThumbnail(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                showThumbnail();
                return null;
            }
        });
    }

    public void showVideo() {
        this.frameLayout.getImageView().setVisibility(View.GONE);
    }

    public void showThumbnail() {
        this.frameLayout.getImageView().setVisibility(View.VISIBLE);

    }


    public void pauseVideo(){
        this.frameLayout.getCustomVideoView().pauseVideo();
        this.frameLayout.getCustomVideoView().setPaused(true);
    }


    public CustomVideoPlayer getVideoPlayer(){
        return this.frameLayout.getCustomVideoView();
    }

    public ImageView getImageView(){
        return this.frameLayout.getImageView();
    }

    public boolean isPlaying() {
        return this.frameLayout.getCustomVideoView().isPlaying();
    }

    public boolean isPaused() {
        return  isPaused;
    }

    public void setPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

    public void muteVideo() {
        this.frameLayout.getCustomVideoView().muteVideo();
    }

    public void unmuteVideo() {
        this.frameLayout.getCustomVideoView().unmuteVideo();
    }

    public void setVideoUrl(String videoUrl){
        this.frameLayout.getImageView().setVisibility(View.VISIBLE);
        this.frameLayout.getCustomVideoView().setVisibility(View.GONE);
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl + "";
    }


}
