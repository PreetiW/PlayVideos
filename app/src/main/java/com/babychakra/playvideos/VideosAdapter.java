package com.babychakra.playvideos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Game on 18-09-17.
 */

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ViewHolder> {

    private ArrayList<Video> videoIdsList;
    Context context;
    public VideoPlayerController videoPlayerController;


    public VideosAdapter(ArrayList<Video> videoIds, Context context){
        videoIdsList = videoIds;
        this.context = context;
        videoPlayerController = new VideoPlayerController(context);
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

    private void playVideo(ViewHolder holder, int position) {

        Video video = videoIdsList.get(position);
        holder.videoTitle.setText("Video " + video.getId());

        final VideoPlayer videoPlayer = new VideoPlayer(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        videoPlayer.setLayoutParams(params);

        holder.layout.addView(videoPlayer);
        videoPlayerController.loadVideo(video, videoPlayer, holder.progressBar);
        videoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                videoPlayer.changePlayState();
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoIdsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView videoTitle;
        private LinearLayout layout;
        private ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            videoTitle = (TextView) itemView.findViewById(R.id.video_title);
            layout = (LinearLayout) itemView.findViewById(R.id.layout);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar);
        }

    }
}
