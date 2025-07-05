package com.manager.account.dto.token;

import lombok.Data;

@Data
public class TokenDTO {
    String sub;
    String exp;
    String iat;
    UsersDTO data;
}
