package com.manager.account.entity;

import java.io.Serializable;

public class FavoriteID implements Serializable {
    private String video_id;
    private String username;

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
