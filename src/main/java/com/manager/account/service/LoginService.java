package com.manager.account.service;

import com.manager.account.business.*;
import com.manager.account.config.JwtTokenUtil;
import com.manager.account.constant.ResponseCode;
import com.manager.account.dao.*;
import com.manager.account.dto.*;
import com.manager.account.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class LoginService {
    @Autowired
    UserDetailsServiceBusiness userDetailsServiceBusiness;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    CheckPassword checkPassword;

    @Autowired
    OtpVerificationServiceBusiness otpVerificationServiceBusiness;

    @Autowired
    UserDAO userDAO; // instant variable

    private static final LoginRequestDTO abc = new LoginRequestDTO(); // static variable

    public BaseResponseDTO execute(LoginRequestDTO login) throws UsernameNotFoundException {
        BaseResponseDTO response = new BaseResponseDTO();
        Users userDetails = userDetailsServiceBusiness.getUser(login.getUsername());

        if (userDetails == null) {
            response = new BaseResponseDTO(ResponseCode.USER_INVALID, "User or password invalid");
            return response;
        }

        // Check password
        boolean passwordValid = checkPassword.execute(userDetails.getPassword(), login.getPassword());
        if (!passwordValid) {
            return new BaseResponseDTO(ResponseCode.USER_INVALID, "User or password invalid");
        }

        // Handle account status
//        String accountStatus = userDetails.getAccountStatus();
//        switch (accountStatus) {
//            case "DISABLED":
//                return new BaseResponseDTO(ResponseCode.USER_DISABLED, "Your account is disabled.");
//
//            case "PENDING":
//                // Sinh OTP
//                String otpCode = otpService.generateOTP(userDetails.getUsername());
//
//                // Gửi email
//                emailService.sendOtpEmail(userDetails.getEmail(), otpCode);
//
//                return new BaseResponseDTO(ResponseCode.USER_UNVERIFIED, "Account not verified. OTP sent to your email.");
//
//            case "VERIFIED":
        // Đăng nhập thành công
        Map<String, Object> claims = new HashMap<>();
        claims.put("data", userDetails);
        String token = jwtTokenUtil.generateToken(userDetails.getUsername(), claims);
        response.setData(new LoginResponseDTO(token));
        return response;

//            default:
//                return new BaseResponseDTO(ResponseCode.USER_ERROR, "Unknown account status.");
//    }
    }

    public BaseResponseDTO register(RegisterRequestDTO registerRequest) throws UsernameNotFoundException {
        BaseResponseDTO response = new BaseResponseDTO();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pass = passwordEncoder.encode(registerRequest.getPassword());
        Users result = userDetailsServiceBusiness.register(
                registerRequest.getUsername(),
                pass,
                registerRequest.getEmail(),
                registerRequest.getRole()
        );
        if (result == null) {
            response = new BaseResponseDTO(ResponseCode.USER_INVALID_V2, "Registration failed");
        } else {
            Map<String, Object> claims = new HashMap<>();
            claims.put("data", result);
            String token = jwtTokenUtil.generateToken(result.getUsername(), claims);
            response.setData(new LoginResponseDTO(token));

            response.setMessage("Registered successfully");
        }
        return response;
    }

    public BaseResponseDTO getInfo(String username) throws UsernameNotFoundException {
        BaseResponseDTO response = new BaseResponseDTO();
        Users userDetails = userDetailsServiceBusiness.getUser(username);
        if (null != userDetails) {
            response.setData(userDetails);
        } else {
            response = new BaseResponseDTO(ResponseCode.USER_INVALID, "User is not exists");
        }

        return response;
    }

    public BaseResponseDTO changePassword(ChangePasswordRequestDTO requestDTO) {
        BaseResponseDTO response = new BaseResponseDTO();
        Users userDetails = userDetailsServiceBusiness.getUser(requestDTO.getUsername());

        if (checkPassword.execute(userDetails.getPassword(), requestDTO.getPassword())) {
            if (checkPassword.execute(userDetails.getPassword(), requestDTO.getNewPassword())) {
                response = new BaseResponseDTO(ResponseCode.PASS_USED, "Password has been used");
            } else {
                userDetails.setPassword(checkPassword.passwordEncoder(requestDTO.getNewPassword()));
                userDAO.save(userDetails);
            }
        } else {
            response = new BaseResponseDTO(ResponseCode.USER_INVALID, "User or password invalid");
        }
        return response;
    }

    public BaseResponseDTO resetPassword(ResetPasswordRequestDTO requestDTO) {
        BaseResponseDTO response = new BaseResponseDTO();
        Users userDetails = userDetailsServiceBusiness.getUser(requestDTO.getUsername());
        if (null == userDetails) {
            response = new BaseResponseDTO(ResponseCode.USER_INVALID, "User invalid");
        } else {
            userDetails.setPassword(checkPassword.passwordEncoder(requestDTO.getNewPassword()));
            userDAO.save(userDetails);
        }
        return response;
    }

    public BaseResponseDTO getUserByUsername(String username) {
        BaseResponseDTO response = new BaseResponseDTO();
        Users userDetails = userDetailsServiceBusiness.getUser(username);
        if (null != userDetails) {
            response.setData(userDetails);
        }
        return response;
    }

    public BaseResponseDTO getUserByEmail(String email) {
        BaseResponseDTO response = new BaseResponseDTO();
        Users userDetails = userDetailsServiceBusiness.getUser(email);
        if (null != userDetails) {
            response.setCode("1");
            response.setMessage("Email này đã tồn tại!");
        }
        return response;
    }

    public BaseResponseDTO getUsers() {
        BaseResponseDTO response = new BaseResponseDTO();
        List<Users> users = userDetailsServiceBusiness.getUsers();
        response.setData(users);
        return response;
    }
}
