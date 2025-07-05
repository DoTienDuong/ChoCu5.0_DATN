package com.manager.account.dao;

import com.manager.account.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryDAO extends JpaRepository<Category, Integer>  {
    List<Category> findByParentIsNull();
}
