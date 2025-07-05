package com.manager.account.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @ApiModelProperty(notes = "User Name", example = "admin", required = true)
    String username;
    @ApiModelProperty(notes = "Pass", example = "admin", required = true)
    String password;
}
