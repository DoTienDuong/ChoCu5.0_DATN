package com.manager.account.service;

import com.manager.account.business.UserDetailsServiceBusiness;
import com.manager.account.config.JwtTokenUtil;
import com.manager.account.constant.ResponseCode;
import com.manager.account.dto.BaseResponseDTO;
import com.manager.account.dto.ProfileDTO;
import com.manager.account.dto.UpdateUserDeviceDTO;
import com.manager.account.dto.token.TokenDTO;
import com.manager.account.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserDetailsServiceBusiness userDetailsServiceBusiness;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public BaseResponseDTO save(TokenDTO user, UpdateUserDeviceDTO request) {
////        UserDevice userDevice = userDeviceDAO.findByUserId(user.getData().getId()).orElse(null);
////
////        if (userDevice == null) {
////            userDevice = new UserDevice();
////            userDevice.setCreatedBy(user.getData().getLoginId());
////            userDevice.setCreatedDate(new Date());
////        }
////
////        userDevice.setUserId(user.getData().getId());
////        userDevice.setToken(request.getToken());
////        userDevice.setUpdatedBy(user.getData().getLoginId());
////        userDevice.setUpdatedDate(new Date());
//
//
//        userDeviceDAO.save(userDevice);
        return new BaseResponseDTO();
    }

    public BaseResponseDTO updateProfile(String username, ProfileDTO dto) throws UsernameNotFoundException {
        BaseResponseDTO response = new BaseResponseDTO();

        try {
            userDetailsServiceBusiness.updateProfile(username, dto);
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.UPDATE_ERROR, "Upload avatar failed");
        }
        return response;
    }

    public BaseResponseDTO getAvatarUser(String username) throws UsernameNotFoundException {
        BaseResponseDTO response = new BaseResponseDTO();
        try {
            Users userDetails = userDetailsServiceBusiness.getUser(username);
            response.setData(userDetails.getAvatar());
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.UPDATE_ERROR, "Upload avatar failed");
        }
        return response;
    }
}
