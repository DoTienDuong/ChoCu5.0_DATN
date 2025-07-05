package com.manager.account.dto.token;

import lombok.Data;

import java.util.List;

@Data
public class UsersDTO {
    Integer id;
    String loginId;
    String mobile;
    String email;
    String createdDate;
    String createdBy;
    String updatedDate;
    String updatedBy;
    UsersInfo usersInfo;
    List<String> permisstions;
}
