package com.babychakra.playvideos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.babychakra.playvideos.util.CustomRecyclerView;
import com.babychakra.playvideos.util.Util;
import com.babychakra.playvideos.util.Video;
import com.babychakra.playvideos.util.VideosAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.video_recylerview)
    CustomRecyclerView videRecyclerview;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ArrayList<Video> videoArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toggle_autoplay:
                {
                    Util.saveAutoPlayVideo(this);
                    this.recreate();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void init() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        videoArrayList = new ArrayList<>();
        setDataSource();
        VideosAdapter videosAdapter;
        videosAdapter = new VideosAdapter(videoArrayList, this);
        videRecyclerview.setAutoPlay(Util.getAutoPlayVideo(this));
        videRecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        videRecyclerview.setAdapter(videosAdapter);


    }

    private void setDataSource() {
        Video video1 = new Video("0", "1", "https://www.quirksmode.org/html5/videos/big_buck_bunny.mp4");
        videoArrayList.add(video1);
        Video video2 = new Video("1", "2", "http://techslides.com/demos/sample-videos/small.mp4");
        videoArrayList.add(video2);
        Video video3 = new Video("2", "3", "http://www.ebookfrenzy.com/android_book/movie.mp4");
        videoArrayList.add(video3);
        Video video4 = new Video("3", "4", "http://dev.exiv2.org/attachments/341/video-2012-07-05-02-29-27.mp4");
        videoArrayList.add(video4);
        Video video5 = new Video("4", "5", "http://techslides.com/demos/sample-videos/small.mp4");
        videoArrayList.add(video5);
        Video video6 = new Video("5", "6", "http://www.quirksmode.org/html5/videos/big_buck_bunny.mp4");
        videoArrayList.add(video6);
        Video video7 = new Video("6", "7", "http://sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4");
        videoArrayList.add(video7);
        Video video8 = new Video("7", "8", "http://sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4");
        videoArrayList.add(video8);
        Video video9 = new Video("8", "9", "http://sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4");
        videoArrayList.add(video9);
        Video video10 = new Video("9", "10", "http://sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4");
        videoArrayList.add(video10);
        Video video11 = new Video("10", "11", "http://sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4");
        videoArrayList.add(video11);
        Video video12 = new Video("11", "12", "http://sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4");
        videoArrayList.add(video12);
        Video video13 = new Video("12", "13", "http://sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4");
        videoArrayList.add(video13);
        Video video14 = new Video("13", "14", "http://techslides.com/demos/sample-videos/small.mp4");
        videoArrayList.add(video14);
        Video video15 = new Video("14", "15", "http://techslides.com/demos/sample-videos/small.mp4");
        videoArrayList.add(video15);
        Video video16 = new Video("15", "16", "http://techslides.com/demos/sample-videos/small.mp4");
        videoArrayList.add(video16);
        Video video17 = new Video("16", "17", "http://techslides.com/demos/sample-videos/small.mp4");
        videoArrayList.add(video17);
        Video video18 = new Video("17", "18", "http://techslides.com/demos/sample-videos/small.mp4");
        videoArrayList.add(video18);
        Video video19 = new Video("18", "19", "http://techslides.com/demos/sample-videos/small.mp4");
        videoArrayList.add(video19);
        Video video20 = new Video("19", "20", "http://techslides.com/demos/sample-videos/small.mp4");
        videoArrayList.add(video20);

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
