package com.manager.account.dao;

import com.manager.account.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentDAO extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.video_id = ?1")
    List<Comment> getCommentsByVideo(String videoId);
}
