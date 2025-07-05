package com.manager.account.dao;

import com.manager.account.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteDAO extends JpaRepository<Favorite, Long> {

    @Query("select f from Favorite f where f.video_id = ?1 AND f.username = ?2")
    Favorite checkFavorite(String videoId, String username);

    @Query("select f from Favorite f where f.video_id = ?1")
    List<Favorite> getTotalFavoriteVideo(String videoId);
}
