package com.manager.account.business;

import com.manager.account.dao.CategoryDAO;
import com.manager.account.dao.UserDAO;
import com.manager.account.dto.ProfileDTO;
import com.manager.account.entity.Category;
import com.manager.account.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryServiceBusiness {

    @Autowired
    CategoryDAO categoryDAO;

    public Category add(
            String name,
            String description,
            Long parentId
    ) {
        return categoryDAO.save(new Category());
    }
}
