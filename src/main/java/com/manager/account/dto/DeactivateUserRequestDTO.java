package com.manager.account.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DeactivateUserRequestDTO {
    @ApiModelProperty(notes = "Reason", example = "Vi phạm chính sách", required = true)
    String reason;
}
