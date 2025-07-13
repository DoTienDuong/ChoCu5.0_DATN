package com.manager.account.dao;

import com.manager.account.entity.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface SlideDAO extends JpaRepository<Slide, Integer> {

    @Query("SELECT MAX(s.createdAt) FROM Slide s")
    LocalDateTime findLatestCreatedAt();
}
