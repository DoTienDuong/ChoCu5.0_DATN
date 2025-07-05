package com.manager.account.dao;

import com.manager.account.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDAO extends JpaRepository<Users, Long> {
    @Query("select u from Users u where (lower(u.email) = ?1 or lower(u.username) =?1)")
    Users getByLoginId(String loginId);
}
