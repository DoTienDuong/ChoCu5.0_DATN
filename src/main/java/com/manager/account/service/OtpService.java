package com.manager.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {

//    private final Map<String, String> otpStorage = new HashMap<>();

//    @Autowired
//    private OtpVerificationRepository otpRepo;
//
//    public String generateOTP(String username) {
//        String otp = String.valueOf(new Random().nextInt(900000) + 100000); // 6 số
//        otpStorage.put(username, otp);
//
//        // Có thể thêm thời gian hết hạn tại đây
//        return otp;
//    }

//    public boolean verifyOTP(String username, String otp) {
//        return otp.equals(otpStorage.get(username));
//    }
}
