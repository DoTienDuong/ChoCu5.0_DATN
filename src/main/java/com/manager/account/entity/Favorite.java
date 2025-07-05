package com.manager.account.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "favorites")
@IdClass(FavoriteID.class)
public class Favorite {
    @Id
    @Column(name = "video_id")
    String video_id;

    @Id
    @Column(name = "username")
    String username;

    public Favorite() {
    }

    public Favorite(String video_id, String username) {
        this.video_id = video_id;
        this.username = username;
    }

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
