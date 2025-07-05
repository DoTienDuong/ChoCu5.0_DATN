package com.manager.account.business;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CheckPassword {
    public boolean execute(String dbPass, String reqPass) {
//        if(dbPass.equals(reqPass))
//            return true;
//        else return false;
        System.out.println("GT56 : " + passwordEncoder("123456"));;
        return doPasswordsMatch(reqPass, dbPass);
    }

    public String passwordEncoder(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    private boolean doPasswordsMatch(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
