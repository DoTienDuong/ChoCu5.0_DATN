package com.manager.account.business;

import com.manager.account.dao.OtpDAO;
import com.manager.account.entity.OtpVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class OtpVerificationServiceBusiness {

    @Autowired
    private OtpDAO otpRepo;

    public String generateAndSaveOTP(String username) {
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        OtpVerification otpEntity = new OtpVerification();
        otpEntity.setUsername(username);
        otpEntity.setOtpCode(otp);
        otpEntity.setExpireAt(LocalDateTime.now().plusMinutes(5));

        otpRepo.save(otpEntity);
        return otp;
    }

    public boolean verifyOtp(String username, String otp) {
        Optional<OtpVerification> otpOpt = otpRepo.findTopByUsernameOrderByExpireAtDesc(username);

        if (otpOpt.isEmpty()) return false;

        OtpVerification otpEntity = otpOpt.get();
        if (otpEntity.isUsed()) return false;
        if (otpEntity.getExpireAt().isBefore(LocalDateTime.now())) return false;
        if (!otpEntity.getOtpCode().equals(otp)) return false;

        otpEntity.setUsed(true);
        otpRepo.save(otpEntity);
        return true;
    }

    public boolean canResendOtp(String username) {
        Optional<OtpVerification> otpOpt = otpRepo.findTopByUsernameOrderByExpireAtDesc(username);
        return otpOpt.isEmpty() || otpOpt.get().getExpireAt().isBefore(LocalDateTime.now().minusMinutes(1));
    }
}
