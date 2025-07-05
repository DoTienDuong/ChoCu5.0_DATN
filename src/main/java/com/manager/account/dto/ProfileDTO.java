package com.manager.account.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProfileDTO {
    @ApiModelProperty(notes = "full_name", example = "path", required = false)
    private String full_name;

    @ApiModelProperty(notes = "phone_number", example = "path", required = false)
    private String phone_number;

    @ApiModelProperty(notes = "avatar", example = "path", required = false)
    private String avatar;

    @ApiModelProperty(notes = "birthday", example = "path", required = false)
    private String birthday;

    @ApiModelProperty(notes = "password", example = "path", required = false)
    private String password;

    @ApiModelProperty(notes = "number_following", example = "path", required = false)
    private int number_following = -1;

    @ApiModelProperty(notes = "number_follower", example = "path", required = false)
    private int number_follower = -1;

    @ApiModelProperty(notes = "number_likes", example = "path", required = false)
    private int number_likes = -1;

    @ApiModelProperty(notes = "reason", example = "path", required = false)
    private String reason;

    @ApiModelProperty(notes = "account_status", example = "path", required = false)
    private String account_status;

    public ProfileDTO(String reason, String account_status) {
        this.reason = reason;
        this.account_status = account_status;
    }
}
