package com.babychakra.playvideos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Game on 18-09-17.
 */

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ViewHolder> {

    private ArrayList<Video> videoIdsList;
    Context context;


    public VideosAdapter(ArrayList<Video> videoIds, Context context){
        videoIdsList = videoIds;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View noteView = inflater.inflate(R.layout.video_list_item, parent, false);

        return new ViewHolder(noteView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        playVideo(holder, position);
    }

    private void playVideo(final ViewHolder holder, int position) {

        if(holder.isMuted){
            holder.volumeImage.setImageResource(R.drawable.ic_volume_off_black_24dp);
            holder.muteVideo();
        }else {
            holder.volumeImage.setImageResource(R.drawable.ic_volume_up_black_24dp);
            holder.unmuteVideo();
        }

        if(holder.isPlaying()){
            holder.playbackImage.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp);
        }else {
            holder.playbackImage.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
        }


        holder.volumeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.isMuted){
                    holder.unmuteVideo();
                    holder.volumeImage.setImageResource(R.drawable.ic_volume_up_black_24dp);
                }else {
                    holder.muteVideo();
                    holder.volumeImage.setImageResource(R.drawable.ic_volume_off_black_24dp);
                }
                holder.isMuted = !holder.isMuted;
            }
        });

        holder.playbackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.isPlaying()){
                    holder.pauseVideo();
                    holder.setPaused(true);
                    holder.playbackImage.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
                } else {
                    holder.playVideo();
                    holder.setPaused(false);
                    holder.playbackImage.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp);
                }
            }
        });

        holder.getImageView().setImageResource(R.drawable.video_placeholder);

        Video video = videoIdsList.get(position);
        holder.videoTitle.setText("Video " +video.getIndexPosition());
        holder.setVideoUrl(video.getUrl());
    }

    @Override
    public int getItemCount() {
        return videoIdsList.size();
    }

    public class ViewHolder extends CustomViewHolder {

        private TextView videoTitle;
        private LinearLayout layout;
        private ImageView playbackImage, volumeImage;
        boolean isMuted = true;
        private CustomVideoPlayer customVideoPlayer;

        public ViewHolder(View itemView) {
            super(itemView);
            videoTitle = (TextView) itemView.findViewById(R.id.video_title);
            playbackImage = (ImageView) itemView.findViewById(R.id.playback_image);
            volumeImage = (ImageView) itemView.findViewById(R.id.volume_image);

        }

        @Override
        public void showVideo() {
            super.showVideo();
            playbackImage.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp);
            if(isMuted){
                muteVideo();
                volumeImage.setImageResource(R.drawable.ic_volume_off_black_24dp);
            } else {
                unmuteVideo();
                volumeImage.setImageResource(R.drawable.ic_volume_up_black_24dp);
            }
        }

        @Override
        public void pauseVideo() {
            super.pauseVideo();
            playbackImage.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
        }
    }


    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        if(holder != null){
            holder.getVideoPlayer().clearAll();
            holder.getVideoPlayer().invalidate();
        }
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        if(holder != null){
            holder.getVideoPlayer().clearAll();
            holder.getVideoPlayer().invalidate();
        }
        super.onViewRecycled(holder);
    }
}
