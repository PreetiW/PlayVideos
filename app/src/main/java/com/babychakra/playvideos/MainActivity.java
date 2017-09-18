package com.babychakra.playvideos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.video_recylerview)
    RecyclerView videRecyclerview;

    private VideosAdapter videosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        ButterKnife.bind(this);
        ArrayList<String> videos = new ArrayList<>();
        videos.add("PbIjuqd4ENY");
        videos.add("PbIjuqd4ENY");
        videos.add("PbIjuqd4ENY");
        videos.add("PbIjuqd4ENY");

        videosAdapter = new VideosAdapter(videos);
        videRecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        videRecyclerview.setAdapter(videosAdapter);

    }
}
