package com.manager.account.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @Column(name = "comment_id")
    String comment_id;
    @Column(name = "video_id")
    String video_id;
    @Column(name = "username")
    String username;
    @Column(name = "content")
    String content;
    @Column(name = "date_comment")
    Long date_comment = 0L;

    public Comment() {
    }

    public Comment(String comment_id, String video_id, String username, String content, Long date_comment) {
        this.comment_id = comment_id;
        this.video_id = video_id;
        this.username = username;
        this.content = content;
        this.date_comment = date_comment;
    }
}
