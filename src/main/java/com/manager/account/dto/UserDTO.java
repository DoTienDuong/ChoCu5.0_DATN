package com.manager.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;

@Data
@AllArgsConstructor
public class UserDTO {
    String id;
    String loginId;
    String password;
    String email;
    String mobile;
}
