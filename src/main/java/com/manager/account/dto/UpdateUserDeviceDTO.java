package com.manager.account.dto;

import lombok.Data;

@SuppressWarnings("Lombok")
@Data
public class UpdateUserDeviceDTO extends BaseRequestDTO {
    private String token;
}
