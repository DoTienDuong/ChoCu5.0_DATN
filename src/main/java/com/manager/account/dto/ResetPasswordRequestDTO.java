package com.manager.account.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ResetPasswordRequestDTO {
    @ApiModelProperty(notes = "User Name", example = "admin", required = true)
    @NotBlank
    private String username;

    @ApiModelProperty(notes = "New Password", example = "administrator", required = true)
    @NotBlank
    private String newPassword;
}
