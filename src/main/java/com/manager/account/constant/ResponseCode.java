package com.manager.account.constant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseCode {
    public static final String USER_INVALID = "SD001";
    public static final String USER_INVALID_V2 = "SD002";
    public static final String USER_DISABLED = "SD003";
    public static final String USER_UNVERIFIED = "SD004";
    public static final String USER_ERROR = "SD005";
    public static final String UNKNOW = "MG999";
    public static final String PASS_USED = "SD002";
    public static final String UPDATE_ERROR = "UP001";
    public static final String VIDEO_GET_ERROR = "VD001";
    public static final String ADD_CATEGORY_ERROR = "CG-01";
    public static final String GET_CATEGORY_ERROR = "CG-02";
    public static final String DELETE_CATEGORY_ERROR = "CG-03";

    String code;
    String message;

    public ResponseCode() {
    }

    public void SUCCESS (){
        this.code = "00";
        this.message = "SUCCESS";
    }
}
