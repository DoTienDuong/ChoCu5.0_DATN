package com.manager.account.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "videos")
public class Video {

    @Id
    @Column(name = "video_id")
    String video_id;

    @Column(name = "username")
    String username;

    @Column(name = "content")
    String content;

    @Column(name = "link_video")
    String link_video;

    @Column(name = "num_likes")
    int num_likes=0;

    @Column(name = "num_comments")
    int num_comments=0;

    @Column(name = "num_views")
    int num_views=0;

    @Column(name = "date_uploaded")
    Long dateUploaded = 0L;

    @Column(name = "age_rating")
    String age_rating;

    public Video() {
    }

    public Video(String video_id, String username, String content, String link_video, int num_likes, int num_comments, int num_views, Long dateUploaded, String age_rating) {
        this.video_id = video_id;
        this.username = username;
        this.content = content;
        this.link_video = link_video;
        this.num_likes = num_likes;
        this.num_comments = num_comments;
        this.num_views = num_views;
        this.dateUploaded = dateUploaded;
        this.age_rating = age_rating;
    }
}
