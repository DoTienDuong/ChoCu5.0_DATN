package com.manager.account.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RegisterRequestDTO {
    @ApiModelProperty(notes = "Email", example = "admin@gmail.com", required = true)
    String email;
    @ApiModelProperty(notes = "username", example = "admin123", required = true)
    String username;
    @ApiModelProperty(notes = "password", example = "123456", required = true)
    String password;
//    @ApiModelProperty(notes = "FillName", example = "Admin", required = true)
//    String full_name;
//    @ApiModelProperty(notes = "PhoneNumber", example = "0911111111", required = true)
//    String phone_number;
//    @ApiModelProperty(notes = "birthday", example = "01/01/1900", required = true)
//    String birthday;
    @ApiModelProperty(notes = "role", example = "user", required = true)
    String role = "user";
}
