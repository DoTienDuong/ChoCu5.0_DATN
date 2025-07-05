package com.manager.account.controller;

import com.google.gson.Gson;
import com.manager.account.dto.token.TokenDTO;
import org.springframework.util.StringUtils;


import java.util.Base64;

public class BaseController {

    public TokenDTO convertToken(String token) {
        if(StringUtils.isEmpty(token))
            return null;

        if(token.startsWith("Bearer ")){
            token.replace("Bearer ", "");
        }
        String[] split_string = token.split("\\.");
        String base64EncodedBody = split_string[1];

        Base64.Decoder decoder = Base64.getUrlDecoder();
        String body = new String(decoder.decode(base64EncodedBody));
        TokenDTO tokenDTO = new Gson().fromJson(body, TokenDTO.class);
        return tokenDTO;
    }
}
