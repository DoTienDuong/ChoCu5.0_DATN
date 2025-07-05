package com.manager.account.business;

import com.manager.account.dao.UserDAO;
import com.manager.account.dto.ProfileDTO;
import com.manager.account.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceBusiness implements UserDetailsService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    CheckPassword checkPassword;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userDAO.getByLoginId(username);
        if (null == users)
            throw new UsernameNotFoundException("User not found with username: " + username);

        return new User(users.getUsername(), users.getPassword(), new ArrayList<>());
    }

    public Users getUser(String username) throws UsernameNotFoundException {
        Users users = userDAO.getByLoginId(username.toLowerCase());
        if (null == users)
            throw new UsernameNotFoundException("User not found with username: " + username);

        return users;
    }

    public List<Users> getUsers() throws UsernameNotFoundException {
        return userDAO.findAll();
    }

    public Users register(
            String username,
            String password,
            String email,
            String role
    ) {
        return userDAO.save(new Users(email, "09xxxxxxxx", username, password, username, null, role, "PENDING"));
    }

    public void updateProfile(String username, ProfileDTO dto) {
        Users users = userDAO.getByLoginId(username);
        if (null == users) return;
        if (dto.getAvatar() != null && !dto.getAvatar().isEmpty()) {
            users.setAvatar(dto.getAvatar());
        }
        if (dto.getBirthday() != null && !dto.getBirthday().isEmpty()) {
            users.setBirthday(dto.getBirthday());
        }
        if (dto.getPhone_number() != null && !dto.getPhone_number().isEmpty()) {
            users.setPhone_number(dto.getPhone_number());
        }
        if (dto.getFull_name() != null && !dto.getFull_name().isEmpty()) {
            users.setFull_name(dto.getFull_name());
        }
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            users.setPassword(checkPassword.passwordEncoder(dto.getPassword()));
        }
        if (dto.getNumber_following() != -1) {
            users.setNumber_following((long) dto.getNumber_following());
        }
        if (dto.getNumber_follower() != -1) {
            users.setNumber_follower((long) dto.getNumber_follower());
        }
        if (dto.getNumber_likes() != -1) {
            users.setNumber_likes((long) dto.getNumber_likes());
        }
        if (dto.getReason() != null) {
            users.setReason(dto.getReason());
        }
        if (dto.getAccount_status() != null) {
            users.setAccountStatus(dto.getAccount_status());
        }
        userDAO.save(users);
    }
}
