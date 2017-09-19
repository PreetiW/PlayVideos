package com.babychakra.playvideos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Game on 19-09-17.
 */

public class CustomRecyclerView extends RecyclerView {

    boolean initialize = false;
    private boolean autoPlay = true;

    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
        if(!initialize){
            try {
                playVisibleVideos(SCROLL_STATE_IDLE);
                initialize = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        addCustomOnScrollListner();
    }

    private void addCustomOnScrollListner() {
        this.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                playVisibleVideos(newState);
            }
        });
    }

    public void playVisibleVideos(int newState) {

        HandlerThread handlerThread = new HandlerThread("PlayingVideos");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        Handler handler = new Handler(looper);
        List<Runnable> runnables = new ArrayList<>();

        if(newState == SCROLL_STATE_IDLE) {

            int firstVisiblePosition = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
            int lastVisiblePosition = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();

            if (firstVisiblePosition >= 0) {

                Rect rect_parent = new Rect();
                getGlobalVisibleRect(rect_parent);
                if(autoPlay) {
                    for (int i = firstVisiblePosition; i <= lastVisiblePosition; i++) {
                        final RecyclerView.ViewHolder holder = findViewHolderForAdapterPosition(i);
                        try {
                            CustomViewHolder customViewHolder = (CustomViewHolder) holder;

                            if (i >= 0 && customViewHolder != null ) {

                                int[] location = new int[2];
                                customViewHolder.getVideoPlayer().getLocationOnScreen(location);
                                Rect rect_child = new Rect(location[0], location[1], location[0]
                                        + customViewHolder.getVideoPlayer().getWidth(), location[1] + customViewHolder.getVideoPlayer().getHeight());

                                if (rect_parent.contains(rect_child)) {

                                    ((CustomViewHolder) holder).initVideoView(customViewHolder.getVideoUrl());

                                    Runnable myRunnable = new Runnable() {
                                        @Override
                                        public void run() {
                                            if (!((CustomViewHolder) holder).isPaused())
                                                ((CustomViewHolder) holder).playVideo();
                                        }
                                    };
                                    handler.post(myRunnable);
                                    runnables.add(myRunnable);
                                } else {
                                    ((CustomViewHolder) holder).pauseVideo();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }

            }


        }else if (runnables.size() > 0) {
            for (Runnable t : runnables) {
                handler.removeCallbacksAndMessages(t);
            }
            runnables.clear();
            handlerThread.quit();
        }

    }

    public void stopVideos() {
        for (int i = 0; i < getChildCount(); i++) {
            if (findViewHolderForAdapterPosition(i) instanceof CustomViewHolder) {
                final CustomViewHolder customViewHolder = (CustomViewHolder) findViewHolderForAdapterPosition(i);
                if (customViewHolder != null && customViewHolder.getVideoUrl() != null &&  !customViewHolder.getVideoUrl().isEmpty() ) {
                    customViewHolder.pauseVideo();
                }
            }
        }
    }
}
