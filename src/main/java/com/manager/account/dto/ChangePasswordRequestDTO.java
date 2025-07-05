package com.manager.account.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangePasswordRequestDTO {
    @ApiModelProperty(notes = "User Name", example = "admin", required = true)
    @NotBlank
    private String username;
    @ApiModelProperty(notes = "Old Password", example = "admin", required = true)
    @NotBlank
    private String password;
    @ApiModelProperty(notes = "New Password", example = "administrator", required = true)
    @NotBlank
    private String newPassword;
}
