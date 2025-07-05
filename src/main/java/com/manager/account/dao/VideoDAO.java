package com.manager.account.dao;

import com.manager.account.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoDAO extends JpaRepository<Video, String> {

    @Query("select v from Video v where v.username = ?1 order by v.dateUploaded ASC")
    List<Video> getVideoByUsername(String username);

    @Query("select v from Video v where v.video_id = ?1")
    Video getVideoById(String video_id);

    @Query("select v from Video v order by v.dateUploaded ASC")
    List<Video> getAllVideo();
}
