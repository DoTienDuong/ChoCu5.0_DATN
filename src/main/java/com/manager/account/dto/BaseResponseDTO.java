package com.manager.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BaseResponseDTO {
    String code;
    String message;
    Object data;
    public BaseResponseDTO() {
        this.code = "0";
        this.message = "SUCCESS";
    }

    public BaseResponseDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResponseDTO(String message) {
        this.code = "0";
        this.message = message;
    }
}
