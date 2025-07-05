package com.manager.account.controller;

import com.manager.account.business.OtpVerificationServiceBusiness;
import com.manager.account.business.UserDetailsServiceBusiness;
import com.manager.account.constant.ResponseCode;
import com.manager.account.dto.BaseResponseDTO;
import com.manager.account.entity.Users;
import com.manager.account.service.EmailService;
import com.manager.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class OtpController {

    @Autowired
    UserDetailsServiceBusiness userDetailsServiceBusiness;

    @Autowired
    OtpVerificationServiceBusiness otpService;

    @Autowired
    EmailService emailService;

    @PostMapping("/auth/resend-otp")
    public ResponseEntity<BaseResponseDTO> resendOtp(@RequestParam String username) {
        Users user = userDetailsServiceBusiness.getUser(username);
        if (user == null || !"PENDING".equals(user.getAccountStatus())) {
            return ResponseEntity.badRequest()
                    .body(new BaseResponseDTO(ResponseCode.USER_INVALID, "Invalid or verified user"));
        }

        String otp = otpService.generateAndSaveOTP(username);
        emailService.sendOtpEmail(user.getEmail(), otp);
        return ResponseEntity.ok(new BaseResponseDTO("OTP resent to email"));
    }

}
