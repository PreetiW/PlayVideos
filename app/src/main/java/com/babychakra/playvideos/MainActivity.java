package com.babychakra.playvideos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.video_recylerview)
    CustomRecyclerView videRecyclerview;

    private VideosAdapter videosAdapter;
    private ArrayList<Video> videoArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        ButterKnife.bind(this);

        videoArrayList = new ArrayList<>();
        Video video1 = new Video("0", "1", "https://www.quirksmode.org/html5/videos/big_buck_bunny.mp4");
        videoArrayList.add(video1);
        Video video2 = new Video("1", "2", "http://techslides.com/demos/sample-videos/small.mp4");
        videoArrayList.add(video2);
        Video video3 = new Video("2", "3", "http://sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4");
        videoArrayList.add(video3);
        Video video4 = new Video("3", "4", "http://dev.exiv2.org/attachments/341/video-2012-07-05-02-29-27.mp4");
        videoArrayList.add(video4);
        Video video5 = new Video("4", "5", "http://techslides.com/demos/sample-videos/small.mp4");
        videoArrayList.add(video5);
        Video video6 = new Video("5", "6", "http://www.quirksmode.org/html5/videos/big_buck_bunny.mp4");
        videoArrayList.add(video6);
        Video video7 = new Video("6", "7", "http://sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4");
        videoArrayList.add(video7);

        videosAdapter = new VideosAdapter(videoArrayList, this);
        videRecyclerview.setAutoPlay(true);
        videRecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        videRecyclerview.setAdapter(videosAdapter);


    }

    @Override
    protected void onStop() {
        super.onStop();
        videRecyclerview.stopVideos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videRecyclerview.playVisibleVideos(0);
    }
}
