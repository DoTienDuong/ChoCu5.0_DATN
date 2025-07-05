package com.manager.account.dao;

import com.manager.account.entity.OtpVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpDAO extends JpaRepository<OtpVerification, Long> {

    Optional<OtpVerification> findTopByUsernameOrderByExpireAtDesc(String username);
}
