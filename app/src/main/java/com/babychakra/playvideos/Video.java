package com.babychakra.playvideos;

/**
 * Created by Game on 18-09-17.
 */

public class Video {

    private String indexPosition;
    private String id;
    private String url;

    public Video(String id, String indexPosition, String url) {
        this.id = id;
        this.indexPosition = indexPosition;
        this.url = url;

    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getIndexPosition() {
        return indexPosition;
    }
}
