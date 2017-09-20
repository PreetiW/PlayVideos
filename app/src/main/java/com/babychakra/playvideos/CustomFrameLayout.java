package com.babychakra.playvideos;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by Game on 20-09-17.
 */

public class CustomFrameLayout extends FrameLayout {

    private CustomVideoPlayer customVideoPlayer;
    private ImageView imageView;

    public CustomFrameLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public CustomFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public CustomVideoPlayer getCustomVideoView(){
        return customVideoPlayer;
    }

    public ImageView getImageView(){
        return imageView;
    }

    private void init() {
        this.setTag("CustomVideoPlayer");
        customVideoPlayer = new CustomVideoPlayer(getContext());
        imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.addView(customVideoPlayer);
        this.addView(imageView);

    }
}
